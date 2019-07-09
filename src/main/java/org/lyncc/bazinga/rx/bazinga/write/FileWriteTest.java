package org.lyncc.bazinga.rx.bazinga.write;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class FileWriteTest {

    private static String baseLogDirFile = String.format("%s/%s",System.getProperty("user.home"),"sherlock");

    @Before
    public void ready(){
        File dir = new File(baseLogDirFile);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }


    @Test
    public void test() throws IOException {

        long begintime = System.currentTimeMillis();
        String currentOpFileName = String.format("%s/%s", baseLogDirFile, "2019-01-08.log");

        File currentFile = new File(currentOpFileName);
        if(!currentFile.exists()){
            currentFile.createNewFile();
        }


        FileOutputStream fileOutputStream = new FileOutputStream(currentFile,false);
        byte[] content = "hello world simile simel sm slslslslsl \n".getBytes();
        for(int i = 0; i < 10000;i++){
            fileOutputStream.write(content);
        }
        fileOutputStream.close();
        System.out.println(System.currentTimeMillis() - begintime);
    }

}
