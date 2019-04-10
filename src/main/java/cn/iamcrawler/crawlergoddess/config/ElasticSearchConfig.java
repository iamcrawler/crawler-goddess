package cn.iamcrawler.crawlergoddess.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by liuliang on 2019/4/8.
 */
@Configuration
public class ElasticSearchConfig {
    @PostConstruct
    void init() {
        //netty冲突的问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
