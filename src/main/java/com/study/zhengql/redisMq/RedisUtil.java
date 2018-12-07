package com.study.zhengql.redisMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author 应用三组
 * @version 1.0.1
 * @date 2017/11/29
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {

    @SuppressWarnings("rawtypes")

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public boolean remove(final String key) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        return !redisTemplate.hasKey(key);
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setPool(final String key, Object value, Long expireTime) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean zSetAdd(final String key, String value, Long score){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
            return operations.add(key, value, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean zSetDel(final String key, String... value){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
            return operations.remove(key,value)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Set<String> getRangeByScore(final String key, int startRange, int endRange, boolean orderByDesc){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
//            if (orderByDesc) {
//                return operations.reverseRangeByScore(key, startRange, endRange);
//            } else {
//                return operations.rangeByScore(key, startRange, endRange);
//            }
            Set set = operations.rangeByScore(key, startRange, endRange);
            Set set1 = operations.reverseRange(key, startRange, endRange);
            Set<ZSetOperations.TypedTuple> set2 = operations.reverseRangeWithScores(key, startRange, endRange);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getScore(final String key, String value){
        try {
            ZSetOperations operations = redisTemplate.opsForZSet();
            return operations.score(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>写入缓存
     * <p>将 key 的值设为 value，当且仅当 key 不存在
     * <p>若给定的 key 已经存在，则 SETNX 不做任何动作
     * <p>SETNX = SET if Not Exists
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean setNX(final String key, Object value, Long expireTime) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (!ops.setIfAbsent(key, value)) {
            return false;
        }
        if (expireTime != null) {
            if (!redisTemplate.expire(key, expireTime, TimeUnit.SECONDS)) {
                redisTemplate.delete(key);
                return false;
            }
        }
        return true;
    }

    /**
     * 通过key获取键到期的剩余时间(秒) -1, 如果key没有到期超时。-2, 如果键不存在。
     *
     * @param key
     * @return
     */
    public Long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }


    /**
     * list
     */

    public long countList(final String key) {
        if (key == null) {
            return 0L;
        }
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


    public Boolean rightPush(final String key,String... val) {
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.rightPush(key,val)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> range(final String key, long start, long end) {
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean removeListValue(final String key, long count, String value) {
        try {
            ListOperations operations = redisTemplate.opsForList();
            return operations.remove(key,count,value)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
