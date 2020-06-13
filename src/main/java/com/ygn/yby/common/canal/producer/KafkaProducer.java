/**
 * *@Copyright: 2019 www.pingshiedu.com inc . All rights reserved.
 * *注意：本内容仅限于雅格纳内部传阅，禁止外泄以及用于其他的商业目
 */
package com.ygn.yby.common.canal.producer;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * kafka生产
 * *@Author duhanmin
 * *@Date 2019/11/18 10:21
 */
@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发数据，带随机分发数据功能
     * @param topic
     * @param msg
     */
    public boolean send(String topic,String msg) {
        try {
            String random = System.currentTimeMillis()+""+ RandomUtil.randomInt(1,100);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic,random, msg);
            kafkaTemplate.send(producerRecord);
            kafkaTemplate.flush();
            return true;
        }catch (Exception e){
            log.error("发送数据失败topic：{}，msg：{}。",topic,msg,e);
        }
        return false;
    }
}

