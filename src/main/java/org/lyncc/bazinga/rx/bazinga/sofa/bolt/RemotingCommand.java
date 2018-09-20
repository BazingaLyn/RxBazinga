package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.exception.DeserializationException;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.exception.SerializationException;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.ProtocolSwitch;

import java.io.Serializable;

/**
 * @author liguolin
 * @create 2018-07-20 9:41
 **/
public interface RemotingCommand extends Serializable {

    /**
     * Get the code of the protocol that this command belongs to
     *
     * @return protocol code
     */
    ProtocolCode getProtocolCode();


    /**
     *
     * @return
     */
    CommandCode getCmdCode();


    int getId();

    /**
     * Get invoke context for this command
     *
     * @return context
     */
    InvokeContext getInvokeContext();

    /**
     * Get serializer type for this command
     *
     * @return
     */
    byte getSerializer();

    /**
     * Get the protocol switch status for this command
     *
     * @return
     */
    ProtocolSwitch getProtocolSwitch();

    /**
     * Serialize all parts of remoting command
     *
     * @throws SerializationException
     */
    void serialize() throws SerializationException;

    /**
     * Deserialize all parts of remoting command
     *
     * @throws DeserializationException
     */
    void deserialize() throws DeserializationException;

    /**
     * Serialize content of remoting command
     *
     * @param invokeContext
     * @throws SerializationException
     */
    void serializeContent(InvokeContext invokeContext) throws SerializationException;

    /**
     * Deserialize content of remoting command
     *
     * @param invokeContext
     * @throws DeserializationException
     */
    void deserializeContent(InvokeContext invokeContext) throws DeserializationException;


}
