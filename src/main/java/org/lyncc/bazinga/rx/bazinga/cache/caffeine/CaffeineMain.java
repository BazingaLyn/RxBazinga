package org.lyncc.bazinga.rx.bazinga.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.lyncc.bazinga.rx.bazinga.cache.model.User;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @author liguolin
 * @create 2018-09-28 15:10
 **/
public class CaffeineMain {


    private LoadingCache<String, User> graphs = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(5, MINUTES)
            .refreshAfterWrite(1, MINUTES)
            .build(key -> createExpensiveGraph(key));

    private User createExpensiveGraph(String key) {

        return null;
    }

    public static void main(String[] args) {

    }
}
