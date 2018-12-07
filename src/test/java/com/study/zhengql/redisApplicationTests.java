package com.study.zhengql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisApplicationTests {

    @Test
    public void contextLoads() {
    }

//    private static final String ADDR = "127.0.0.1";
//    private static final int PORT = 6379;
//    private static JedisPool jedisPool = new JedisPool(ADDR, PORT);
//
//    public static Jedis getJedis() {
//        return jedisPool.getResource();
//    }
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    //生产者,生成5个订单放进去
    public void productionDelayMessage() {
        for (int i = 0; i < 5; i++) {
            //延迟3秒
            Calendar cal1 = Calendar.getInstance();
            int time =new Random().nextInt(100);
            System.out.println(time);
            cal1.add(Calendar.SECOND, time);
//            cal1.add(Calendar.SECOND, 3);
            int second3later = (int) (cal1.getTimeInMillis() / 1000);
            redisTemplate.opsForZSet().add("card1", "OID0000001"+i,second3later);
            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID0000001" + i);
        }
    }


    //消费者，取订单
    @Test
    public void consumerDelayMessage(){
        ZSetOperations operations = redisTemplate.opsForZSet();
        while(true){
            Set<ZSetOperations.TypedTuple> items = operations.rangeWithScores("card1", 0, -1);
            if(items == null || items.isEmpty()){
                System.out.println("当前没有等待的任务");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            for (ZSetOperations.TypedTuple item : items){
                Double score = item.getScore();
                Calendar cal = Calendar.getInstance();
                double nowSecond = (cal.getTimeInMillis() / 1000);
                if(nowSecond >= score){
                    String orderId = item.getValue().toString();
                    Long num = operations.remove("card1", orderId);
                    System.out.println("remove : num :"+num);
                    if( num != null && num>=0) {
                        System.out.println(new Date()+"   "+System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
                    }
                }
            }
        }
    }
}
