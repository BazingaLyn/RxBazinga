package org.lyncc.bazinga.rx.bazinga.jdk8.base;

import com.alibaba.fastjson.JSON;

/**
 * @author liguolin
 * @create 2018-08-09 9:44
 **/
public class TeacherSayHello extends HelloService {

    @Override
    protected String saySpecialHello(BaseObject o) {
        if(o instanceof Teacher){
            return JSON.toJSONString((Teacher)o);
        }
        return "bye teacher";
    }
}
