package cn.iamcrawler.crawlergoddess.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuliang on 2019/4/10.
 */
@Component
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private static RedisTemplate staticRedisTemplate;

    private static RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        RedisUtil.staticRedisTemplate = redisTemplate;
        staticRedisTemplate.setKeySerializer(new StringRedisSerializer());
        staticRedisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    private RedisUtil() {
    }

    static {
        if (null == redisUtil) {
            redisUtil = new RedisUtil();
        }
    }

    public static RedisUtil getInstance() {
        return redisUtil;
    }

    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60 * 60;            //一小时
    public final static int EXRP_DAY = 60 * 60 * 24;        //一天
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30;    //一个月


    public static String getItemStockKey(String itemId, String skuCode) {
        return "ItemSkuStock:" + itemId + "_" + skuCode;
    }

    /**
     * 设置 String
     * @param key
     * @param value
     */
    public synchronized static void set(String key, String value) {
        try {
            staticRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }


    public synchronized static void getset(String key, String value) {
        try {
            staticRedisTemplate.opsForValue().getAndSet(key, value);
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }


    /**
     * 设置 byte[]
     * @param key
     * @param value
     */
    public synchronized static void set(byte[] key, byte[] value) {
        try {
            staticRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }


    public synchronized static void setHashMap(String key, Map<String, String> map) {
        HashOperations hashOper = staticRedisTemplate.opsForHash();
        hashOper.putAll(key, map);
    }


    /**
     * 设置 key 过期时间
     * @param key
     * @param time
     */
    public synchronized static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                staticRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置 String 过期时间
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(String key, String value, int seconds) {
        try {
            staticRedisTemplate.opsForValue().set(key, value);
            staticRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Set keyex error : " + e);
        }
    }

    /**
     * 设置 byte[] 过期时间
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(byte[] key, byte[] value, int seconds) {
        try {
            staticRedisTemplate.opsForValue().set(key, value);
            staticRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 获取String值
     * @param key
     * @return value
     */
    public synchronized static String get(String key) {
        Object obj = staticRedisTemplate.opsForValue().get(key);
        return null == obj ? null : obj.toString();
    }

    /**
     * 获取byte[]值
     * @param key
     * @return value
     */
    public synchronized static byte[] get(byte[] key) {
        return (byte[]) staticRedisTemplate.opsForValue().get(key);

    }

    /**
     * 删除值
     * @param key
     */
    public synchronized static void remove(String key) {
        staticRedisTemplate.delete(key);
    }

    /**
     * 删除值
     * @param key
     */
    public synchronized static void remove(byte[] key) {
        staticRedisTemplate.delete(key);
    }

    /**
     * 从Redis中获取值，并重置有效期
     * @param key
     * @return
     */
    public static Object getRedisResetExpire(String key, long expireTime, TimeUnit timeUnit) {
        Object sessionObj = staticRedisTemplate.opsForValue().get(key);
        if (sessionObj != null) {
            //重置有效时间
            staticRedisTemplate.expire(key, expireTime, timeUnit);
        }
        return sessionObj;
    }

    /**
     * 根据表达式，获取所有键的集合
     * @param pattern 表达式格式，如：key_*
     * @return
     */
    public static Set<String> getRedisKeys(String pattern) {
        Set<String> keys = Sets.newConcurrentHashSet();
        Set<byte[]> byteKeys = staticRedisTemplate.getConnectionFactory().getConnection().keys(pattern.getBytes());
        Iterator<byte[]> it = byteKeys.iterator();
        while (it.hasNext()) {
            byte[] data = it.next();
            keys.add(new String(data, 0, data.length));
        }
        return keys;
    }

    public static long llen(String key) {
        Set<String> set = getRedisKeys(key);
        if (null == set) {
            return 0;
        }
        return set.size();
    }

    /**
     * 一次获取多个值
     * @param keys
     * @return
     */
    public static Collection<Object> getRedisMulti(String... keys) {
        if (keys == null && keys.length == 0) {
            Collections.emptySet();
        }
        Collection<Object> collection = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            collection.add(keys[i]);
        }
        return staticRedisTemplate.opsForValue().multiGet(collection);
    }

    /**
     * 根据Key获取已经保存到Redis中所有的值,Session级别
     * @param key 键
     * @return map数据
     */
    public static Map<String, String> getAllMap(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Set<Object> keys = staticRedisTemplate.keys("*_" + key);
        Map<String, String> map = new ConcurrentHashMap<>();
        if (keys != null && keys.size() > 0) {
            for (Object hashKey : keys) {
                String text = getValue(String.valueOf(hashKey), key);
                map.put(String.valueOf(hashKey), text);
            }
        }
        return map;
    }

    /**
     * 根据Key和HashKey获取一个值，返回String
     * @param key     键
     * @param hashKey has键
     * @return String 缓存数据
     */
    public static String getValue(String key, String hashKey) {
        Object obj = staticRedisTemplate.opsForHash().get(key, hashKey);
        return obj == null ? null : obj.toString();
    }

    public static Map<String, String> getHashMap(String hashKey) {
        if (ObjectUtils.isNullOrEmpty(hashKey)) {
            return new HashMap();
        }
        return staticRedisTemplate.boundHashOps(hashKey).entries();
    }


    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return staticRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @return
     */
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return staticRedisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 删除hash表中的值
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hdel(String key, Object... item) {
        staticRedisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 移除N个值为value
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public static long lRem(String key, long count, Object value) {
        try {
            Long remove = staticRedisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            logger.error("failed to remove data,reason:{}", e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public static long srem(String key, Object... values) {
        try {
            Long count = staticRedisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取list缓存的内容
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public static List<Object> lRange(String key, long start, long end) {
        try {
            return staticRedisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public static long lGetListSize(String key) {
        try {
            return staticRedisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(String key, Object value) {
        try {
            staticRedisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(String key, Object value, long time) {
        try {
            staticRedisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(String key, List<Object> value) {
        try {
            staticRedisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(String key, List<Object> value, long time) {
        try {
            staticRedisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从右侧弹出
     * @param key 键
     * @return
     */
    public static boolean rpop(String key) {
        try {
            staticRedisTemplate.opsForList().rightPop(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从左侧弹出
     * @param key 键
     * @return
     */
    public static boolean lpop(String key) {
        try {
            staticRedisTemplate.opsForList().leftPop(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 在Redis中放入值，并指定有效时间
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public static void setRedis(String key, Object value, long expire, TimeUnit timeUnit) {
        staticRedisTemplate.opsForValue().set(key, value);
        if (expire > 0) {
            staticRedisTemplate.expire(key, expire, timeUnit);
        }
    }

    /**
     * 模糊查询
     * @param pattern
     * @return
     */
    public static Set<String> getTemplateValue(String pattern) {
        return staticRedisTemplate.keys("*" + pattern + "*");
    }


}
