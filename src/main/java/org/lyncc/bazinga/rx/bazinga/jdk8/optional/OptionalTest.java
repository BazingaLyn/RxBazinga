package org.lyncc.bazinga.rx.bazinga.jdk8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author liguolin
 * @create 2018-06-29 15:06
 **/
public class OptionalTest {

    @Test
    public void test1(){
        User user = new User("Lyncc");
        user = Optional.ofNullable(user).orElse(createUser()); //orElse 一定会被执行，但是user如果是null，这执行不会赋值
//        user = Optional.ofNullable(user).orElseGet(()->createUser());
        System.out.println(user);

    }

    public User createUser(){
        User user = new User("zhangsan");
        return user;
    }
}
