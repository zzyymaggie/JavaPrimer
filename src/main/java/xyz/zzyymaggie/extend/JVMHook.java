package xyz.zzyymaggie.extend;

public class JVMHook {
    public static void main(String[] args) {
        start();
    }
    public static void start() {
        System.out.println("The JVM is started");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    //do something
                    System.out.println("The JVM Hook is execute");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
