package cn.iamcrawler.crawlergoddess;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagerServer
@MapperScan("cn.iamcrawler.crawlergoddess.mapper")
public class CrawlerGoddessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerGoddessApplication.class, args);
    }
}
