package cn.iamcrawler.crawlergoddess.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuliang on 2019/4/18.
 */
@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public ISqlInjector sqlInjector() {
        //逻辑已删除值(1)
        //否则为0  @TableLogic
        return new LogicSqlInjector();//逻辑删除
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        //@Version
        return new OptimisticLockerInterceptor();//乐观锁
    }

}
