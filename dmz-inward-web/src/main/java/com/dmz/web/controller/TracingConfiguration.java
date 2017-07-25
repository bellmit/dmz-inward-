//package com.dmz.web.controller;
//
//import brave.Tracing;
//import brave.context.log4j2.ThreadContextCurrentTraceContext;
//import brave.http.HttpTracing;
//import com.dmz.web.advisor.TracingRquestHandlerInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.AsyncHandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//import zipkin.Span;
//import zipkin.reporter.AsyncReporter;
//import zipkin.reporter.Reporter;
//import zipkin.reporter.Sender;
//import zipkin.reporter.okhttp3.OkHttpSender;
//
///**
// * @author dmz
// * @date 2017/7/6
// */
//@Configuration
//// Importing these classes is effectively the same as declaring bean methods
////@Import({TracingRquestHandlerInterceptor.class})
//public class TracingConfiguration extends WebMvcConfigurerAdapter {
//
//    /** Configuration for how to send spans to Zipkin */
//    @Bean
//    Sender sender() {
//        return OkHttpSender.create("http://127.0.0.1:9411/api/v1/spans");
//    }
//
//    /** Configuration for how to buffer spans into messages for Zipkin */
//    @Bean
//    Reporter<Span> reporter() {
//        return AsyncReporter.builder(sender()).build();
//    }
//
//    /** Controls aspects of tracing such as the name that shows up in the UI */
//    @Bean
//    Tracing tracing() {
//        return Tracing.newBuilder()
//                .localServiceName("DMZ")
//                .currentTraceContext(ThreadContextCurrentTraceContext.create()) // puts trace IDs into logs
//                .reporter(reporter()).build();
//    }
//
//    // decides how to name and tag spans. By default they are named the same as the http method.
//    @Bean
//    HttpTracing httpTracing() {
//        return HttpTracing.create(tracing());
//    }
//
//    @Bean
//    AsyncHandlerInterceptor tracingInterceptor() {
//       return TracingRquestHandlerInterceptor.create(tracing());
//    }
//
//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
//        handlerMapping.setOrder(0);
//        handlerMapping.setInterceptors(new AsyncHandlerInterceptor[]{tracingInterceptor()});
//        return handlerMapping;
//    }
//
//    //@Autowired
//    //private TracingRquestHandlerInterceptor tracingRquestHandlerInterceptor;
//
//    //@Autowired
//    //private TracingClientHttpRequestInterceptor clientInterceptor;
//
//    //@Autowired
//    //private RestTemplate restTemplate;
//    //
//    ///** adds tracing to the {@linkplain ExampleController application-defined} rest template */
//    //@PostConstruct public void init() {
//    //  List<ClientHttpRequestInterceptor> interceptors =
//    //      new ArrayList<>(restTemplate.getInterceptors());
//    //  interceptors.add(clientInterceptor);
//    //  restTemplate.setInterceptors(interceptors);
//    //}
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tracingInterceptor());
//    }
//}
