//package cn.iamcrawler.crawlergoddess.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.plugin.Interceptor;
//import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
//import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
////import com.baomidou.mybatisplus.MybatisConfiguration;
////import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
////import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
////import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
////import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
//
///**
// * Created by liuliang on 2018/11/1.
// */
//@Configuration
//@EnableConfigurationProperties({DataSourceProperties.class, MybatisProperties.class})
//public class DatabaseConfiguration {
//
//    private final DataSourceProperties dataSourceProperties;
//    private final MybatisProperties properties;
//    private final ResourceLoader resourceLoader;
//
//    @Autowired(required = false)
////    private Interceptor[] interceptors;
//    public DatabaseConfiguration(DataSourceProperties dataSourceProperties, MybatisProperties properties, ResourceLoader resourceLoader) {
//        this.dataSourceProperties = dataSourceProperties;
//        this.properties = properties;
//        this.resourceLoader = resourceLoader;
//    }
//
//    /**
//     * 注册数据源，使用阿里巴巴的一个实现类
//     *
//     * @return
//     */
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(dataSourceProperties.getUrl());
//        dataSource.setUsername(dataSourceProperties.getUsername());
//        dataSource.setPassword(dataSourceProperties.getPassword());
//        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
//        dataSource.setInitialSize(2);
//        dataSource.setMaxActive(20);
//        dataSource.setMinIdle(0);
//        dataSource.setMaxWait(20000);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setPoolPreparedStatements(false);
//        return dataSource;
//    }
//
//
//    /**
//     * 注册sqlSession
//     *
//     * @return
//     */
//    @Bean
//    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
//        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        mybatisSqlSessionFactoryBean.setDataSource(dataSource());
//        mybatisSqlSessionFactoryBean.setVfs(SpringBootVFS.class);
//        mybatisSqlSessionFactoryBean.setPlugins(getInterceptors());
//        //sqlsession实体类
//        if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
//            mybatisSqlSessionFactoryBean.setTypeAliasesPackage(properties.getTypeAliasesPackage());
//        }
//
//        //typeHandle包
//        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
//            mybatisSqlSessionFactoryBean.setTypeHandlersPackage(properties.getTypeHandlersPackage());
//        }
//
//
//        if (StringUtils.hasText(properties.getConfigLocation())) {
//            mybatisSqlSessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(properties.getConfigLocation()));
//        }
//
//        //扫描mapper.xml包
//        Resource[] resources = this.properties.resolveMapperLocations();
//        if (!ObjectUtils.isEmpty(resources)) {
//            mybatisSqlSessionFactoryBean.setMapperLocations(resources);
//        }
//
//
//        //扫描自定义的拦截器 ：如果要，则需要重新定义插件，否则total会失效
////        if(ObjectUtils.isEmpty(this.interceptors)){
////            mybatisSqlSessionFactoryBean.setPlugins(interceptors);
////        }
//        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
//        mybatisConfiguration.setMapUnderscoreToCamelCase(true);//开启mybatisPlus驼峰转换
//        mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
//        return mybatisSqlSessionFactoryBean;
//    }
//
//
//    @Bean
//    public Interceptor[] getInterceptors() {
//        List<Interceptor> interceptors = new ArrayList<Interceptor>();
//        Interceptor performanceInterceptor = new PerformanceInterceptor();
//        OptimisticLockerInterceptor optimisticLockerInterceptor = new OptimisticLockerInterceptor();
//        Properties performanceInterceptorProps = new Properties();
//        //调整最长的sql查询时间为10s
//        performanceInterceptorProps.setProperty("maxTime", "10000");
//        performanceInterceptorProps.setProperty("format", "true");
//        performanceInterceptor.setProperties(performanceInterceptorProps);
//        PaginationInterceptor pagination = new PaginationInterceptor();
//        pagination.setDialectType("mysql");
//        interceptors.add(pagination);
//        interceptors.add(performanceInterceptor);
//        interceptors.add(optimisticLockerInterceptor);
//        return interceptors.toArray(new Interceptor[]{});
//    }
//
//
//}
