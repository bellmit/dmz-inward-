package dmztest.HttpClientExample;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by dmz on 2016/9/13.
 */
public class HttpClientResponse {
    public static void main(String[] args) throws IOException {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie",
                "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie",
                "c2=b; path=\"/\", c3=c; domain=\"localhost\"");



//        Header[] headers = response.getHeaders("Set-Cookie");
//        for (Header header : headers) {
//            System.out.println(header.getName());
//            HeaderElement[] elements = header.getElements();
//            for (HeaderElement element : elements) {
//                System.out.println(element.getName() + "=" + element.getValue());
//                NameValuePair[] params = element.getParameters();
////                for (NameValuePair param : params) {
////                    System.out.println(param.getName() + " = " + param.getValue());
////                }
//                for (int i = 0; i < params.length; i++) {
//                    System.out.println(" " + params[i]);
//                }
//            }
//        }

        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()) {
            System.out.println(it.nextHeader());
        }

//        HeaderElementIterator it = new BasicHeaderElementIterator(
//                response.headerIterator("Set-Cookie"));
//        while (it.hasNext()) {
//            HeaderElement elem = it.nextElement();
//            System.out.println(elem.getName() + " = " + elem.getValue());
//            NameValuePair[] params = elem.getParameters();
//            for (int i = 0; i < params.length; i++) {
//                System.out.println(" " + params[i]);
//            }
//        }

        StringEntity myEntity = new StringEntity("important message",
                ContentType.create("text/plain", "UTF-8"));
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/");
        CloseableHttpResponse responses = httpclient.execute(httpget);
        try {
            HttpEntity entity = responses.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (len != -1 && len < 2048) {
                    System.out.println(EntityUtils.toString(entity));
                } else {
                    // Stream content out
                }
            }
        } finally {
            responses.close();
        }

//        split(System.getProperty("https.protocols")),
//                split(System.getProperty("https.cipherSuites")),


    }
}
