package xyz.zzyymaggie.java.primer.futuretask;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {
    private String para;
    public RealData(String para) {
        this.para = para;
    }
    
    public String call() throws Exception {
        StringBuffer sBuffer = new StringBuffer();
        for(int i=0;i<10;i++) {
            sBuffer.append(para);
            try {
                //simulate one long operation
                Thread.sleep(100);
            }catch(InterruptedException e) {
                
            }
        }
        return sBuffer.toString();
    }
    
}
