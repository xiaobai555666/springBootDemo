package com.ygn.yby.config;

/**
 * @author     ：yby
 * @date       ：2020/1/12 13:11
 * @description:  多数据源
 */
 
public class DataSourceContextHolder {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();
    /**
     * 设置线程持有的DataSource, 底层以map形式呈现, key为当前线程
     *
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        THREAD_LOCAL.set(dataSource);
    }

    /**
     * 获取线程持有的当前数据源
     *
     * @return
     */
    public static String getDataSource() {
        return THREAD_LOCAL.get();
    }

    /**
     * 清除数据源
     */
    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
