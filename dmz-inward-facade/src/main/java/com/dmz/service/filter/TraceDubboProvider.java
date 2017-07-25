package com.dmz.service.filter;

import com.alibaba.dubbo.remoting.TimeoutException;
import com.alibaba.dubbo.rpc.*;
import com.dmz.service.BaseRequest;
import com.dmz.service.BaseResponse;
import com.google.common.base.Stopwatch;
import com.twitter.zipkin.gen.Annotation;
import com.twitter.zipkin.gen.Endpoint;
import com.twitter.zipkin.gen.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/7/5
 */
public class TraceDubboProvider implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TraceDubboProvider.class);

    public TraceDubboProvider() {
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String methodName = null;
        String consumerIp = null;
        String invokerInterfaceName = invoker.getInterface().getName();
        Result result;
        Class<?> returnType = null;
        BaseResponse baseResponse;
        try {
            Object[] args;
            try {
                methodName = invokerInterfaceName + "." + invocation.getMethodName();
                consumerIp = RpcContext.getContext().getRemoteHost() + ":" + RpcContext.getContext().getRemotePort();
                args = invocation.getArguments();
                StringBuilder builderArgs = new StringBuilder();
                if (args != null) {
                    for (Object arg : args) {
                        if (arg instanceof BaseRequest) {
                            builderArgs.append(arg);
                        }
                        if (arg instanceof String || arg instanceof Integer || arg instanceof Long || arg instanceof Double || arg instanceof Float || arg instanceof Character || arg instanceof Boolean) {
                            builderArgs.append(arg);
                        }
                    }
                }
                logger.info("PROVIDER_IN:{} CONSUMER_HOST:{} REQUEST:{}", methodName, consumerIp, builderArgs);
            } catch (Exception e) {
                logger.error("REQUEST_ARG_BUILD_FAILED:{}", e);
            }

            returnType = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes()).getReturnType();

            Map<String, String> attaches = invocation.getAttachments();
            if (!attaches.containsKey(TraceContext.TRACE_ID_KEY)){
                return invoker.invoke(invocation);
            }
            Stopwatch watch = Stopwatch.createStarted();
            Span providerSpan= startTrace(attaches);
            // RPC INVOKE
            result = invoker.invoke(invocation);
            endTrace(providerSpan,watch);


            if (result == null) {
                if (BaseResponse.class.isAssignableFrom(returnType)) {
                    RpcResult rpcResult = new RpcResult();
                    baseResponse = (BaseResponse) returnType.newInstance();
                    baseResponse.setRespmsg("");
                    baseResponse.setRescode("");
                    logger.info("PROVIDER_IN:{} CONSUMER_HOST:{} RSP_ERROR :{}", new Object[]{methodName, consumerIp, "DUBBO RETURN NULL"});
                    rpcResult.setValue(baseResponse);
                    return rpcResult;
                } else {
                    throw new RpcException("DUBBO RETURN NULL !");
                }
            }
            return result;

        } catch (Exception e) {
            if (BaseResponse.class.isAssignableFrom(returnType)) {
                RpcResult rpcResult = new RpcResult();
                try {
                    baseResponse = (BaseResponse) returnType.newInstance();
                    if (e instanceof SQLException) {
                        baseResponse.setRescode("");
                        baseResponse.setRespmsg("");
                    } else if (e instanceof TimeoutException) {
                        baseResponse.setRescode("");
                        baseResponse.setRespmsg("");
                    } else if (e instanceof RpcException) {
                        baseResponse.setRescode("");
                        baseResponse.setRespmsg("");
                    } else {
                        baseResponse.setRescode("");
                        baseResponse.setRespmsg("系统异常");
                    }
                } catch (Exception e1) {
                    logger.info("PROVIDER_IN:{} CONSUMER_HOST:{} RPC_ERROR", methodName, consumerIp, e1);
                    throw new RpcException(e1);
                }
                rpcResult.setValue(baseResponse);
            }
            logger.info("PROVIDER_IN:{} CONSUMER_HOST:{}", methodName, consumerIp);
            throw new RpcException(e);
        }
    }


    private Span startTrace(Map<String, String> attaches) {

        Long traceId = Long.valueOf(attaches.get(TraceContext.TRACE_ID_KEY));
        Long parentSpanId = Long.valueOf(attaches.get(TraceContext.SPAN_ID_KEY));

        TraceContext.start();
        TraceContext.setTraceId(traceId);
        TraceContext.setSpanId(parentSpanId);

        Span providerSpan = new Span();
        long id = 2000;
        providerSpan.setId(id);
        providerSpan.setParent_id(parentSpanId);
        providerSpan.setTrace_id(traceId);
        providerSpan.setName("DMZ");
        long timestamp = System.currentTimeMillis()*1000;
        providerSpan.setTimestamp(timestamp);

        providerSpan.addToAnnotations(
                Annotation.create(timestamp, TraceContext.Service_Receive,
                        Endpoint.create(
                                "DMZ",
                                NetworkUtils.ip2Num(NetworkUtils.getSiteIp()),
                                22)));

        TraceContext.addSpan(providerSpan);
        return providerSpan;
    }


    private void endTrace(Span span, Stopwatch watch) {

        span.addToAnnotations(
                Annotation.create(System.currentTimeMillis()*1000, TraceContext.Service_Send,
                        Endpoint.create(
                                span.getName(),
                                NetworkUtils.ip2Num(NetworkUtils.getSiteIp()),
                                22)));

        span.setDuration(watch.stop().elapsed(TimeUnit.MICROSECONDS));
        //TraceAgent traceAgent=new TraceAgent(TraceContext.getTraceConfig().getZipkinUrl());
        //
        //traceAgent.send(TraceContext.getSpans());

    }

}
