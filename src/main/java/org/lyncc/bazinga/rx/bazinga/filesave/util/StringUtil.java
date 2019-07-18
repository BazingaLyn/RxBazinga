package org.lyncc.bazinga.rx.bazinga.filesave.util;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-22-07:41
 */
public class StringUtil {

    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }

        if (s.trim().length() == 0) {
            return true;
        }
        return false;
    }

}
