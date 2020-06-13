package com.ygn.yby.common.canal.constants;

public class Constants {

	/**
	 * 标识常量
	 */
	public static final String  VACANCY = "vacancy";
	public static final String  MYSQL = "mysql";
	public static final String  HBASE = "hbase";
	public static final String  REDIS = "redis";
	public static final String  DEFAULT_RETURN = "-";
	public static final String HTTP_SCHEME = "http";
	public static final String RECORDER_ENABLE = "recorder_enable";
	public static final String DB = "ygn_bigdata";
	public static final String DB_CONF_KAFKA_OFFSET = DB + ".conf_kafka_offset";
	public static final String DB_MONI_EFFICIENCY = DB + ".moni_efficiency";
	public static final String EVENT_ID = "event_id";


	/**
	 * 消息队列相关
	 *
	 */
	public static final String  KAFKA = "kafka";
	public static final String  BOOTSTRAP_SERVERS = "bootstrap.servers";
	public static final String  GROUP_ID = "group_id";
	public static final String  MQ_TOPICS = "mq_topics";
	public static final String  MQ_IN_DATA = "in_data";
	public static final String  MQ_OUT_DATA = "out_data";
	public static final String  MQ_TOPIC = "topic";
	public static final String  MQ_NOTIFY_TOPIC = "notify_topic";
	public static final String  MQ_TOPIC_WINDOW = "topic_window";
	public static final String  MQ_GROUP_ID = "groupId";
	public static final String  MQ_PARTITION = "partition";
	public static final String  MQ_OFFSET = "offset";
	public static final String  MQ_TIMESTAMP = "timestamp";
	public static final String  SAVE_DB_TAG = "save_db_tag";
	public static final String  KAFKA_LATEST = "latest";
	public static final String  KAFKA_EARLIEST = "earliest";

	/**
	 * ElasticSearch
	 */
	public static final String  ELASTIC_SEARCH = "elastic_search";
	/**
	 * 新链路标识
	 */
	public static final String MQ_IN_MARK = "mark";
	public static final String WRITE_MARK = "write_mark";

	/**
	 * binlog
	 */
	public static final String BEFORE = "before";
	public static final String AFTER = "after";
	public static final String TAG = "tag";

	public static final String DATE_FORMAT_MONTH = "yyyy-MM";
	public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSS";

	/**
	 * 业务上的
	 */
	public static final String SUPERVISE_AREA_MARK_CONFIG = "supervise:area_mark_config:{}:{}";
	public static final String SUPERVISE_NOTIFY = "supervise:notify:{}";

	/**
	 *
	 *     字段名	                解释	                          打点状态	打点个数
	 * 1	event_id	            该条数据的唯一标识（eventId）	    1	    1
	 * 2	job_name	            当前任务名称（统一用groupId）     1	    1
	 * 3	source_class	        入口class名字	                1	    1
	 * 4	business_tag	        业务标识（2期即topic）	        4	    1
	 * 5	phases_tab	            数据4个状态中的一个(1,2,3)      1,2,3 	3
	 * 6	host_name	            当前数据所在节点名称	            4	    1
	 * 7	address	                当前数据所在节点ip	            4	    1
	 * 8	start_time	            数据发送时间	                    1	    1
	 * 9	reach_time	            数据到达时间	                    2	    1
	 * 10	process_time	        开始处理时间	                    3	    1
	 * 11	end_time	            理结束时间	                    4	    1
	 * 12	start_2_reach_time      队列时间间隔	                    2	    1
	 * 13	reach_2_process_time    计算时间间隔        	            3	    1
	 * 14	process_2_end_time	    落库时间间隔	                    4	    1
	 *                                                          合计打点个数:16
	 *
	 * 另：提供公共字段order_no，name，person_id，fv_id（order_no可能缺省）
	 *                                                          合计打点个数:20
	 */
	public static final String  ANCHOR_EVENT_ID = "event_id";
	public static final String  ANCHOR_JOB_NAME = "job_name";
	public static final String  ANCHOR_KAFKA_NAME = "kafka_timestamp";
	public static final String  ANCHOR_SOURCE_CLASS = "source_class";
	public static final String  ANCHOR_BUSINESS_TAG = "business_tag";
	public static final String  ANCHOR_PHASES_TAB = "phases_tab";
	public static final String  ANCHOR_HOST_NAME = "host_name";
	public static final String  ANCHOR_ADDRESS = "address";
	public static final String  ANCHOR_START_TIME = "start_time";
	public static final String  ANCHOR_REACH_TIME = "reach_time";
	public static final String  ANCHOR_EVENT_TARGET = "event_target";
	public static final String  ANCHOR_PROCESS_TIME = "process_time";
	public static final String  ANCHOR_END_TIME = "end_time";
	public static final String  ANCHOR_CALLBACK_TIME = "callback_time";
	public static final String  ANCHOR_START_2_REACH_TIME = "start2reach_time";
	public static final String  ANCHOR_REACH_2_PROCESS_TIME = "reach2process_time";
	public static final String  ANCHOR_PROCESS_2_END_TIME = "process2end_time";
	public static final String  ANCHOR_END_2_CALLBACK_TIME = "end2callback_time";

}
