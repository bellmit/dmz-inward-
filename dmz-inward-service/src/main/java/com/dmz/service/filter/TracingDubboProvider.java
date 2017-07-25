package com.dmz.service.filter;


import brave.Span;
import brave.Tracer;
import brave.Tracing;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import zipkin.Endpoint;

/**
 * @author dmz
 * @date 2017/7/10
 */
@Activate(group = Constants.PROVIDER)
public class TracingDubboProvider implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("Dubbo Provider Filter");
        String invokerInterfaceName = invoker.getInterface().getName();
        String methodName = invokerInterfaceName + "." + invocation.getMethodName();
        Tracer tracer = Tracing.currentTracer();
        Span span = tracer.nextSpan().kind(Span.Kind.CLIENT).name(methodName);
        Endpoint.Builder builder = Endpoint.builder().serviceName("dmz").port(RpcContext.getContext().getRemotePort());
        if (!builder.parseIp(RpcContext.getContext().getRemoteHost())) {
            span.remoteEndpoint(builder.build());
        }
        span.start();
        tracer.withSpanInScope(span);
        //invocation.getAttachments().put(Tracer.SpanInScope.class.getName(), String.valueOf(tracer.withSpanInScope(span)));
        //request.setAttribute(Tracer.SpanInScope.class.getName(), );
        // INVOKE
        Result result = invoker.invoke(invocation);
        span.finish();
        return result;
    }
}
