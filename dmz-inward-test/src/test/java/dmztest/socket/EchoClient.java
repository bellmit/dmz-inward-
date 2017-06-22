package dmztest.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author dmz
 * @date 2017/3/9
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        int portNumber = 8888;
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), portNumber);
            Socket socketClient = new Socket();
            socketClient.connect(inetSocketAddress); //TCP 3 time handshake

            PrintWriter printWriter = new PrintWriter(socketClient.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketClient.getInputStream(), "UTF-8"));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userLine;
            while ((userLine = stdIn.readLine()) != "\n") {
                printWriter.println(userLine);
                System.out.println("echo:" + bufferedReader.readLine());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
