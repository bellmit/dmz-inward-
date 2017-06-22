package dmztest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by dmz on 2016/7/28.
 */
public class DatagramChannelListen {
    public static void main(String[] ags) {
        try {
            DatagramChannel channel = DatagramChannel.open(); //UDP connection_less
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(9999));
            ByteBuffer buffer = ByteBuffer.allocate(20);
            while (true) {
                SocketAddress sa = channel.receive(buffer);
                if (sa == null) {
                    continue;
                }
                buffer.flip();
                    System.out.println(buffer);
                buffer.clear();
            }


        } catch (IOException e) {


        }

    }
}
