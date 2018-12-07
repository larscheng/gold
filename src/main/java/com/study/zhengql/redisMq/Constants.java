package com.study.zhengql.redisMq;

/**
 * Created by Jason on 2016/11/7.
 */
public class Constants {

    public static String CACHE_KEY_PREFIX = "reids_";

    /**盐*/
    public static final String SALT = "senthink2017";
    //region 缓存
    public static final String EHCACHE_WEB_SOCKET = "WEB_SOCKET";
    //endregion

    /**
     * 一天的毫秒数
     */
    public static final Long ONE_DAY_TIME_MILLIS = 86400000L;


    //region 租车相关
    /** 圆形 */
    public static final String CIRCULAR = "circle";

    /** 矩形 */
    public static final String RECTANGLE = "rectangle";

    /** 多边形 */
    public static final String POLYGON = "polygon";

    /** 用户周边n米内停车点，单位米 */
    public static final Integer PARKING_SEARCH_DISTANCE = 500;

    /** 用户周边n米内停车点，单位米 */
    public static final Integer PARKING_PERSON_DISTANCE = 100;

    /** 解锁车辆人与车最大距离，单位米 */
    public static final Integer USER_VEHICLE_DISTANCE = 100;

    /** 还车时车距停车点边界最大距离，单位米 */
    public static final Integer RETURN_PARKING_EDGE_DISTANCE = 10;

    /** 车辆低电量提醒电量 */
    public static final Integer LOW_ELECTRICITY = 60;

    /** 车辆禁用电量 */
    public static final Integer DISABLED_ELECTRICITY = 50;

    /** 骑行数据 redis key 前缀 */
    public static final String RIDING_PREFIX = "RIDING_";

    /** 用户骑行数据 redis key 前缀 */
    public static final String USER_RIDING_PREFIX = "RIDING_USER_";

    /** 用户解锁车辆 */
    public static final String USER_UNLOCK = "USER_UNLOCK_";

    /** 用户上锁车辆 */
    public static final String USER_LOCK = "USER_LOCK_";

    /** 用于限制用户频繁发送开启寻车铃的redis key前缀 */
    public static final String RINGING_PREFIX = "RING_";

    /** 寻车铃调用时间限制默认值，默认为10s */
    public static final Long RING_EXPIRE_TIME = 10L;

    /** DCU不存在，默认值“-1” */
    public static final String DEFAULT_DCU_NO = "-1";

    /**车辆BMS的故障：单体过压保护、整组过压保护、充电低温保护、放电低温保护、充电过流保护、软件锁定MOS 发生时车辆仍可被用户租用*/
    public static final String CAR_AVAILABLE_FAULT_CODE = "0,2,5,7,8,12";

    //endregion


    //region 交换机、队列配置
    //MQ直连交换机
    public static final String D_E_SINGAPORE_LEASE = "d.e.singapore.lease";
    //任务队列
    public static final String Q_SINGAPORE_LEASE_SCHEDULE = "q.singapore.lease.schedule";
    //延时队列，车辆超过30s未开锁，自动关闭订单
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE1 = "q.d.l.singapore.lease.schedule1";
    //延时队列，订单超过24小时未结束
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE2 = "q.d.l.singapore.lease.schedule2";
    //延时队列，用户临时停车达到10min未重新解锁或还车
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE3 = "q.d.l.singapore.lease.schedule3";
    //延时队列
    public static final String Q_D_L_SINGAPORE_LEASE_SCHEDULE4 = "q.d.l.singapore.lease.schedule4";
    //endregion



    //数字 3、4 开始的是 vcuNo；数字 5，6 开始的是 rcuNo
    public static final String VCU_NO_PREFIX3 = "3";
    public static final String VCU_NO_PREFIX4 = "4";
    public static final String RCU_NO_PREFIX5 = "5";
    public static final String RCU_NO_PREFIX6 = "6";

    public static final String QIQ_SERVICE_PHONE = "+86111111111";

    public static final Long LAST_ORDER_TIME = 5184000000L;

    
    /**
     * 车辆编号正则
     * 8位纯数字，可以从0开始
     */
    public static final String VEHICLE_NO_FORMAT = "^[0-9]\\d{7}$";
    public static final String VCU_NO_FORMAT = "^[0-9]\\d{7}$";


    /*-------------------------------------字符串国际化------start-------------------------------------------------*/


    //region Excle相关
     /**押金交易流水、导出时间:、导出数量*/
    public static final String EXPORT_STATISTICS="EXPORT_STATISTICS";

    /**业务明细表开始*/
    public static final String EXPORT_START="EXPORT_START";

    /**业务明细表结束*/
    public static final String EXPORT_END="EXPORT_END";

    /**订单数据表表头*/
    public static final String EXPORT_TITLES_ORDER = "EXPORT_TITLES_ORDER";

    /**反馈数据表表头*/
    public static final String FEEDBACK_TITLE = "FEEDBACK_TITLE";

    /**退款数据表表头*/
    public static final String REFUND_TITLE = "REFUND_TITLE";
    //regionEnd


    /**车状态错误*/
    public static final String DISPATCH_STATE = "vehicle states error";

    /**车掉线*/
    public static final String DISPATCH_OFFLINE = "Car dropped";

    /**已满*/
    public static final String DISPATCH_FULL = "full";

    /**保存失败*/
    public static final String DISPATCH_SAVE = "save failed";

    //region APP租车中提示信息
    /**超出运营范围*/
    public static final String OUT_OF_SCOPE_OF_OPERATION = "OUT_OF_SCOPE_OF_OPERATION";

    /**电量不足，请尽快还车*/
    public static final String LOW_BATTERY = "LOW_BATTERY";

    /**剩余电量*/
    public static final String REMAINING_BATTERY = "REMAINING_BATTERY";

    /**骑车或者还车*/
    public static final String CYCLING_OR_RETURNING = "CYCLING_OR_RETURNING";
    //regionEnd

    /*-------------------------------------字符串国际化------end-------------------------------------------------*/
    public static final String DEFAULT_SERIALNUMBER = "00";
}
