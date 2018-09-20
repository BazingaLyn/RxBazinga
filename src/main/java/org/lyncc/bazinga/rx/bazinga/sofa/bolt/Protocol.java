package org.lyncc.bazinga.rx.bazinga.sofa.bolt;


/**
 * @author liguolin
 * @create 2018-07-18 16:48
 **/
public interface Protocol {

    /**
     * Get the encoder for the protocol.
     *
     * @return
     */
    CommandEncoder getEncoder();

    /**
     * Get the decoder for the protocol.
     *
     * @return
     */
    CommandDecoder getDecoder();

    /**
     * Get the heartbeat trigger for the protocol.
     *
     * @return
     */
    HeartbeatTrigger getHeartbeatTrigger();

    /**
     * Get the command handler for the protocol.
     *
     * @return
     */
    CommandHandler getCommandHandler();

    /**
     * Get the command factory for the protocol.
     * @return
     */
    CommandFactory getCommandFactory();
}
