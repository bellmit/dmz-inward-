package dmztest.nioserver.src.org.sse.server;

import dmztest.nioserver.src.org.sse.http.DefaultServlet;
import dmztest.nioserver.src.org.sse.http.HttpRequest;
import dmztest.nioserver.src.org.sse.http.HttpResponse;
import dmztest.nioserver.src.org.sse.http.ServletContext;

/**
 * @author Taylor Cowan
 */
public class RequestHandlerThread extends Thread {
    private Queue myQueue;
    private ServletContext myServletContext;
    private DefaultServlet defaultServlet = new DefaultServlet();

    public RequestHandlerThread(ServletContext context, Queue q) {
        myServletContext = context;
        myQueue = q;
    }

    public void run() {
        while (true) {
            Client client = (Client) myQueue.remove();
            try {
                for (; ; ) {
                    HttpRequest req = new HttpRequest(client.clientInputStream,
                            myServletContext);
                    HttpResponse res = new HttpResponse(client.key);
                    defaultServlet.service(req, res);
                    if (client.notifyRequestDone())
                        break;
                }
            } catch (Exception e) {
                client.key.cancel();
                client.key.selector().wakeup();
            }
        }
    }
}