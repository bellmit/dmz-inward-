package dmztest.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dmz
 * @date 2017/3/30
 */
public class BlockingServer {
    private static int PORT = 8888;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                        String readLine;
                        while (!(readLine = reader.readLine()).equals("quit")) {
                            writer.println("Echo:" + readLine);
                            System.out.println("???");
                        }

                        System.out.println("DOWN!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

//                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
//                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
//                String readLine;
//                while (!(readLine = reader.readLine()).equals("quit")) {
//                    writer.println("Echo:" + readLine);
//                    System.out.println("???");
//                }
//
//                System.out.println("DOWN!");
            }

//            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
