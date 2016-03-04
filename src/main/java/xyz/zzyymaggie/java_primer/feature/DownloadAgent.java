package xyz.zzyymaggie.java_primer.feature;

/**
 * This is the child class extends Agent. It has a private filed that overrides the father's field, so it couldn't change 
 * its father's filed value. This filed is a string filed, so we can identify easily. But when the field is dbConnection, 
 * it will be hard to find the difference.
 * @date 03/04/16
 * @author sozhang
 *
 */
public class DownloadAgent extends Agent {

    private String conStr;
    
    public DownloadAgent(String conStr){
        this.conStr = conStr;
    }
    
    @Override
    public void display(){
        System.out.println("Download Agent:" + this.conStr);
        super.display();
    }
    
    public static void main(String[] args) {
        DownloadAgent downloadAgent = new DownloadAgent("hello world!");
        downloadAgent.display();
    }
}
