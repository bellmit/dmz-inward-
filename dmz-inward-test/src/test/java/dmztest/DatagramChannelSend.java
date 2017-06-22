package dmztest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by dmz on 2016/7/28.
 */
public class DatagramChannelSend {
    public static void main(String[] args) {
        DatagramChannel channel = null; //UDP connection_less
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            String newData = "New String to write to file..."
                    + System.currentTimeMillis();

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            int bytesSent = channel.send(buf, new InetSocketAddress("localhost", 9999));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
