package org.lyncc.bazinga.rx.bazinga.spi.service;

import org.lyncc.bazinga.rx.bazinga.spi.Spi;

@Spi(scope = Spi.Scope.SINGLETON)
public interface ElasticService {

    String elasticService(String value);
}
