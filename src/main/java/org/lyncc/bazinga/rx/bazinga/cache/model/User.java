package org.lyncc.bazinga.rx.bazinga.cache.model;

/**
 * @author liguolin
 * @create 2018-09-28 15:18
 **/
public class User {

    public static void main(String[] args) {
        String st1 = "123";
        String st2 = "123";
        System.out.println(st1 == st2.intern());

        String str1 = new String("abc");
        String str2 = str1.intern();
        System.out.println(str1 == str2);
    }
}
