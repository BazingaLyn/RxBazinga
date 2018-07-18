package org.lyncc.bazinga.rx.bazinga.netty4.srv.common;

/**
 * @author liguolin
 * @create 2018-07-16 10:00
 **/
public class NativeSupport {

    private static final boolean SUPPORT_NATIVE_ET;

    static {
        boolean epoll;
        try {
            Class.forName("io.netty.channel.epoll.Native");
            epoll = true;
        } catch (Throwable e) {
            epoll = false;
        }
        SUPPORT_NATIVE_ET = epoll;
    }

    /**
     * The native socket transport for Linux using JNI.
     */
    public static boolean isSupportNativeET() {
        return SUPPORT_NATIVE_ET;
    }
}
