package com.ygn.yby.common.canal.client.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息队列通道数据协议
 * @author du
 * @date 2018-08-17
 */
@Data
public class EventEntry implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    /**
     * 从源头标记id，实现全程可追溯
     */
    private String eventId;

    /**
     * 标记topic或业务code，实现业务隔离
     */
    private String eventChannel;

    /**
     * 发送端标记时间
     */
    private Long eventTime;

    /**
     * 标记各种自定义添加的要素
     */
    private JSONObject eventTarget;

    /**
     * 只携带数据
     */
    private JSONObject eventData;

    /**
     * 返回数据
     */
    private String eventNotifyData;

    /**
     * 返回标识数据
     */
    private Boolean eventNotifyNeed;

    /**
     * 回调消息队列
     */
    private String eventNotifyChannel;

    /**
     * 埋点
     */
    private JSONObject eventAnchor;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
