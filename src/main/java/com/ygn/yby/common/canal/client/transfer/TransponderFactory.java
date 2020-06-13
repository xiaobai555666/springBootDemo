package com.ygn.yby.common.canal.client.transfer;

import com.alibaba.otter.canal.client.CanalConnector;
import com.ygn.yby.common.canal.client.ListenerPoint;
import com.ygn.yby.common.canal.config.CanalConfig;
import com.ygn.yby.common.canal.event.CanalEventListeners;

import java.util.List;
import java.util.Map;

/**
 * TransponderFactory
 *
 * @author duhanmin
 * @date 2018/3/23
 */
public interface TransponderFactory {

    /**
     * @param connector connector
     * @param config config
     * @param listeners listeners
     * @param annoListeners annoListeners
     * @return MessageTransponder
     */
    MessageTransponder newTransponder(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config, List<CanalEventListeners> listeners,
                                      List<ListenerPoint> annoListeners);
}
