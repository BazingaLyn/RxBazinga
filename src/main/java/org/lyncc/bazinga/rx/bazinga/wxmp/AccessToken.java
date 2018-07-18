package org.lyncc.bazinga.rx.bazinga.wxmp;

import java.io.Serializable;

/**
 * @author liguolin
 * @create 2018-07-13 10:55
 **/
public class AccessToken implements Serializable {
    //获取到的凭证
    private String accessToken;
    //凭证有效时间，单位：秒
    private int expiresin;
    //此处省略get/set 方法


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }
}
