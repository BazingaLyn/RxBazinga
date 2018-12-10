package org.lyncc.bazinga.rx.bazinga.future.async;

import java.util.Optional;

/**
 * @author liguolin
 * @create 2018-09-27 14:04
 **/
public interface AsyncCallback<T> {

    void onComplete(T value, Optional<Exception> ex);
}
