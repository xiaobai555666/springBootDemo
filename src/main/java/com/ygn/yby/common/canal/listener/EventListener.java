package com.ygn.yby.common.canal.listener;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.common.collect.ImmutableMap;
import com.ygn.yby.common.canal.annotation.CanalEventListener;
import com.ygn.yby.common.canal.annotation.ListenPoint;
import com.ygn.yby.common.canal.client.entity.EventEntry;
import com.ygn.yby.common.canal.constants.Constants;
import com.ygn.yby.common.canal.producer.KafkaProducer;
import com.ygn.yby.common.canal.util.EventUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author duhanmin
 * @date 2018/3/19
 */
@Slf4j
@CanalEventListener
public class EventListener {
    private static final String CANAL_CLIENT = "example";
    private static final String DB = "canal_tsdb";
    private static final String TABLES = "collect_ais";
    private static final Map TARGET= ImmutableMap.of("message_version","v1");

    @Autowired
    private KafkaProducer kafkaProducer;

    @Resource
    private Environment environment;

    /**
     * 根据业务库区域绑定查询对应的大数据CHANNEL code
     */
    private static final String CHANNEL = "EXAMPLE";

    @ListenPoint(destination = CANAL_CLIENT, schema = DB, table = {TABLES}, eventType = CanalEntry.EventType.INSERT)
    public void onInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        EventEntry eventEntry = EventUtils.of(CHANNEL,after(rowData), new JSONObject(TARGET));
        // boolean sendStatus = sedKafka(eventEntry);
        /**
         * 查询后端 区域和大数据code绑定表
         * 将数据分发
         */
        System.out.println(eventEntry);
    }

    @ListenPoint(destination = CANAL_CLIENT, schema = DB, table = {TABLES}, eventType = CanalEntry.EventType.UPDATE)
    public void onUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        EventEntry eventEntry = EventUtils.of(CHANNEL,before(rowData), new JSONObject(TARGET));
        /**
         * 查询后端 区域和大数据code绑定表
         * 将数据分发
         */
        System.out.println(eventEntry);
    }

    /**
     * 数据发送kafka
     * @param eventEntry
     * @return
     */
    private boolean sedKafka(EventEntry eventEntry) {
        return kafkaProducer.send(environment.getProperty("config.kafka.topic"),eventEntry.toString());
    }

    /**
     * tag：1 Insert
     * tag：2 Update
     * 改变之前的状态
     * @param rowData
     * @return
     *
     */
    private JSONObject before(CanalEntry.RowData rowData) {
        List<CanalEntry.Column> list = rowData.getBeforeColumnsList();
        JSONObject json = after(rowData);
        json.put(Constants.BEFORE,column(list));
        json.put(Constants.TAG,2);
        return json;
    }

    /**
     * tag：1 Insert
     * tag：2 Update
     * 改变或新增后的状态
     * @param rowData
     * @return
     */
    private JSONObject after(CanalEntry.RowData rowData) {
        List<CanalEntry.Column> list = rowData.getAfterColumnsList();
        JSONObject json = new JSONObject();
        json.put(Constants.AFTER,column(list));
        json.put(Constants.TAG,1);
        return json;
    }

    /**
     * canal数据转json
     * @param List
     * @return
     */
    private JSONObject column(List<CanalEntry.Column> List) {
        JSONObject json = new JSONObject();
        for (CanalEntry.Column entity:List){
            String key = entity.getName();
            String value = entity.getValue();
            if(!(key.equals("deleteFlag") && value.equals("1")))
                json.put(key,value);
        }
        return json;
    }
}
