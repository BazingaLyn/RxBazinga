package org.lyncc.bazinga.rx.bazinga.spi;

import org.lyncc.bazinga.rx.bazinga.spi.service.ElasticService;

public class SpiExtensionLoaderTest {

    public static void main(String[] args) {

        ElasticService v1elasticService = ExtensionLoader.getExtensionLoader(ElasticService.class).getExtension("v1elasticService");
        String s = v1elasticService.elasticService("hello");
        System.out.println(s);
    }


}
