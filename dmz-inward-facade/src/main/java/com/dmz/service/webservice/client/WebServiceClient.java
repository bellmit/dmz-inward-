package com.dmz.service.webservice.client;

import com.dmz.service.webservice.client.englishchinese.invoke.EnglishChineseClientInvoke;
import com.dmz.service.webservice.client.weather.invoke.WeatherClientInvoke;
import com.dmz.service.webservice.client.englishchinese.ArrayOfString;
import com.dmz.service.webservice.client.englishchinese.TranslatorString;
import com.dmz.service.webservice.client.englishchinese.TranslatorStringResponse;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import com.dmz.service.webservice.client.weather.GetWeatherbyCityName;
import com.dmz.service.webservice.client.weather.GetWeatherbyCityNameResponse;

import javax.xml.soap.SOAPConstants;
import java.util.List;

/**
 * Created by dmz on 2016/2/29.
 */
public class WebServiceClient implements WeatherClientInvoke,EnglishChineseClientInvoke {

    public WebServiceClient(){

    }

    private String soap_version = SOAPConstants.SOAP_1_2_PROTOCOL;

    private String qname;

    private String packAge;

    private String soap_action;

    private WebServiceTemplate webservice;

    public WebServiceClient(String qName, String pacage, WebServiceTemplate webService) {
        this.qname = qName;
        this.packAge = pacage;
        this.webservice = webService;
    }

    public WebServiceClient(String qName, String pacage, WebServiceTemplate webService, String soapVersion, String actionSoap) throws Exception {
        this(qName, pacage,webService);
        this.soap_version = soapVersion;
        this.soap_action = actionSoap;
        this.webservice = webService;
    }


    public List<String> getWeatherbyCityName(String city) {
        GetWeatherbyCityName request = new GetWeatherbyCityName();
        request.setTheCityName(city);
        GetWeatherbyCityNameResponse response = (GetWeatherbyCityNameResponse) webservice
                .marshalSendAndReceive(
                        qname,
                        request,
                        new SoapActionCallback(soap_action));
        com.dmz.service.webservice.client.weather.ArrayOfString result = response.getGetWeatherbyCityNameResult();
        return result.getString();
    }


    public List<String> getTranslatorString(String word) {
        TranslatorString resuest = new TranslatorString();
        resuest.setWordKey(word);
        TranslatorStringResponse response = (TranslatorStringResponse) webservice.marshalSendAndReceive(qname, resuest, new SoapActionCallback(soap_action));
        ArrayOfString result = response.getTranslatorStringResult();
        return result.getString();
    }

    public String getSoap_version() {
        return soap_version;
    }

    public void setSoap_version(String soap_version) {
        this.soap_version = soap_version;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getPackAge() {
        return packAge;
    }

    public void setPackAge(String packAge) {
        this.packAge = packAge;
    }

    public String getSoap_action() {
        return soap_action;
    }

    public void setSoap_action(String soap_action) {
        this.soap_action = soap_action;
    }

    public WebServiceTemplate getWebservice() {
        return webservice;
    }

    public void setWebservice(WebServiceTemplate webservice) {
        this.webservice = webservice;
    }
}
