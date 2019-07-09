package org.lyncc.bazinga.rx.bazinga.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelAndMMap {

    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new RandomAccessFile(new File("/Users/liguolin/nio/hello2"),"rw").getChannel();

        int length = "Hello".getBytes().length;
        System.out.println(length);
//        fileChannel.write(ByteBuffer.wrap("Hello".getBytes()),length);

        System.out.println(fileChannel.size());

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());



        mappedByteBuffer.put("KK".getBytes());

        ByteBuffer byteBuffer = mappedByteBuffer.slice();
        byteBuffer.put("M".getBytes());
       


    }
}
