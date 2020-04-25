package xyz.zzyymaggie.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NioHttpServer {
    private static Selector selector;
    public static ServerSocketChannel serverSocketChannel;
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
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8081));

        System.out.println("NIO服务器启动:8081");

        //换一种思路---能不能在新连接来的时候，再去调accept()
        selector = Selector.open();
        //登记：标识对8081端口 ACCEPT事件感兴趣，帮我查询监听有没有定义一个的网络事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //阻塞等待，依赖JDK封装的操作系统多路复用实现
            //但是可以接收所有连接的请求，实现单线程处理所有请求的核心！
            selector.select(1000);

            Set<SelectionKey> results = selector.selectedKeys(); //查询结果，就像sql查询一样
            Iterator<SelectionKey> iterator = results.iterator();
            while (iterator.hasNext()) { //遍历每一个事件
                SelectionKey result = iterator.next();
                //处理过的结果，删除，要先删除，以免出现不可预知的问题
                iterator.remove();
                if (result.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ); //等有数据了再处理
                } else if (result.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) result.channel();
                    result.cancel(); //取消selector对该连接的监听。因为安排线程正在处理，不需要重复处理

                    threadPoolExecutor.submit(() -> {
                        //1. 读取请求内容
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        try {
                            socketChannel.read(byteBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        byteBuffer.flip(); //转为读取数据的模式
                        String request = new String(byteBuffer.array());

                        //2.处理请求
                        //TODO: 处理请求。。。调用servlet
                        System.out.println(request);

                        //3.返回响应
                        String responseContent = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world";
                        try {
                            socketChannel.write(ByteBuffer.wrap(responseContent.getBytes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //最后处理完毕了，不会关闭（连接可能没断开，例如长连接），继续交给selector监听
                        try {
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        }
                    });

                }
            }
            selector.selectNow(); //过滤掉cancel keys
        }

    }
}
