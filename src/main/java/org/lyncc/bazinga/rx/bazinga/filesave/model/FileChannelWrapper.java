package org.lyncc.bazinga.rx.bazinga.filesave.model;

import lombok.Data;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:48
 */
@Data
public class FileChannelWrapper {

    FileChannel channel;

    String fileName;

    RandomAccessFile file;

    public FileChannelWrapper(FileChannel channel, String fileName, RandomAccessFile file) {
        this.channel = channel;
        this.fileName = fileName;
        this.file = file;
    }

    public long getFileLength() {
        try {
            return file.length();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
