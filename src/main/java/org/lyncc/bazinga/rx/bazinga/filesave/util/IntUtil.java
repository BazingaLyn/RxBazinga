package org.lyncc.bazinga.rx.bazinga.filesave.util;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-09:14
 */
public class IntUtil {

    public static byte[] IntToBytes(int a) {
        return new byte[]{
            (byte) ((a >> 24) & 0xFF),
            (byte) ((a >> 16) & 0xFF),
            (byte) ((a >> 8) & 0xFF),
            (byte) (a & 0xFF)
        };
    }

    public static int BytesToInt(byte[] b) {
        return b[3] & 0xFF |
            (b[2] & 0xFF) << 8 |
            (b[1] & 0xFF) << 16 |
            (b[0] & 0xFF) << 24;
    }


}
