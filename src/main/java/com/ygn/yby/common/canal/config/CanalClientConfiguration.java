package com.ygn.yby.common.canal.config;


import com.ygn.yby.common.canal.client.CanalClient;
import com.ygn.yby.common.canal.client.SimpleCanalClient;
import com.ygn.yby.common.canal.client.transfer.MessageTransponders;
import com.ygn.yby.common.canal.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * @author duhanmin
 * @date 2018/3/19
 */
@Slf4j
public class CanalClientConfiguration {

    @Autowired
    private CanalConfig canalConfig;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public BeanUtil beanUtil() {
        return new BeanUtil();
    }

    @Bean
    private CanalClient canalClient() {
        CanalClient canalClient = new SimpleCanalClient(canalConfig, MessageTransponders.defaultMessageTransponder());
        canalClient.start();
        log.info("Starting canal client....");
        return canalClient;
    }
}
