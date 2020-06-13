/**
 * *@Copyright: 2019 www.yagena.com.cn inc . All rights reserved.
 * *注意：本内容仅限于雅格纳内部传阅，禁止外泄以及用于其他的商业目
 */
package com.ygn.yby.common.canal.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ygn.yby.common.canal.client.entity.EventEntry;
import com.ygn.yby.common.canal.constants.Constants;

import java.util.Arrays;

public class EventUtils {

    public static EventEntry of(String eventChannel, JSONObject eventData, JSONObject eventTarget) {
        EventEntry eventEntry = of(eventChannel,eventData);
        eventEntry.setEventTarget(eventTarget);
        return eventEntry;
    }

    public static EventEntry of(String eventChannel, JSONObject eventData) {
        EventEntry eventEntry = of(eventChannel);
        eventEntry.setEventData(eventData);
        return eventEntry;
    }

    private static EventEntry of(String eventChannel) {
        EventEntry eventEntry = new EventEntry();
        eventEntry.setEventId(StrUtil.uuid());
        eventEntry.setEventTime(System.currentTimeMillis());
        eventEntry.setEventChannel(eventChannel);
        return eventEntry;
    }

    /**
     * 构造写数据库数据
     * @param t
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> JSONArray sendData(T... t) {
        return new JSONArray(Arrays.asList(t));
    }

    /**
     * 讲通道json转为对象
     *
     * @param json
     * @return
     */
    public static EventEntry classOf2Bean(String json) {
        return JSONUtils.json2Bean(json, EventEntry.class);
    }

    /**
     * @param jsonObject
     * @param key
     * @param value
     * @return
     */
    public static JSONObject setSinkData(JSONObject jsonObject, String key, Object value) {
        JSONObject jsonObjectTmp = new JSONObject();
        jsonObjectTmp.put(key, value);
        JSONObject outData = jsonObject.getJSONObject(Constants.MQ_OUT_DATA);
        try {
            JSONUtils.jsonUnion(jsonObjectTmp, outData);
        } catch (Exception e) {
        } finally {
            jsonObject.put(Constants.MQ_OUT_DATA, jsonObjectTmp);
        }
        jsonObject.put(Constants.WRITE_MARK, Boolean.TRUE);
        return jsonObject;
    }

    /**
     * 不往下写标记日志
     *
     * @param jsonObject
     * @return
     */
    public static JSONObject setSinkData(JSONObject jsonObject) {
        jsonObject.put(Constants.MQ_IN_MARK, "非标准流程，以计算完成且落库，数据不往下传递了！！！");
        jsonObject.put(Constants.WRITE_MARK, Boolean.FALSE);
        return jsonObject;
    }

}
