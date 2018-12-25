package org.lyncc.bazinga.rx.bazinga.msentinel.slots.block;

/**
 * @author liguolin
 * @create 2018-12-21 10:33
 **/
public abstract class BlockException extends Exception {

    public static final String BLOCK_EXCEPTION_FLAG = "SentinelBlockException";


    public static RuntimeException THROW_OUT_EXCEPTION = new RuntimeException(BLOCK_EXCEPTION_FLAG);


    private String ruleLimitApp;

    //TODO
    public static StackTraceElement[] sentinelStackTrace = new StackTraceElement[]{
            new StackTraceElement(BlockException.class.getName(), "block", "BlockException", 0)
    };

    static {
        THROW_OUT_EXCEPTION.setStackTrace(sentinelStackTrace);
    }

    public BlockException(String ruleLimitApp) {
        super();
        this.ruleLimitApp = ruleLimitApp;
    }

    public BlockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockException(String ruleLimitApp, String message) {
        super(message);
        this.ruleLimitApp = ruleLimitApp;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public String getRuleLimitApp() {
        return ruleLimitApp;
    }

    public void setRuleLimitApp(String ruleLimitApp) {
        this.ruleLimitApp = ruleLimitApp;
    }

    public static boolean isBlockException(Throwable t){
        if(null == t){
            return false;
        }

        int counter = 0;

        Throwable cause = t;
        while(cause != null && counter ++ < 50){
            if ((cause instanceof BlockException) || (BLOCK_EXCEPTION_FLAG.equals(cause.getMessage()))) {
                return true;
            }
            cause = cause.getCause();
        }

        return false;

    }
}
