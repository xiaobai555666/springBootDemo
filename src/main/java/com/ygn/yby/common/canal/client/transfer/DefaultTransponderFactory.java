package com.ygn.yby.common.canal.client.transfer;

import com.alibaba.otter.canal.client.CanalConnector;
import com.ygn.yby.common.canal.client.ListenerPoint;
import com.ygn.yby.common.canal.config.CanalConfig;
import com.ygn.yby.common.canal.event.CanalEventListeners;

import java.util.List;
import java.util.Map;

/**
 * @author duhanmin
 * @date 2018/3/23
 */
public class DefaultTransponderFactory implements TransponderFactory {
    @Override
    public MessageTransponder newTransponder(CanalConnector connector, Map.Entry<String, CanalConfig.Instance> config, List<CanalEventListeners> listeners,
                                             List<ListenerPoint> annoListeners) {
        return new DefaultMessageTransponder(connector, config, listeners, annoListeners);
    }
}
