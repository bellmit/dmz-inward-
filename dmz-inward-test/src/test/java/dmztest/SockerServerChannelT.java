package dmztest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by dmz on 2016/7/28.
 */
public class SockerServerChannelT {
    public static void main(String[] args) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();

            server.socket().bind(new InetSocketAddress(9999));
            server.configureBlocking(false);

            while (true) {
                SocketChannel channel = server.accept();  // 异步
                if (channel != null) {
//                    DO SOMETHING...
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
