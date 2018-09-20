package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-18 17:03
 **/
public interface UserProcessor<T> {


    BizContext preHandleRequest(RemotingContext remotingCtx, T request);
}
