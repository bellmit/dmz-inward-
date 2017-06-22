package dmztest.HttpClientExample;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.*;
import java.util.List;

/**
 * Created by dmz on 2016/9/18.
 */
public class HttpClientContextEx {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        HttpContext context = <...>
//        HttpClientContext clientContext = HttpClientContext.adapt(context);
//        HttpClientContext clientContext = HttpClientContext.create();
//        HttpHost target = clientContext.getTargetHost();
//        HttpRequest request = clientContext.getRequest();
//        HttpResponse response = clientContext.getResponse();
//        RequestConfig config = clientContext.getRequestConfig();
//        System.out.println(JSON.toJSONString(target));


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet("http://localhost:8080/");
        System.out.println(httpget.getURI());
        CloseableHttpResponse response = httpclient.execute(httpget, context);
        try {
            HttpHost target = context.getTargetHost();
            List<URI> redirectLocations = context.getRedirectLocations();
            URI location = URIUtils.resolve(httpget.getURI(), target, redirectLocations);
            System.out.println("Final HTTP location: " + location.toASCIIString());
            // Expected to be an absolute URI
        } finally {
            response.close();
        }

        HttpClientContext clientContext = HttpClientContext.create();
        PlainConnectionSocketFactory sf = PlainConnectionSocketFactory.getSocketFactory();
        Socket socket = sf.createSocket(clientContext);
        int timeout = 1000; //ms
        HttpHost target = new HttpHost("localhost");
        InetSocketAddress remoteAddress = new InetSocketAddress(
                InetAddress.getByAddress(new byte[] {127,0,0,1}), 80);
        sf.connectSocket(timeout, socket, target, remoteAddress, null, clientContext);
    }
}
