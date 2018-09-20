package org.lyncc.bazinga.rx.bazinga.netty4.study.codec.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMapper {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return MAPPER;
    }

}
