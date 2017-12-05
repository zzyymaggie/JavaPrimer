package xyz.zzyymaggie.java.primer.future;

public class FutureData implements Data{
    protected RealData realData = null; //FutureData is wrapper for RealData
    protected boolean isReady = false;
    public synchronized void setRealData(RealData realData) {
        if(isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }
    
    public synchronized String getResult() { //wait for RealData's construct
        while(!isReady) {
            try {
                wait(); //wait for RealData is injected
            }catch(InterruptedException e) {
                
            }
        }
        return realData.result;
    }
}
