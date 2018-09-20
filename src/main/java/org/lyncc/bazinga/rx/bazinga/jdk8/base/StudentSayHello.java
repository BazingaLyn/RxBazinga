package org.lyncc.bazinga.rx.bazinga.jdk8.base;

import com.alibaba.fastjson.JSON;

/**
 * @author liguolin
 * @create 2018-08-09 9:49
 **/
public class StudentSayHello extends HelloService {
    @Override
    protected String saySpecialHello(BaseObject o) {
        if(o instanceof Student){
            return JSON.toJSONString((Student)o);
        }
        return "bye student";
    }
}
