//package cn.iamcrawler.crawlergoddess.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
///**
// * Created by liuliang on 2019/3/21.
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//    @Value(value = "${spring.redis.host}")
//    private String host;
//    @Value(value = "${spring.redis.port}")
//    private int port;
//    @Value(value = "${spring.redis.timeout}")
//    private int timeout;
//
//    //缓存管理器（将springboot自带的缓存替换成Redis）
//    @Bean
//    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate){
//        RedisCacheManager cacheManager=new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(10000);
//        return cacheManager;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//
//        setSerializer(template);//设置序列化工具
//        template.afterPropertiesSet();
//        return template;
//    }
//    private void setSerializer(StringRedisTemplate template){
//        @SuppressWarnings({ "rawtypes", "unchecked" })
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }
//
//
//
//
//}
