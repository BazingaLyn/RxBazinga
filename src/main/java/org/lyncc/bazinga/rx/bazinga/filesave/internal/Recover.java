package org.lyncc.bazinga.rx.bazinga.filesave.internal;

import cn.thinkinjava.model.Index;
import cn.thinkinjava.util.IntUtil;
import cn.thinkinjava.util.LongUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * 快速恢复.
 *
 * 即:在合并文件时,需要同时生成索引文件,提高系统重启后的恢复时间.
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:29
 */
public class Recover {

    static byte[] delete_flag = "delete_flag".getBytes();

    private static IndexManager indexManager = IndexManager.getInstance();

    /**
     * |---8 Timestamp---|---4 keySize--|---4 valueSize---|-----key-----|-----value----|
     */
   static void recoverIndexFile() {
        File file = new File("./BitcaskDB");
        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                try {
                    if (listFile.getPath().contains("active-record")) {
                        continue;
                    }

                    RandomAccessFile r = new RandomAccessFile(listFile, "r");
                    FileChannel fileChannel = r.getChannel();
                    while (true) {

                        if (fileChannel.position() >= r.length()) {
                            break;
                        }

                        ByteBuffer timestampBF = ByteBuffer.allocate(8);
                        ByteBuffer keySizeBF = ByteBuffer.allocate(4);
                        ByteBuffer valueSizeBF = ByteBuffer.allocate(4);

                        fileChannel.read(timestampBF);
                        fileChannel.read(keySizeBF);
                        fileChannel.read(valueSizeBF);

                        ByteBuffer keyBF = ByteBuffer.allocate(IntUtil.BytesToInt(keySizeBF.array()));
                        ByteBuffer valueBF = ByteBuffer.allocate(IntUtil.BytesToInt(valueSizeBF.array()));

                        fileChannel.read(keyBF);
                        long valuePos = fileChannel.position();
                        fileChannel.read(valueBF);

                        // 被删除了
                        if (new String(valueBF.array()).equals("delete_flag")) {
                            continue;
                        }

                        Index index = new Index();
                        index.setFile_id(listFile.getPath());
                        index.setValue_sz(IntUtil.BytesToInt(valueSizeBF.array()));
                        index.setValue_pos(valuePos);
                        index.setTimestamp(LongUtil.BytesToLong(timestampBF.array()));

                        Index exists;
                        // 使用最新的
                        if ((exists = indexManager.get(keyBF.array())) != null) {
                            if (exists.getTimestamp() > index.getTimestamp()) {
                                index = exists;
                            }
                        }

                        indexManager.put(keyBF.array(), index);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
