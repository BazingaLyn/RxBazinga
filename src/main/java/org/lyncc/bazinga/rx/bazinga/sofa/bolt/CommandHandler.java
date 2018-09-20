package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import java.util.concurrent.ExecutorService;

/**
 * @author liguolin
 * @create 2018-07-18 16:57
 **/
public interface CommandHandler {


    void handleCommand(RemotingContext ctx, Object msg) throws Exception;


    /**
     * Register processor for command with specified code.
     *
     * @param cmd
     * @param processor
     */
    public void registerProcessor(CommandCode cmd, RemotingProcessor<?> processor);

    /**
     * Register default executor for the handler.
     *
     * @param executor
     */
    public void registerDefaultExecutor(ExecutorService executor);

    /**
     * Get default executor for the handler.
     */
    public ExecutorService getDefaultExecutor();



}
