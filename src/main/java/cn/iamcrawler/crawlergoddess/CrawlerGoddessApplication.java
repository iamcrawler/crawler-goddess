package cn.iamcrawler.crawlergoddess;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagerServer
@MapperScan("cn.iamcrawler.crawlergoddess.mapper")
@ComponentScan("cn.iamcrawler.crawlergoddess,cn.iamcrawler.crawler_common.feign")
public class CrawlerGoddessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerGoddessApplication.class, args);
    }
}
