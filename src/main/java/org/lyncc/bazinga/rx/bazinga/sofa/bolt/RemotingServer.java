package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liguolin
 * @create 2018-07-18 11:41
 **/
public abstract class RemotingServer implements Server {

    private static final Logger logger = LoggerFactory.getLogger(RemotingServer.class);


    protected int port;
    /** server init status */
    private AtomicBoolean inited  = new AtomicBoolean(false);
    /** server start status */
    private AtomicBoolean started = new AtomicBoolean(false);

    public RemotingServer(int port) {
        this.port = port;
    }

    @Override
    public void init() {
        if (inited.compareAndSet(false, true)) {
            logger.warn("Initialize the server.");
            this.doInit();
        } else {
            logger.warn("Server has been inited already.");
        }
    }


    @Override
    public boolean start() {
        this.init();
        if (started.compareAndSet(false, true)) {
            try {
                logger.warn("Server started on port: " + port);
                return this.doStart();
            } catch (Throwable t) {
                started.set(false);
                this.stop();
                logger.error("ERROR: Failed to start the Server!", t);
                return false;
            }
        } else {
            String errMsg = "ERROR: The server has already started!";
            logger.error(errMsg);
            throw new IllegalStateException(errMsg);
        }
    }



    @Override
    public void stop() {
        if (inited.get() || started.get()) {
            inited.compareAndSet(true, false);
            started.compareAndSet(true, false);
            this.doStop();
        } else {
            throw new IllegalStateException("ERROR: The server has already stopped!");
        }
    }

    protected abstract void doInit();

    protected abstract boolean doStart();

    protected abstract void doStop();
}
