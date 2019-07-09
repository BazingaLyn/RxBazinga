package org.lyncc.bazinga.rx.bazinga.spi.service;

import org.lyncc.bazinga.rx.bazinga.spi.SpiMeta;

@SpiMeta(name = "v1elasticService")
public class ElasticV1Service implements ElasticService {
    @Override
    public String elasticService(String value) {
        return "elasticsearch";
    }
}
