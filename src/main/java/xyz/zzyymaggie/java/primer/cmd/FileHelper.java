/**
 * Copyright @zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java.primer.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHelper {
    private String fUploadFilePath = null;
    private String fFileName = null;
   
    public FileHelper(String fUploadFilePath, String fFileName) {
        this.fUploadFilePath = fUploadFilePath;
        this.fFileName = fFileName;
    }
    
    public FileHelper() {
        this(System.getProperty("user.dir") + File.separator + "tmp", "test");
    }

    public static void main(String[] args) throws IOException {
        FileHelper helper = new FileHelper();
        helper.evokeUploadCmd();
    }
    
    public boolean evokeUploadCmd() throws IOException
    {
        StringBuffer sqlldrLogs = new StringBuffer();
        int exitVal = 0;
        BufferedReader inputStdout = null;
        BufferedReader inputStderr = null;
        try {
            //process the stdout, stederr
            File workingPath = new File(fUploadFilePath).getAbsoluteFile();
            String cmdLine = workingPath.getAbsolutePath() + File.separator + fFileName+".bat";
            Process process = Runtime.getRuntime().exec(cmdLine,null,workingPath);
            inputStdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = inputStdout.readLine()) != null) {
                System.out.println(line);
            }
            InputStream stderr = process.getErrorStream();
            inputStderr = new BufferedReader(new InputStreamReader(stderr));
            while ( (line = inputStderr.readLine()) != null){
                System.out.println(line);
            }
            exitVal = process.waitFor();
        }
        catch (Exception ex) {
           sqlldrLogs.append(ex.getMessage()+"\n");
           exitVal = -1;
        }
        finally{
            if (inputStdout != null) inputStdout.close();
            if (inputStderr != null) inputStderr.close();
        }
        
        boolean returnVal = true;
        if(exitVal != 0 && exitVal!=2) {
            returnVal = false;
        }
        return returnVal;
    }
    
}
