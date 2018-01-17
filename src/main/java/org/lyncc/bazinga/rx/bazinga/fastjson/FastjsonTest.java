package org.lyncc.bazinga.rx.bazinga.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

/**
 * fastjson测试基本类
 *
 * @author liguolin
 * @create 2018-01-16 17:26
 **/
public class FastjsonTest {

    public static void main(String[] args) {

        Pojo pojo = new Pojo();
        System.out.println(JSON.toJSONString(pojo,SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
    }

    private static class Pojo {

        private BigDecimal amount;

        private String username;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


}
