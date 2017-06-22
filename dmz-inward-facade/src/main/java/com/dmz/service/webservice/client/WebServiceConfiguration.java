package com.dmz.service.webservice.client;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dmz on 2016/2/29.
 */
public class WebServiceConfiguration implements InitializingBean {

    private List<WebServiceClient> INFOS;

    private static ConcurrentHashMap<String, WebServiceClient> SERVICE_POOL = new ConcurrentHashMap();

    public WebServiceConfiguration() {

    }

    public static WebServiceClient getWebServiceByKey(String key) {
        return SERVICE_POOL.get(key);
    }

    public void initWebServiceClients() throws Exception {
        System.out.println("start init webservice clients");
        for (WebServiceClient info : INFOS) {
            WebServiceClient webServiceClient = SERVICE_POOL.get(info.getQname());
            if (webServiceClient == null) {
                // SOAP1.2 do not need SOAP_ACTION
                if (SOAPConstants.SOAP_1_2_PROTOCOL.equals(info.getSoap_version())) {
                    MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
                    SaajSoapMessageFactory newSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
                    WebServiceTemplate webService = new WebServiceTemplate(newSoapMessageFactory);
                    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
                    marshaller.setContextPath(info.getPackAge());
                    webService.setMarshaller(marshaller);
                    webService.setUnmarshaller(marshaller);
                    info.setWebservice(webService);
                    SERVICE_POOL.put(info.getQname(), info);

                } else if (SOAPConstants.SOAP_1_1_PROTOCOL.equals(info.getSoap_version())) {
                    // SOAP1.1 need SOAP_ACTION
                    WebServiceTemplate webService = new WebServiceTemplate();
                    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
                    marshaller.setContextPath(info.getPackAge());
                    webService.setMarshaller(marshaller);
                    webService.setUnmarshaller(marshaller);
                    info.setWebservice(webService);
                    SERVICE_POOL.put(info.getQname(), info);
                } else {
                    System.out.println("ILLEGAL SOAP_VERSION");
                    throw new Exception();
                }
            }
        }
        System.out.println("finish init webservice clients");
    }

    public void afterPropertiesSet() throws Exception {
        initWebServiceClients();
    }

    public List<WebServiceClient> getINFOS() {
        return INFOS;
    }

    public void setINFOS(List<WebServiceClient> INFOS) {
        this.INFOS = INFOS;
    }
}
