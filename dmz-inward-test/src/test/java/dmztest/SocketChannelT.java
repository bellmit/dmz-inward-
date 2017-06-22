package dmztest;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dmz on 2016/7/27.
 */
public class SocketChannelT {
    public static void main(String[] args) {
        SocketChannel chanel = null;
        try {
            Selector selector = Selector.open();
            chanel.configureBlocking(false);
            chanel.register(selector, SelectionKey.OP_READ);


            while (true) {
                int readyChanel = selector.select();
                if (readyChanel==0) continue;
                Set<SelectionKey> selectorKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectorKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
//                    int interest = key.interestOps();
//                    key.readyOps();

                    if (key.isAcceptable()) {

                    } else if (key.isConnectable()) {

                    }
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
