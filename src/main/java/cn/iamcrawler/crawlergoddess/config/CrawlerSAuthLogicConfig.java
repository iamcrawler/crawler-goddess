package cn.iamcrawler.crawlergoddess.config;

import com.codingapi.txlcn.tm.support.restapi.auth.sauth.SAuthLogic;
import com.codingapi.txlcn.tm.support.restapi.auth.sauth.token.TokenStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuliang on 2019/4/19.
 */
@Configuration
public class CrawlerSAuthLogicConfig {

    //指定启动自定义的认证类
    @Bean
    public SAuthLogic sAuthLogic(TokenStorage tokenStorage){
       return new CrawlerSAuthLogic( tokenStorage);
    }

}
