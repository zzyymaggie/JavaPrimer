package xyz.zzyymaggie.java.primer.future;


public class RealData implements Data{
    protected final String result;
    
    public RealData(String para) {
        StringBuffer sBuffer = new StringBuffer();
        for(int i=0;i<10;i++) {
            sBuffer.append(para);
            try {
                //simulate one long operation
                Thread.sleep(100);
            }catch(InterruptedException e) {
                
            }
        }
        result = sBuffer.toString();
    }
    
    public String getResult() {
        return result;
    }
}
