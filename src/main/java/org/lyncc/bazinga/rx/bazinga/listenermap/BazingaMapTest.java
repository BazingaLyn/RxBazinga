package org.lyncc.bazinga.rx.bazinga.listenermap;

import java.util.Map;

public class BazingaMapTest {

    public static void main(String[] args) {
        Map<String,Object> maps = new BazingaMap<>();
        BazingaListener<String,Object> listener = new BazingaListener<String, Object>() {
            @Override
            public void configUpdate(String key, Object oldValue, Object newValue) {
                System.out.println(String.format("invoke configUpdate execute %s oldValue is %s and newValue is %s",key,oldValue,newValue));
            }

            @Override
            public void configLoad(String key, Object value) {
                System.out.println(String.format("invoke configLoad %s key and new value is %s",key,value));

            }
        };

        ((BazingaMap<String, Object>) maps).addListener(listener);

        maps.put("hello","world");

        maps.put("hello2","nihao");

        maps.put("hello","world2");

        maps.remove("hello2");
    }
}
