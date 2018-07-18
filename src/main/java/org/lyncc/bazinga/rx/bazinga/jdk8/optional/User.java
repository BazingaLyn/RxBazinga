package org.lyncc.bazinga.rx.bazinga.jdk8.optional;

import com.alibaba.fastjson.JSON;

/**
 * @author liguolin
 * @create 2018-06-29 15:05
 **/
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
