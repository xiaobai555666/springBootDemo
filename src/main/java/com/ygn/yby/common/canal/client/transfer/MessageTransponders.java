package com.ygn.yby.common.canal.client.transfer;

/**
 * @author duhanmin
 * @date 2018/3/23
 */
public class MessageTransponders {

    public static TransponderFactory defaultMessageTransponder() {
        return new DefaultTransponderFactory();
    }

}
