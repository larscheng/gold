package com.study.zhengql.redisMq;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/7 15:47
 */
public class DelayMessageBody {

    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息id
     */
    private String id;
    /**
     * 消息延迟
     */
    private long delay;
    /**
     * 消息优先级
     */
    private int priority;
    /**
     * 消息存活时间
     */
    private int ttl;
    /**
     * 消息体，对应业务内容
     */
    private String body;
    /**
     * 创建时间，如果只有优先级没有延迟，可以设置创建时间为0
     * 用来消除时间的影响
     */
    private long createTime;
    /**
     * 消息状态（延迟-0；待发送-1；已发送-2；发送失败-3）
     */
    private int status;
}
