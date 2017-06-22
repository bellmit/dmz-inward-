package dmztest.nioserver.src.org.sse.server;

import java.io.File;
import java.io.IOException;

/**
 * @author Taylor Cowan
 */
public class Start {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt("999");

        final Server s = new Server(port, new ServerEventHandler("C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/", 4));

        System.out.println("Starting server on port " + port);

        System.out.println("Web root folder: " + new File("C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/").getAbsolutePath());
        while (true)
            s.listen();
    }
}
