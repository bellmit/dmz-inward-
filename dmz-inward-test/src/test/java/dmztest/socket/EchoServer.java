package dmztest.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dmz
 * @date 2017/3/9
 */
public class EchoServer {

    public static void main(String[] args) {

        int portNumber = 999;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            Socket clientSocket = serverSocket.accept();// 阻塞
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                printWriter.println(readLine);
            }
        } catch (IOException e) {
            System.out.println("Exception listen for port " + portNumber);
        }
    }

}
