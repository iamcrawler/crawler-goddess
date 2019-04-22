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
//@EnableConfigurationProperties({DataSourceProperties.class, MybatisProperties.class})
public class MyBatisPlusConfiguration {

//
//    private final DataSourceProperties dataSourceProperties;
//    private final MybatisProperties properties;
//    private final ResourceLoader resourceLoader;
//
//    @Autowired(required = false)
////    private Interceptor[] interceptors;
//    public MyBatisPlusConfiguration(DataSourceProperties dataSourceProperties, MybatisProperties properties, ResourceLoader resourceLoader) {
//        this.dataSourceProperties = dataSourceProperties;
//        this.properties = properties;
//        this.resourceLoader = resourceLoader;
//    }
//
//
//
//
//
//    /**
//     * 注册数据源，使用阿里巴巴的一个实现类
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
//        dataSource.setMaxWait(60000);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setPoolPreparedStatements(false);
//        return dataSource;
//    }
//
//
//
//    /**
//     * 注册sqlSession
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
//
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
//        pagination.setDialectType("postgres");
//        interceptors.add(pagination);
//        interceptors.add(performanceInterceptor);
//        interceptors.add(optimisticLockerInterceptor);
//        return interceptors.toArray(new Interceptor[]{});
//    }





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
