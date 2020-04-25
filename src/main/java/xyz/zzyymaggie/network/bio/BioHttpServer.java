package xyz.zzyymaggie.network.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class BioHttpServer {
    private static final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    static {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                {
                    System.out.println("当前活跃线程数：" + threadPoolExecutor.getActiveCount());
                }
            }
        }, 0, 3000);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPoolExecutor.submit(() -> {
                while (true) { //连接没断开，随时可能传数据。
                    byte[] requestBody = new byte[1024];
                    socket.getInputStream().read(requestBody); //如果没有数据传过来，这个方法阻塞
                    String request = new String(requestBody);

                    System.out.println(request);

                    String responseContent = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world";
                    socket.getOutputStream().write(responseContent.getBytes());
                    socket.getOutputStream().flush();
                }
            });
        }

    }
}
