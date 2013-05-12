package org.herring.receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringReceiverServer {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                threadPool.execute(new HerringReceiverDispacher(socket));
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }

}
