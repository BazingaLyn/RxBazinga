package org.lyncc.bazinga.rx.bazinga.filesave.internal;

import lombok.Data;
import org.lyncc.bazinga.rx.bazinga.filesave.model.FileChannelWrapper;
import org.lyncc.bazinga.rx.bazinga.filesave.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:39
 */
@Data
public class FileManager {


    private static final Logger LOGGER = LoggerFactory.getLogger("FileManager");


    FileChannelWrapper hintFile;

    String indexFile;

    List<String> dataFiles;

    FileChannelWrapper activeFile;

    private FileManager() {
    }

    public static FileManager getInstance() {
        return FileManagerLazyHolder.INSTANCE;
    }

    private static class FileManagerLazyHolder {

        private static final FileManager INSTANCE = new FileManager();
    }

    synchronized FileChannelWrapper loadActiveFile() {
        if (activeFile == null) {
            File dir = new File("./BitcaskDB");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File("./BitcaskDB/active-record");
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(file, "rw");
                // 1 byte
                ByteBuffer buffer = ByteBuffer.allocate(1);
                raf.getChannel().read(buffer, 0);

                ByteBuffer fileNameBuffer = ByteBuffer.allocate(buffer.array()[0]);
                raf.getChannel().read(fileNameBuffer, 1);

                String activeFileName = new String(fileNameBuffer.array());
                if (StringUtil.isEmpty(activeFileName)) {
                    return null;
                }
                File f = new File(activeFileName);
                RandomAccessFile r = new RandomAccessFile(f, "rw");
                activeFile = new FileChannelWrapper(r.getChannel(), activeFileName, r);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return activeFile;
    }


    void updateActiveFile(FileChannelWrapper newFile) {
        File file = new File("./BitcaskDB/active-record");
        RandomAccessFile raf = null;
        try {
            // 将活跃文件更新.并持久化
            raf = new RandomAccessFile(file, "rw");
            FileChannel fc = raf.getChannel();

            // 1 byte
            fc.write(ByteBuffer.wrap(new byte[]{(byte) newFile.getFileName().length()}));
            fc.write(ByteBuffer.wrap(newFile.getFileName().getBytes()));

            fc.force(true);

            activeFile = newFile;

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }

    }


    public FileChannelWrapper createNewFile() {
        String u = UUID.randomUUID().toString();
        String path = "./BitcaskDB/" + u;
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(new File(path), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert file != null;
        FileChannel channel = file.getChannel();

        LOGGER.warn("create new File, path : {}", path);

        return new FileChannelWrapper(channel, path, file);

    }


}
