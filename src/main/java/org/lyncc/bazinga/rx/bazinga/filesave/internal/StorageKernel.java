package org.lyncc.bazinga.rx.bazinga.filesave.internal;

import cn.thinkinjava.model.DataItem;
import cn.thinkinjava.model.FileChannelWrapper;
import cn.thinkinjava.model.Index;
import cn.thinkinjava.util.IntUtil;
import cn.thinkinjava.util.LongUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:34
 */
public class StorageKernel {

    /** 1 M */
    public static final long active_file_max_length = 1024 * 1;

    private static final Logger LOGGER = LoggerFactory.getLogger("StorageKernel");


    private IndexManager indexManager = IndexManager.getInstance();

    private FileManager fileManager = FileManager.getInstance();

    private StorageKernel() {
    }

    public static StorageKernel getInstance() {

        return StorageKernelLazyHolder.INSTANCE;
    }

    private static class StorageKernelLazyHolder {

        private static final StorageKernel INSTANCE = new StorageKernel();
    }


    public void put(byte[] key, byte[] value) {
        DataItem dataItem = new DataItem();
        dataItem.setCrc("crc");
        dataItem.setTimestamp(System.currentTimeMillis());
        dataItem.setKey_sz(key.length);
        dataItem.setValue_sz(value.length);
        dataItem.setKey(key);
        dataItem.setValue(value);

        try {
            FileChannelWrapper activeFile = fileManager.activeFile;
            if (activeFile == null) {
                activeFile = fileManager.loadActiveFile();
                if (activeFile == null) {
                    activeFile = createActiveFileAndUpdateCache();
                }
            }

            long length = activeFile.getFileLength();
            // 剩余空间不足
            if (active_file_max_length - length < value.length + key.length) {
                activeFile = createActiveFileAndUpdateCache();
            }

            // seek
            activeFile.getFile().seek(activeFile.getFileLength());
            FileChannel channel = activeFile.getChannel();

            // write file
//            channel.write(ByteBuffer.wrap(dataItem.getCrc().getBytes()));
            channel.write(ByteBuffer.wrap(LongUtil.LongToBytes(dataItem.getTimestamp())));
            channel.write(ByteBuffer.wrap(IntUtil.IntToBytes(dataItem.getKey_sz())));
            channel.write(ByteBuffer.wrap(IntUtil.IntToBytes(dataItem.getValue_sz())));
            channel.write(ByteBuffer.wrap(dataItem.getKey()));
            // position
            long position = channel.position();
            channel.write(ByteBuffer.wrap(dataItem.getValue()));

            // save indexHashMap memory
            Index meta = new Index();
            meta.setFile_id(fileManager.activeFile.getFileName());
            meta.setValue_sz(dataItem.getValue_sz());
            meta.setValue_pos(position);
            meta.setTimestamp(dataItem.getTimestamp());

            indexManager.put(key, meta);

            channel.force(true);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private FileChannelWrapper createActiveFileAndUpdateCache() {
        FileChannelWrapper activeFile;
        activeFile = fileManager.createNewFile();
        fileManager.updateActiveFile(activeFile);
        activeFile = fileManager.getActiveFile();
        return activeFile;
    }


    public String get(byte[] key) {
        Index index = indexManager.get(key);
        if (index == null) {
            return null;
        }
        RandomAccessFile file = null;
        try {
            // todo cache
            file = new RandomAccessFile(index.getFile_id(), "r");

            ByteBuffer byteBuffer = ByteBuffer.allocate(index.getValue_sz());
            file.getChannel().read(byteBuffer, index.getValue_pos());

            return new String(byteBuffer.array());
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return null;

    }


    public void del(byte[] key) {
        put(key, "delete_flag".getBytes());
        indexManager.remove(key);
    }

    public void update(byte[] key, byte[] newVal) {
        put(key, newVal);
    }


}
