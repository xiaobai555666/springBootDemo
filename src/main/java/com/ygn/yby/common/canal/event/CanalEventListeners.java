package com.ygn.yby.common.canal.event;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author duhanmin
 * @date 2018/3/19
 */
public interface CanalEventListeners {

    /**
     * run when event was fired
     *
     * @param eventType eventType
     * @param rowData rowData
     */
    void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData);

}
