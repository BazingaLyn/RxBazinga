package org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.CommandCode;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.InvokeContext;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.ProtocolCode;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.exception.DeserializationException;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.exception.SerializationException;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.ProtocolSwitch;

/**
 * @author liguolin
 * @create 2018-07-22 10:21
 **/
public class ResponseCommand extends RpcCommand {


    @Override
    public ProtocolCode getProtocolCode() {
        return null;
    }

    @Override
    public CommandCode getCmdCode() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public InvokeContext getInvokeContext() {
        return null;
    }

    @Override
    public byte getSerializer() {
        return 0;
    }

    @Override
    public ProtocolSwitch getProtocolSwitch() {
        return null;
    }

    @Override
    public void serialize() throws SerializationException {

    }

    @Override
    public void deserialize() throws DeserializationException {

    }

    @Override
    public void serializeContent(InvokeContext invokeContext) throws SerializationException {

    }

    @Override
    public void deserializeContent(InvokeContext invokeContext) throws DeserializationException {

    }
}
