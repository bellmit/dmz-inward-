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
public class TraceDubboConsumer implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TraceDubboConsumer.class);

    public TraceDubboConsumer() {
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String methodName = null;
        String providerIp = null;
        String invokerInterfaceName = invoker.getInterface().getName();
        Result result;
        Class<?> returnType = null;
        BaseResponse baseResponse;
        try {
            Object[] args;
            try {
                methodName = invokerInterfaceName + "." + invocation.getMethodName();
                providerIp = RpcContext.getContext().getRemoteHost() + ":" + RpcContext.getContext().getRemotePort();
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
                logger.info("CONSUMER_IN:{} PROVIDER_HOST:{} REQUEST:{}", methodName, providerIp, builderArgs);
            } catch (Exception e) {
                logger.error("REQUEST_ARG_BUILD_FAILED:{}", e);
            }

            returnType = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes()).getReturnType();

            Stopwatch watch = Stopwatch.createStarted();
            Span span= startTrace(invocation);
            TraceContext.start();
            TraceContext.setTraceId(span.getTrace_id());
            TraceContext.setSpanId(span.getId());
            TraceContext.addSpan(span);
            // RPC INVOKE
            result = invoker.invoke(invocation);
            endTrace(span, watch);

            if (result == null) {
                if (BaseResponse.class.isAssignableFrom(returnType)) {
                    RpcResult rpcResult = new RpcResult();
                    baseResponse = (BaseResponse) returnType.newInstance();
                    baseResponse.setRespmsg("");
                    baseResponse.setRescode("");
                    logger.info("CONSUMER_IN:{} PROVIDER_HOST:{} RSP_ERROR :{}", new Object[]{methodName, providerIp, "DUBBO RETURN NULL"});
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
                    logger.info("CONSUMER_IN:{} PROVIDER_HOST:{} RPC_ERROR", methodName, providerIp, e1);
                    throw new RpcException(e1);
                }
                rpcResult.setValue(baseResponse);
            }
            logger.info("CONSUMER_IN:{} PROVIDER_HOST:{}", methodName, providerIp);
            throw new RpcException(e);
        }
    }

    private void endTrace(Span span, Stopwatch watch) {

        span.addToAnnotations(
                Annotation.create(System.currentTimeMillis()*1000, TraceContext.Client_Receive,
                        Endpoint.create(
                                span.getName(),
                                NetworkUtils.ip2Num(NetworkUtils.getSiteIp()),
                                32)));

        span.setDuration(watch.stop().elapsed(TimeUnit.MICROSECONDS));

        //TraceAgent traceAgent=new TraceAgent(TraceContext.getTraceConfig().getZipkinUrl());
        //traceAgent.send(TraceContext.getSpans());

    }

    private com.twitter.zipkin.gen.Span startTrace(Invocation invocation) {

        com.twitter.zipkin.gen.Span consumerSpan = new com.twitter.zipkin.gen.Span();
        Long traceId;
        long id = 100;
        consumerSpan.setId(id);
        if (null == TraceContext.getTraceId()) {
            TraceContext.start();
            traceId = id;
        } else {
            traceId = TraceContext.getTraceId().get();
        }

        consumerSpan.setTrace_id(traceId);
        consumerSpan.setParent_id(TraceContext.getSpanId().get());
        consumerSpan.setName("DMZ");
        long timestamp = System.currentTimeMillis() * 1000;
        consumerSpan.setTimestamp(timestamp);

        consumerSpan.addToAnnotations(
                Annotation.create(timestamp, TraceContext.Client_Send,
                        Endpoint.create(
                                "DMZ",
                                NetworkUtils.ip2Num(NetworkUtils.getSiteIp()),
                                32)));

        Map<String, String> attaches = invocation.getAttachments();
        attaches.put(TraceContext.TRACE_ID_KEY, String.valueOf(consumerSpan.getTrace_id()));
        attaches.put(TraceContext.SPAN_ID_KEY, String.valueOf(consumerSpan.getId()));
        return consumerSpan;
    }


}
