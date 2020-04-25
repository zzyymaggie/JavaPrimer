package xyz.zzyymaggie.network;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

/**
 * 代码参考网易云课堂的NIO公开课(https://www.bilibili.com/video/BV164411B7Dn)
 * 模拟http并发访问，了解BIO和NIO的线程状态
 *
 * <note>连接重用判断逻辑：</note>
 * request首部中包含Connection:Close，不复用
 * response中Content-Length长度设置不正确，不复用
 * response首部包含Connection:Close，不复用
 * reponse首部包含Connection:Keep-Alive，复用
 * 都没命中的情况下，如果HTTP版本高于1.0则复用
 */
public class BenchMarkTests {
    public static void main(String[] args) throws IOException {
        //http连接池---作用，http请求结束之后，TCP网络连接不断开（复用）
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(100);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        //并发发起20个http请求
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                CloseableHttpResponse response = null;
                try {
                    cyclicBarrier.await();
                    response = httpClient.execute(new HttpGet("http://localhost:8080/"));
                    System.out.println(response.getProtocolVersion());
                    System.out.println("status:" + response.getStatusLine()); //打印响应结果
                    response.getEntity().getContent().close(); //关闭流
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.in.read();
        System.out.println("客户端手动关闭，当前线程池连接状态：" + cm.getTotalStats().toString());

    }
}
