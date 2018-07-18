package org.lyncc.bazinga.rx.bazinga.netty4.srv.serializer;

import org.lyncc.bazinga.rx.bazinga.netty4.srv.serializer.protostuff.ProtoStuffSerializer;

/**
 * @author liguolin
 * @create 2018-07-16 11:44
 **/
public class SerializerHolder {

    //使用google的protostuff
    //protostuff 是一个支持各种格式的一个序列化Java类库，包括 JSON、XML、YAML等格式。
    private static final Serializer serializer = new ProtoStuffSerializer();

    public static Serializer serializerImpl() {
        return serializer;
    }
}
