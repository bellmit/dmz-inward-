package dmz.facade.webserviceTest;

import com.dmz.service.webservice.client.WebServiceConfiguration;
import com.dmz.service.webservice.client.englishchinese.invoke.EnglishChineseClientInvoke;
import com.dmz.service.webservice.client.weather.invoke.WeatherClientInvoke;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by dmz on 2016/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-facade-consumer.xml","classpath:Spring-utils.xml"})
public class WebServiceClientTest {
    @Test
    public void testEnglishChineseClientInvoke(){
        EnglishChineseClientInvoke webServiceClient = WebServiceConfiguration.getWebServiceByKey("http://fy.webxml.com.cn/webservices/EnglishChinese.asmx");
        List<String> list = webServiceClient.getTranslatorString("scope");
        showDetails(list);
    }

    @Test
    public void testWeatherClientInvoke(){
        WeatherClientInvoke webService = WebServiceConfiguration.getWebServiceByKey("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");
        List<String> list = webService.getWeatherbyCityName("上海");
        showDetails(list);
    }

    private void showDetails(List<String> details){
        for (String detail:details){
            System.out.println(detail);
        }
    }
}
