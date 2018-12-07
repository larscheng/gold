package com.study.zhengql.redisMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @decription JedisUtils
 * <p>redis操作工具</p>
 * @author Yampery
 * @date 2018/2/9 12:53
 */
@Component
public class JedisUtils {

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 获取值
     * @param key
     * @param defaultVal
     * @return
     */
    public String get(String key, String defaultVal) {
        try {
            String val = (String)redisUtil.get(key);
            return val == null ? defaultVal : val;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVal;
    }

    /**
     * 设置kv
     * @param key
     * @param val
     * @param seconds 有效期（秒）
     * @return
     */
    public boolean setex(String key, String val, long seconds) {
        try {
            return redisUtil.set(key,val,seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public boolean del(String key) {
        try {
            return redisUtil.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 向zset添加元素
     * @param key
     * @param val
     * @param score
     * @return
     */
    public boolean zadd(String key, long score, String val) {
        Jedis jedis = null;
        try {
            return redisUtil.zSetAdd(key,val,score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除zset元素
     * @param key
     * @param val
     * @return
     */
    public boolean zdel(String key, String... val) {
        try {
            return redisUtil.zSetDel(key,val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取优先队列元素
     * @param key
     * @param startRange
     * @param endRange
     * @param orderByDesc 是否降序
     * @return
     */
    public Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc) {
        try {
            return redisUtil.getRangeByScore(key, startRange, endRange, orderByDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取评分
     * @param key
     * @param member
     * @return
     */
    public Double getScore(String key, String member) {
        try {
            return redisUtil.getScore(key, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        /**
         * 获取list长度
         * @param key
         * @return
         */
    public long countList(String key) {
        if (key == null) {
            return 0;
        }
        Jedis jedis = null;
        try {
            return redisUtil.countList(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 添加元素到list（使用右进）
     * @param key
     * @param val
     * @return
     */
    public boolean insertList(String key, String val) {
        if (key == null || val == null) {
            return false;
        }
        try {
            return redisUtil.rightPush(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取list元素（采用左出方式）
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> rangeList(String key, long start, long end) {
        if (key == null || key.equals("")) {
            return null;
        }
        try {
            return redisUtil.range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除list数据
     * @param key
     * @param count
     * @param value
     * @return
     */
    public boolean removeListValue(String key, long count, String value) {
        try {
            return redisUtil.removeListValue(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int removeListValue(String key, long count, List<String> values) {
        int result = 0;
        if (values != null && values.size() > 0) {
            for (String value : values) {
                if (removeListValue(key, count, value)) {
                    result++;
                }
            }
        }
        return result;
    }

    public int removeListValue(String key, List<String> values) {
        return removeListValue(key, 1, values);
    }

}
