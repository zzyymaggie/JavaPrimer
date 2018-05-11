package xyz.zzyymaggie.java.primer.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(future);
        System.err.println("request finish");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            
        }
        System.out.println("data =" + future.get());
    }
}
