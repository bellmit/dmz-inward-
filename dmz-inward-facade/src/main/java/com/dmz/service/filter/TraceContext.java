package com.dmz.service.filter;

import com.twitter.zipkin.gen.Span;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/7/5
 */
public class TraceContext extends AbstractTraceContext {

    private static ThreadLocal<Long> TRACE_ID = new InheritableThreadLocal<>();

    private static ThreadLocal<Long> SPAN_ID = new InheritableThreadLocal<>();

    private static ThreadLocal<List<Span>> SPAN_LIST = new InheritableThreadLocal<>();


    /**
     *  就是一个全局的跟踪ID，是跟踪的入口点，根据需求来决定在哪生成traceId。
         比如一个http请求，首先入口是web应用，一般看完整的调用链这里自然是traceId生成的起点，
         结束点在web请求返回点
     */
    public static final String TRACE_ID_KEY = "traceId";


    /**
     *这是下一层的请求跟踪ID,这个也根据自己的需求，比如认为一次rpc，
     一次sql执行等都可以是一个span。一个traceId包含一个以上的spanId。
     */
    public static final String SPAN_ID_KEY = "spanId";


    //客户端发起请求的时间，比如dubbo调用端开始执行远程调用之前
    public static final String Client_Send = "cs";

    //客户端收到处理完请求的时间
    public static final String Client_Receive = "cr";

    //服务端收到调用端请求的时间
    public static final String Service_Receive = "sr";

    //服务端处理完逻辑的时间
    public static final String Service_Send = "ss";

    //private static TraceConfig traceConfig;
    //
    //public static TraceConfig getTraceConfig(){
    //    return traceConfig;
    //}
    //
    //public static void setTraceConfig(TraceConfig config){
    //    traceConfig=config;
    //}

    private TraceContext(){}

    public static void clear(){
        TRACE_ID.remove();
        SPAN_ID.remove();
        SPAN_LIST.remove();
    }

    //public static void init(TraceConfig traceConfig) {
    //    setTraceConfig(traceConfig);
    //}

    public static void start(){
        clear();
        SPAN_LIST.set(new ArrayList<>());
    }


    public static ThreadLocal<Long> getTraceId() {
        return TRACE_ID;
    }

    public static void setTraceId(Long traceId) {
        TRACE_ID.set(traceId);
    }

    public static ThreadLocal<Long> getSpanId() {
        return SPAN_ID;
    }

    public static void setSpanId(Long spanId) {
        SPAN_ID.set(spanId);
    }
    public static void addSpan(com.twitter.zipkin.gen.Span span){
        SPAN_LIST.get().add(span);
    }

    public static ThreadLocal<List<Span>> getSpanList() {
        return SPAN_LIST;
    }

    public static void setSpanList(ThreadLocal<List<Span>> spanList) {
        SPAN_LIST = spanList;
    }

    public static String getTraceIdKey() {
        return TRACE_ID_KEY;
    }

    public static String getSpanIdKey() {
        return SPAN_ID_KEY;
    }

    public static String getClient_Send() {
        return Client_Send;
    }

    public static String getClient_Receive() {
        return Client_Receive;
    }

    public static String getService_Receive() {
        return Service_Receive;
    }

    public static String getService_Send() {
        return Service_Send;
    }

}