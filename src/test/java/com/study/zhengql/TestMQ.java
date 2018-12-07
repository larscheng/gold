package com.study.zhengql;

import com.alibaba.fastjson.JSONObject;
import com.study.zhengql.redisMq.Message;
import com.study.zhengql.redisMq.RedisMQ;
import com.study.zhengql.redisMq.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @decription TestMQ
 * <p>测试</p>
 * @author Yampery
 * @date 2018/2/9 18:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMQ {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private RedisMQ redisMQ;
//    @Value("${mq.queue.first}")
    private String MQ_QUEUE_FIRST="queue:1";
    private static final String MSG_POOL = "Message:Pool:";
    String seqId=null;
    @Test
    public void testMq() {

        JSONObject jObj = new JSONObject();
        jObj.put("msg", "这是一条短信");

        seqId = UUID.randomUUID().toString();
        System.out.println(seqId);
        // 将有效信息放入消息队列和消息池中
        Message message = new Message();
        message.setBody(jObj.toJSONString());
        // 可以添加延迟配置
        message.setDelay(20000);
        message.setTopic("SMS");
        message.setCreateTime(System.currentTimeMillis());
        message.setId(seqId);
        message.setTtl(25);
        message.setStatus(0);
        message.setPriority(0);
        redisMQ.addMsgPool(message);
        redisMQ.enMessage(MQ_QUEUE_FIRST,
                message.getCreateTime() + message.getDelay() + message.getPriority(), message.getId());
        System.out.println("-----------success");

    }


    @Test
    public void testPool() {
        redisUtil.set("zql","aaa",10L);
        String a = (String) redisUtil.get(MSG_POOL+"29bc47ac-5ff5-4543-b540-30e3d5336d9e");
        System.out.println("-----------success");

    }


}
