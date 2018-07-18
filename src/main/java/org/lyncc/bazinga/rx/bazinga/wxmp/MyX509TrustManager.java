package org.lyncc.bazinga.rx.bazinga.wxmp;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author liguolin
 * @create 2018-07-13 10:56
 **/
public class MyX509TrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        //        return new X509Certificate[0];
        return  null;
    }
}