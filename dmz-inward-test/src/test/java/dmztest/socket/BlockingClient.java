package dmztest.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author dmz
 * @date 2017/3/30
 */
public class BlockingClient {
    public static void main(String[] args) {
        try {
            InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
            System.out.println(isa.toString());
            Socket client = new Socket();
            client.connect(isa); //blocking
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            String readLine;
            while (!(readLine = stdIn.readLine()).equals("quit")) {
                writer.println(readLine);
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
