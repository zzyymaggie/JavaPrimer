package xyz.zzyymaggie.java.primer.future;

public class Client {
    public Data request(final String queryStr) {
        final FutureData futrue = new FutureData();
        new Thread() {
            public void run() { //RealData construct is a long operation, so put it in a single thread
                RealData realData = new RealData(queryStr);
                futrue.setRealData(realData);
            }
        }.start();
        return futrue; //FutureTask will return immediately.
    }
    
    public static void main(String[] args) {
        Client client = new Client();
        //
        Data data = client.request("name");
        System.out.println("request finish");
        Data value = client.request("value");
        System.out.println("request finish");
        
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e) {
            
        }
        System.out.println("Data =" + data.getResult());
        System.out.println("value=" + value.getResult());
    }
}
