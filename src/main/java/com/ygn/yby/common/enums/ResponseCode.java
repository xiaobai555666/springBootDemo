package com.ygn.yby.common.enums;

import cn.hutool.core.util.StrUtil;

/**
 *
 * description: 相应code码定义
 *
 * @auther: yby
 * @date: 2018/12/26 14:01
 */
public enum ResponseCode {
	/**
	 * 请求成功
	 */
	SUCCESS("200", "SUCCESS", "成功", "成功", 0),

	/**
	 * 请求失败
	 */
	FAIL("406", "FAIL", "失败", "系统错误，请联系管理员", 0),

	/**
	 * 请求URL不存在
	 */
	FAIL_404("404", "REQUEST_URL_NOT_EXIST", "请求路径不存在", "请求路径不存在", 0),

	/**
	 * 请求方式不支持
	 */
	FAIL_HTTP_METHOD_NOT_SUPPORT("405", "REQUEST_METHOD_NOT_SUPPORT", "请求方式不支持", "请求方式不支持", 0),

	/**
	 * redis连接异常
	 */
	SERVER_BUSY_REDIS("408", "SERVER_BUSY_REDIS", "redis连接超时", "服务器繁忙，请稍后重试", 0),


	/**
	 * 服务器错误
	 */
	ERROR("500", "ERROR", "错误", "系统异常，请联系管理员", 0),

	/**
	 * 找不到资源或数据为空
	 */
	NOT_FOUND_OR_NULL_DATA_EXCEPTION("204", "NOT_FOUND_OR_NULL_DATA_EXCEPTION", "找不到资源或数据为空, 主要可能数据库不存在数据或标记为删除", "数据不存在或被删除，请刷新重试" , 2),

	/**
	 * 请求access_token失效
	 */
	PUBLIC_ACCESS_TOKEN_INVALID_ERROR("401", "PUBLIC_ACCESS_TOKEN_INVALID_ERROR", "请求access_token失效", "登录失效，请重新登录" , 2),


	/** ========== 公共的应用级错误码 ========== **/
	/**
	 * 用户不存在
	 */
	USER_NOT_EXIST("101", "USER_NOT_EXIST", "用户不存在", "用户账号不存在或已删除" , 2),

	/**
	 * 操作频繁,请等待
	 */
	OPERATION_FREQUENT_ERROR("102", "OPERATION_FREQUENT_ERROR", "操作频繁,请等待", "用户不存在或已经删除，请联系管理员" , 2),

	/**
	 * 保存异常
	 */
	ERROR_SAVE_EXCEPTION("103", "ERROR_SAVE_EXCEPTION", "保存异常", "保存异常，请重试" , 2),

	/**
	 * 更新异常
	 */
	ERROR_UPDATE_EXCEPTION("104", "ERROR_UPDATE_EXCEPTION", "更新异常", "更新异常，请重试" , 2),

	/**
	 * 存在子节点信息，不能删除
	 */
	ERROR_EXIST_CHILD_NODE_INFO("105", "ERROR_EXIST_CHILD_NODE_INFO", "存在子节点信息，不能删除", "存在部门信息，不允许删除", 2),


	/**
	 * 类型关联信息，不能删除
	 */
	ERROR_OIL_TYPE_RELATED_INFO("106", "ERROR_OIL_TYPE_RELATED_INFO", "类型关联信息，不能删除", "存在关联的滑油信息，不允许删除" , 2),


	/**
	 * 附件上传失败
	 */
	ERROR_ATTACHMENT_UPLOAD_FAIL("107", "ERROR_ATTACHMENT_UPLOAD_FAIL", "附件上传失败", "附件上传失败，请重试" , 2),

	/**
	 * 英文名称无效
	 */
	ERROR_ENGLISH_INVALID("108", "ERROR_ENGLISH_INVALID", "无效英文", "无效英文", 2),

	/**
	 * 邮政编码错误
	 */
	ERROR_POST_CODE_INVALID("109", "ERROR_POST_CODE_INVALID","邮政编码不合法", "邮政编码不合法" , 2),

	/**
	 * 手机号码错误
	 */
	ERROR_MOBILE_INVALID("110", "ERROR_MOBILE_INVALID", "手机号码不合法", "手机号码不合法" , 2),

	/**
	 * 邮箱错误
	 */
	ERROR_EMAIL_INVALID("111", "ERROR_EMAIL_INVALID", "邮箱不合法", "邮箱不合法" , 2),

	/**
	 * 单价不合法
	 */
	ERROR_PRICE_INVALID("112", "ERROR_PRICE_INVALID", "单价不合法", "单价不合法" , 2),

	/**
	 * 型号无效
	 */
	ERROR_MODEL_INVALID("113", "ERROR_MODEL_INVALID", "型号不合法", "型号不合法" , 2),

	/**
	 * 日期不合法
	 */
	ERROR_DATE_INVALID("114", "ERROR_DATE_INVALID", "日期不合法", "日期不合法" , 2),

	/**
	 * 编号不合法
	 */
	ERROR_NUM_INVALID("115", "ERROR_NUM_INVALID", "编号不合法", "编号不合法" , 2),

	/**
	 * 上传附件大小超出限制
	 */
	ERROR_ATTACHMENT_SIZE_LIMIT_OVER_FAIL("116", "ERROR_ATTACHMENT_SIZE_LIMIT_OVER_FAIL", "上传附件大小超出限制", "上传附件大小超出20M" , 2),

	/**
	 * 删除异常
	 */
	ERROR_DELETE_EXCEPTION("117", "ERROR_DELETE_EXCEPTION", "删除异常", "数据异常，请联系管理员" , 2),

	/**
	 * 表不存在
	 */
	ERROR_TABLE_NOT_EXIST("118", "ERROR_TABLE_NOT_EXIST", "数据表不存在", "数据异常，请联系管理员" , 2),


	/** ========== 系统错误码10001开始 ========== **/
	/**
	 * 系统错误
	 */
	ERROR_SYSTEM("10001", "ERROR_SYSTEM", "系统异常", "数据异常，请联系管理员" , 1),

	/**
	 * 请求响应为空
	 */
	NULL_SYSTEM_RESPONSE_PARAM("10002", "NULL_SYSTEM_RESPONSE_PARAM", "请求响应为空！", "数据异常，请联系管理员" , 1),

	/**
	 * 请求参数有误
	 */
	ERROR_SYSTEM_REQUEST_PARAM("10003", "ERROR_SYSTEM_REQUEST_PARAM", "请求参数错误!", "数据异常，请联系管理员" , 1),

	/**
	 * 请求参数不全
	 */
	ERROR_SYSTEM_REQUEST_PARAM_NOT_FULL("10004", "ERROR_SYSTEM_REQUEST_PARAM_NOT_FULL", "请求参数不全!", "数据异常，请联系管理员" , 1),

	/**
	 * 响应参数错误
	 */
	ERROR_SYSTEM_RESPONSE_PARAM("10005", "ERROR_SYSTEM_RESPONSE_PARAM", "响应参数错误!", "数据异常，请联系管理员" , 1),

	/**
	 * HTTP请求出现异常
	 */
	ERROR_HTTP_RESPONSE_EXCEPTION("10006", "ERROR_HTTP_RESPONSE_EXCEPTION", "HTTP请求出现异常!", "数据异常，请联系管理员" , 1),

	/**
	 * 归属异常
	 */
	ERROR_INFO_BELONG("10007", "ERROR_INFO_BELONG", "发生错误,可能归属有问题", "数据异常，请联系管理员" , 1),

	/**
	 * 附件不存在或已失效
	 */
	ERROR_ATTACHMENT_INVALID("10008", "ERROR_ATTACHMENT_INVALID", "附件不存在或已失效", "附件不存在或已失效" , 1),

	/**
	 * 导出无数据
	 */
	ERROR_EXPORT_SIZE_IS_NULL("10009", "ERROR_EXPORT_SIZE_IS_NULL", "暂无数据，导出失败", "暂无数据，导出失败" , 1),

	/** ========== 用户账号模块20001开始 ========== **/

	/**
	 * 用户未登录
	 */
	USER_NO_LOGIN("20001", "USER_NO_LOGIN", "用户未登录", "用户未登录" , 2),

	/**
	 * 用户登录密码错误
	 */
	ERROR_USER_LOGIN_PASSWORD("20002", "ERROR_USER_LOGIN_PASSWORD", "用户登录密码错误", "用户登录密码错误" , 2),

	/**
	 * 用户手机号已存在
	 */
	ERROR_USER_MOBILE_EXIST("20003", "ERROR_USER_MOBILE_EXIST", "用户手机号已存在", "用户手机号已存在" , 2),

	/**
	 * 用户账号已存在
	 */
	ERROR_USER_ACCOUNT_EXIST("20004", "ERROR_USER_ACCOUNT_EXIST", "用户账号已存在", "用户账号已存在" , 2),

	/**
	 * 用户登录密码为空
	 */
	ERROR_USER_LOGIN_PWD_NULL("20005", "ERROR_USER_LOGIN_PWD_NULL", "用户登录密码为空", "用户登录密码为空" , 2),

	/**
	 * 用户登录账号为空
	 */
	ERROR_USER_LOGIN_USER_NULL("20006", "ERROR_USER_LOGIN_USER_NULL", "用户登录账号为空", "用户登录账号为空" , 2),

	/**
	 * 用户失效
	 */
	ERROR_USER_INVALID("20007", "ERROR_USER_INVALID", "用户未启用", "用户状态已失效，请联系管理员" , 2),

	/**
	 * 用户没有权限
	 */
	USER_NO_PERMISSION("20008", "USER_NO_PERMISSION", "用户没有权限", "用户无权限，请联系管理员" , 2),

	/**
	 * 新密码为空
	 */
	ERROR_USER_NEW_PWD_NULL("20009", "ERROR_USER_NEW_PWD_NULL", "新密码为空", "请完善新密码" , 2),

	/**
	 * 用户部门为空
	 */
	ERROR_USER_DEPARTMENT_NULL("20010", "ERROR_USER_DEPARTMENT_NULL", "用户部门为空", "数据异常，请联系管理员" , 2),

	/**
	 * 用户所在组织机构为空
	 */
	ERROR_USER_ORGANIZATION_NULL("20011", "ERROR_USER_ORGANIZATION_NULL", "用户所在组织机构为空", "数据异常，请联系管理员" , 2),

	/**
	 * 组织机构名称为空
	 */
	ERROR_ORGANIZATION_NAME_NULL("20012", "ERROR_ORGANIZATION_NAME_NULL", "组织机构名称为空", "数据异常，请联系管理员" , 2),

	/**
	 * 输入的原始密码错误
	 */
	ERROR_INPUT_ORIGINAL_PASSWORD("20013", "ERROR_INPUT_ORIGINAL_PASSWORD", "输入的原始密码错误", "原始密码有误" , 2),


	/** ========== 基础数据30001开始 ========== **/
	/**
	 * 零配件编码非法
	 */
	ERROR_SPARE_CODE_INVALID("30001", "ERROR_SPARE_CODE_INVALID", "零配件编码非法", "零配件编码不符合规则，请检查" , 2),

	/**
	 * 英文名称非法
	 */
	ERROR_SPARE_NAME_EN_INVALID("30002", "ERROR_SPARE_NAME_EN_INVALID", "零配件英文名称非法", "零配件英文名称不符合规则，请检查" , 2),

	/**
	 * cwbt备件编码非法
	 */
	ERROR_SPARE_CWBT_CODE_INVALID("30003", "ERROR_SPARE_CWBT_CODE_INVALID", "cwbt备件编码不合法", "CWBT备件编码不符合规则，请检查" , 2),

	/**
	 * cwbt备件编码不唯一
	 */
	ERROR_SPARE_CWBT_CODE_NOT_UNIQUE("30004", "ERROR_SPARE_CWBT_CODE_NOT_UNIQUE", "CWBT备件编码不唯一", "CWBT备件编码重复，请检查" , 2),

	/**
	 * impa类型名称非法
	 */
	ERROR_IMPA_TYPE_NAME_NULL("30005", "ERROR_IMPA_TYPE_NAME_NULL", "impa类型名称为空", "请完善分类数据" , 2),

	/**
	 * cwbt编码不唯一
	 */
	ERROR_EXIST_CWBT_CODE("30006", "ERROR_EXIST_CWBT_CODE", "cwbt编码已存在", "CWBT编码重复，请检查" , 2),

	/**
	 * 组织机构层级非法
	 */
	ERROR_ORGANIZATION_LEVEL_NOT_EXIST("30007", "ERROR_ORGANIZATION_LEVEL_NOT_EXIST", "组织机构层级不存在", "数据异常，请联系管理员" , 2),

	/**
	 * 组织机构有子级
	 */
	ERROR_ORGANIZATION_EXIST_CHILDREN("30008", "ERROR_ORGANIZATION_EXIST_CHILDREN", "组织机构有子级", "存在下级机构或部门，不允许删除" , 2),

	/**
	 *  机构层次存在下一级
	 */
	ERROR_ORGANIZATION_LEVEL_EXIST_UNDER_LEVEL("30009", "ERROR_ORGANIZATION_LEVEL_EXIST_UNDER_LEVEL", " 机构层次存在下一级", "存在下级层级，不允许删除" , 2),

	/**
	 * 机构层次存在组织
	 */
	ERROR_ORGANIZATION_LEVEL_EXIST_ORGANIZATION("30010", "ERROR_ORGANIZATION_LEVEL_EXIST_ORGANIZATION", "机构层次存在组织", "存在已关联的组织机构，不允许删除" , 2),

	/**
	 * 设备cwbt编码下存在设备
	 */
	ERROR_EQUIPMENT_CWBT_CODE_HAS_EQUIPMENT("30011", "ERROR_EQUIPMENT_CWBT_CODE_HAS_EQUIPMENT", "设备cwbt编码下存在设备", "存在已关联的设备，不允许删除" , 2),

	/**
	 * 备件cwbt编码下存在备件
	 */
	ERROR_SPARE_CWBT_CODE_HAS_SPARE("30012", "ERROR_SPARE_CWBT_CODE_HAS_SPARE", "备件cwbt编码下存在备件", "存在已关联的设备，不允许删除" , 2),

	/**
	 * 文档类型下存在文档
	 */
	ERROR_DOCUMENT_CATEGORY_HAS_DOCUMENT("30013", "ERROR_DOCUMENT_CATEGORY_HAS_DOCUMENT", "文档类型下存在文档", "存在文档信息，不允许删除" , 2),

	/**
	 * 角色下存在用户
	 */
	ERROR_ROLE_HAS_USER("30014", "ERROR_ROLE_HAS_USER", "角色下存在用户", "存在用户信息，不允许删除" , 2),

	/**
	 * 机构层次存在角色
	 */
	ERROR_ORGANIZATION_LEVEL_EXIST_ROLE("30015", "ERROR_ORGANIZATION_LEVEL_EXIST_ROLE", "机构层次存在角色", "存在已关联的角色，不允许删除" , 2),

	/**
	 * cwbt编码不正确
	 */
	ERROR_CWBT_CODE("30016", "ERROR_CWBT_CODE", "cwbt编码不正确,不符合规则", "CWBT编码不符合规则，请检查" , 2),


	/** ========== 船端40001开始 ========== **/

	/**
	 * 通讯摄像头不存在
	 */
	ERROR_COMMUNICATION_CAMERA_NOT_EXISTS("40001", "ERROR_COMMUNICATION_CAMERA_NOT_EXISTS", "通讯摄像头不存在", "摄像头信息不存在，请检查" , 2),

	/**
	 * 通讯摄像头支持云台参数不全
	 */
	ERROR_COMMUNICATION_CAMERA_SUPPORT_CLOUD_PARAM_NO_ENOUGH("40002", "ERROR_COMMUNICATION_CAMERA_SUPPORT_CLOUD_PARAM_NO_ENOUGH", "通讯摄像头支持云台参数不全", "摄像头信息参数不全，请检查" , 2),

	/**
	 * 通讯电话簿所属同一机构或船舶的所属账号已存在
	 */
	ERROR_COMMUNICATION_TELEPHONE_USER_EXIST("40003", "ERROR_COMMUNICATION_TELEPHONE_USER_EXIST", "通讯电话簿所属同一机构或船舶的所属账号已存在, 不能重复绑定", "该账号已被使用" , 2),



	/** ========== 岸端50001开始 ========== **/
	/**
	 * 登记号不合法
	 */
	ERROR_REGISTER_NUM_INVALID("50001", "ERROR_REGISTER_NUM_INVALID", "登记号不合法", "登记号不符合规则，请检查" , 2),

	/**
	 * 呼号不合法
	 */
	ERROR_CALL_NUM_INVALID("50002", "ERROR_CALL_NUM_INVALID", "呼号不合法", "呼号不符合规则，请检查" , 2),

	/**
	 * 识别码不合法
	 */
	ERROR_IDENTIFIY_CODE_INVALID("50003", "ERROR_IDENTIFIY_CODE_INVALID", "识别码不合法", "识别码不符合规则，请检查" , 2),

	/**
	 * 设备编码不合法
	 */
	ERROR_EQUIPMENT_CODE_INVALID("50004", "ERROR_EQUIPMENT_CODE_INVALID", "设备编码不合法", "设备编码不符合规则，请检查" , 2),

	/**
	 * 证书编号不合法
	 */
	ERROR_CERTIFICATE_NUM_INVALID("50005", "ERROR_CERTIFICATE_NUM_INVALID", "证书编号不合法", "证书编号不符合规则，请检查" , 2),

	/**
	 * 证书性质非法
	 */
	ERROR_CERTIFICATE_NATURE_NOT_SCOPE("50006", "ERROR_CERTIFICATE_NATURE_NOT_SCOPE", "证书性质不在合法范围内", "证书性质错误，请检查" , 2),

	/**
	 * 证书日期非法
	 */
	ERROR_CERTIFICATE_DATE_INVALID("50007", "ERROR_CERTIFICATE_DATE_INVALID", "证书日期非法", "证书日期不符合规则，请检查" , 2),

	/**
	 * 安检任务不能修改
	 */
	ERROR_SECURITY_INSPECT_TASK_NOT_UPDATE("50008", "ERROR_SECURITY_INSPECT_TASK_NOT_UPDATE", "安检任务不能修改,因为有提交的记录", "任务进行中，无法操作" , 2),

	/**
	 * 安检任务不能删除
	 */
	ERROR_SECURITY_INSPECT_TASK_NOT_DELETE("50009", "ERROR_SECURITY_INSPECT_TASK_NOT_DELETE", "安检任务不能删除,因为有提交的记录", "任务状态已变更，无法操作" , 2),

	/**
	 * 不能添加安检记录
	 */
	ERROR_SECURITY_INSPECT_ITEM_NOT_SAVE("50010", "ERROR_SECURITY_INSPECT_ITEM_NOT_SAVE", "不能添加安检记录,1:船端用户并且任务是岸端派发,2:岸端用户操作安检任务是船端自检或者其他3:非待安检状态不能添加记录", "审批进行中，无法操作" , 2),

	/**
	 * 不能操作安检记录的延期操作
	 */
	ERROR_SECURITY_INSPECT_ITEM_NOT_DELAY_OPERATION("50011", "ERROR_SECURITY_INSPECT_ITEM_NOT_DELAY_OPERATION", "不能操作安检记录的延期操作，状态不是整改中", "超出试卷总分，请检查" , 2),


	/**
	 * 船舶考核任务关联工作流
	 */
	ERROR_SHIP_EXAMINATION_TASK_RELATION_WORKFLOW("50012","ERROR_SHIP_EXAMINATION_TASK_RELATION_WORKFLOW","考核任务状态不是待考核,或者已进入审批流", "考核任务已提交，无法操作", 2),

	/**
	 * 不能操作安检记录
	 */
	ERROR_SECURITY_INSPECT_ITEM_NOT_OPERATION("50013","ERROR_SECURITY_INSPECT_ITEM_NOT_OPERATION","当前整改项已完成，不能重复整改", "状态已变更，请刷新页面", 2),

	/**
	 * 船舶考核任务状态不合法
	 */
	ERROR_SHIP_EXAMINATION_TASK_STATUS_INVALID("50014","ERROR_SHIP_EXAMINATION_TASK_STATUS_INVALID","船舶考核任务状态不合法,不能执行操作", "状态已变更，请刷新页面", 2),

	/**
	 * 船舶考核任务未通过审批
	 */
	ERROR_SHIP_EXAMINATION_TASK_NOT_PASS("50015","ERROR_SHIP_EXAMINATION_TASK_NOT_PASS","只有工作流状态是审批通过时才可以进行录入", "考核任务审批中，无法操作", 2),

	/**
	 * 船舶考核任务明细分数越界异常
	 */
	ERROR_SHIP_EXAMINATION_TASK_SCORE_BEYOND("50016","ERROR_SHIP_EXAMINATION_TASK_SCORE_BEYOND","船舶考核任务明细分数越界异常", "超出试卷总分，请检查", 2),

	/**
	 * 船舶编号不合法
	 */
	ERROR_SHIP_NUM_INVALID("50017", "ERROR_SHIP_NUM_INVALID", "船舶编号不合法", "船舶编号不符合规则，请检查" , 2),

	/**
	 * 船舶编号不唯一
	 */
	ERROR_SHIP_NUM_NOT_UNIQUE("50018", "ERROR_SHIP_NUM_NOT_UNIQUE", "船舶编号不唯一", "船舶编号重复，请检查" , 2),

	/**
	 * 设备下有子项备件
	 */
	ERROR_EQUIPMENT_EXIST_CHILDREN("50019", "ERROR_EQUIPMENT_EXIST_CHILDREN","设备下有子项备件", "存在备件信息，不允许删除", 2),

	/**
	 * 完成维保时维保项为空
	 */
	ERROR_FINISH_SHIP_MAINTAIN_ITEM_IS_NULL("50020", "ERROR_FINISH_SHIP_MAINTAIN_ITEM_IS_NULL","完成维保时维保项为空", "请勾选维保项", 2),

	/**
	 * 出库的数量大于库存的数量
	 */
	ERROR_OUT_STOCK_NUM("50021", "ERROR_OUT_STOCK_NUM","出库的数量大于库存的数量", "出库的数量大于库存的数量", 2),

	/**
	 * 审批通过的维保项不能被删除
	 */
	ERROR_DELETE_SHIP_MAINTAIN_APPLY_PLAN_IN("50022", "ERROR_DELETE_SHIP_MAINTAIN_APPLY_PLAN_IN","审批通过的维保项不能被删除", "已审批通过的计划内维保项，不允许删除", 2),

	/**
	 * 船舶维保项为空
	 */
	ERROR_SHIP_MAINTAIN_ITEM_NULL("50023", "ERROR_SHIP_MAINTAIN_ITEM_NULL"," 船舶维保项为空", "请完善维保项内容", 2),

	/**
	 * 船舶计划时间为空
	 */
	ERROR_SHIP_MAINTAIN_PLAN_TIME_NULL("50024","ERROR_SHIP_MAINTAIN_PLAN_TIME_NULL","船舶计划时间为空", "请完善计划时间内容", 2),

	/**
	 * 仓库被使用了，不能删除
	 */
	ERROR_SHIP_STOREHOUSE_IS_USE("50025", "ERROR_SHIP_STOREHOUSE_IS_USE", "仓库被使用了，不能删除", "已关联库存信息，不允许删除", 2),

	/**
	 * 待维保初始化数据失败
	 */
	ERROR_SHIP_MAINTAIN_INIT_FAIL("50026", "ERROR_SHIP_MAINTAIN_INIT_FAIL", "待维保初始化数据失败", "数据异常，请联系管理员", 2),

	/**
	 *该船舶下无审批角色，不能请假
	 */
	ERROR_LEAVE_NOT_APPROVE_ROLE("50027", "ERROR_LEAVE_NOT_APPROVE_ROLE", "该船舶下无审批角色，不能请假", "无审批角色，请联系管理员" , 2),

	/**
	 * 维修不能修改，因为已提交
	 */
	ERROR_SHIP_REPAIR_SAVE("50028","ERROR_SHIP_REPAIR_SAVE","维修不能修改，因为已提交", "状态已变更，请刷新页面", 2),

	/**
	 * 可入库的数量小于0,无法入库成功
	 */
	ERROR_IN_STOCK_NUM("50029", "ERROR_IN_STOCK_NUM","可入库的数量小于0,无法入库成功 ", "可入库的数量小于0,无法入库成功", 2),

	/**
	 * 该维修已完成,请刷新页面
	 */
	ERROR_ALREADY_FINISH_REPAIR("50030","ERROR_ALREADY_FINISH_REPAIR","该维修已完成,不能重复维修", "该维修已完成,请刷新页面", 2),

	/**
	 * 该维保已完成,请刷新页面
	 */
	ERROR_ALREADY_FINISH_MAINTAIN("50031","ERROR_ALREADY_FINISH_REPAIR","该维保已完成,不能重复维保", "该维保已完成,请刷新页面", 2),

	/**
	 * 考核录入与考核发布的项不一致异常
	 */
	ERROR_SHIP_EXAMINATION_TASK_CHECK_LIST("50032","ERROR_SHIP_EXAMINATION_TASK_CHECK_LIST","考核录入与考核发布的项不一致异常","考核录入项与发布不一致,无法录入",2),

	/**
	 * 船舶复制目的表下存在设备不允许复制
	 */
	ERROR_SHIP_COPY_EQUIPMENT_ERROR("50033","ERROR_SHIP_COPY_EQUIPMENT_ERROR","新船下存在设备，不允许重复复制", "新船下存在设备，不允许重复复制", 2),


	/**
	 * 修改出入库数量时，出入库数量参数有误
	 */
	ERROR_SHIP_STORE_QUANTITY_ERROR("50034","ERROR_SHIP_STORE_QUANTITY_ERROR","出入库数量参数有误", "出入库数量参数有误", 2),

	/**
	 * 维保项重复作废
	 */
	ERROR_SHIP_MAINTAIN_INVALID_ITEM("50035", "SHIP_MAINTAIN_PLAN_HAD_INVALID", "维保项已经是作废状态，无需再次作废", "维保项已经是作废状态，无需再次作废", 2),

	/**
	 * 维保计划重复作废
	 */
	ERROR_SHIP_MAINTAIN_INVALID_PLAN("50036", "SHIP_MAINTAIN_PLAN_TASK_HAD_INVALID", "维保计划已经是作废状态，无需再次作废", "维保计划已经是作废状态，无需再次作废", 2),

	/**
	 * 存在维保项已经完成不可作废维保计划
	 */
	ERROR_SHIP_MAINTAIN_EXIST_ITEM_FINISH("50037", "SHIP_MAINTAIN_HAD_FINISH", "维保计划存在已经完成的维保项目，不可作废", "维保计划存在已经完成的维保项目，不可作废", 2),

	/**
	 * 维保计划重启
	 */
	ERROR_SHIP_MAINTAIN_REBOOT_PLAN("50038", "SHIP_MAINTAIN_NOT_INVALID", "维保计划不是作废状态", "维保计划不是作废状态，无需重启", 2),

	/**
	 * 船舶复制目的表下存在物料不允许复制物料数据
	 */
	ERROR_SHIP_COPY_IMPA_ERROR("50039","ERROR_SHIP_COPY_IMPA_ERROR","新船下存在物料，不允许重复复制", "新船下存在物料，不允许重复复制", 2),


	/**
	 * 不是年修计划
	 */
	ERROR_SHIP_REPAIR_NOT_YEAR("50040", "ERROR_SHIP_REPAIR_TYPE_ERROR", "该维修计划不是年修计划", "该维修计划不是年修计划", 2),

	/**
	 * 已经有年修项目完成
	 */
	ERROR_SHIP_REPAIR_HAS_ITEM_FINISH("50041", "HAS_ITEM_FINISH", "已经有计划内维保项完成或者作废", "已经有计划内维保项完成或者作废", 2),

	/**
	 * 源船舶下无设备/物料
	 */
	ERROR_SHIP_COPY_SOURCE_ERROR("50042","ERROR_SHIP_COPY_EQUIPMENT_ERROR","此船舶无可导入数据", "此船舶无可导入数据", 2),


	/** ========== 审批流60001开始 ========== **/
	/**
	 *工作流重复
	 */
	ERROR_WORKFLOW_REPEAT("60001", "ERROR_WORKFLOW_REPEAT", "工作流重复", "数据异常，请联系管理员" , 2),

	/**
	 *工作流实例被锁住
	 */
	ERROR_WORKFLOW_INSTANCE_LOCKER("60002", "ERROR_WORKFLOW_INSTANCE_LOCKER", "工作流实例被锁住", "状态已变更，请刷新页面" , 2),

	/**
	 *工作流实例进入审批环节,不能编辑
	 */
	ERROR_WORKFLOW_INSTANCE_USED("60003", "ERROR_WORKFLOW_INSTANCE_USED", "工作流实例进入审批环节,不能编辑", "状态已变更，请刷新页面" , 2),

	/**
	 *考卷和任务已经关联,不能操作
	 */
	ERROR_EXAMINATION_PAPER_RELATION("60004", "ERROR_EXAMINATION_PAPER_RELATION", "考卷和任务已经关联,不能操作", "已关联任务，无法操作" , 2),

	/**
	 * 用户没有权限审批工作流
	 */
	ERROR_USER_NOT_APPROVE_WORKFLOW_INSTANCE("60005","ERROR_USER_NOT_APPROVE_WORKFLOW_INSTANCE","用户没有权限审批工作流", "无权限审批，请刷新页面", 2),

	/**
	 * 工作状态不对应
	 */
	ERROR_WORKFLOW_INSTANCE_OPERATION_INVALID("60006","ERROR_WORKFLOW_INSTANCE_OPERATION_INVALID","工作状态不对应", "状态已变更，请刷新页面", 2),

	/**
	 * 审批状态不正确
	 */
	ERROR_APPROVE_STATUS("60007","ERROR_APPROVE_STATUS","审批状态不正确", "状态已变更，请刷新页面", 2),

	/**
	 * 撤回的工作流不能审批
	 */
	ERROR_CANCEL_WORKFLOW_CAN_NOT_APPROVE("60008","ERROR_CANCEL_WORKFLOW_CAN_NOT_APPROVE","撤回的工作流不能审批", "状态已变更，请刷新页面", 2),

	/**
	 *考核任务已经删除,不能进行申请
	 */
	ERROR_EXAMINATION_TASK_DELETE("60009", "ERROR_EXAMINATION_TASK_DELETE", "考核任务已经删除,不能进行申请", "状态已变更，请刷新页面" , 2),

	/**
	 * 该申请已进行审批,无法撤回
	 */
	ERROR_APPLY_IN_APPROVALING("60010","ERROR_APPLY_IN_APPROVALING","该申请已进行审批,无法撤回", "状态已变更，请刷新页面", 2),

	/**
	 *提交状态工作流不能审批
	 */
	ERROR_SUBMIT_WORKFLOW_CAN_NOT_APPROVE("60011","ERROR_SUBMIT_WORKFLOW_CAN_NOT_APPROVE","提交状态工作流不能审批", "状态已变更，请刷新页面", 2),

	/**
	 *当前请假已审批
	 */
	ERROR_LEAVE_HAS_APPROVE("60012","ERROR_LEAVE_HAS_APPROVE","当前请假已审批", "状态已变更，请刷新页面", 2),

	/**
	 * 该申请已撤回,无法撤回
	 */
	ERROR_APPLY_IN_CANCEL("60013","ERROR_APPLY_IN_CANCEL","该申请已撤回,无法再次撤回", "状态已变更，请刷新页面", 2),


	/** ========== 消息70001开始 ========== **/
	/**
	 * 更新消息队列消费数量失败
	 */
	ERROR_UPDATE_QUEUE_CONSUME_QUANTITY_FAIL("70001", "ERROR_UPDATE_QUEUE_CONSUME_QUANTITY_FAIL", "更新消息队列消费数量失败","" , 2),



	/** ========== 第三方80001开始 ========== **/

	/**
	 * 下载excel失败
	 */
	ERROR_EXCEL_DOWNLOAD_FAIL("80001", "ERROR_EXCEL_DOWNLOAD_FAIL", "下载excel失败", "下载excel失败" , 2),

	/**
	 * 下载pdf失败
	 */
	ERROR_PDF_DOWNLOAD_FAIL("80002", "ERROR_PDF_DOWNLOAD_FAIL", "下载pdf失败", "下载pdf失败" , 2),

	/**
	 * 水印失败
	 */
	ERROR_PUT_WATERREMARK_FAIL("80003", "ERROR_PUT_WATERREMARK_FAIL", "增加水印失败", "导出失败" , 2),


	/** ========== 定时任务90001开始 ========== **/


	/** ========== 同步数据11001开始 ========== **/


	/** ========== 海图12001开始 ========== **/


	/** ========== 监管指挥13001开始 ========== **/
	/**
	 * 事故状态不合法
	 */
	ERROR_SUPERVISE_STATUS_NOT_LEGAL("13002","ERROR_SUPERVISE_STATUS_NOT_LEGAL","事故状态不合法", "状态已变更，请刷新页面", 2),

	/**
	 * 预案类别存在子项,不能删除
	 */
	ERROR_SUPERVISE_PLAN_TYPE_EXIST_CHILDREN("13004","ERROR_SUPERVISE_PLAN_TYPE_EXIST_CHILDREN","预案类别存在子项,不能删除", "类别下存在内容，不允许删除", 2),

	/**
	 * 演练计划状态不合法
	 */
	ERROR_SUPERVISE_DRILL_PLAN_STATUS_NOT_LEGAL("13005","ERROR_SUPERVISE_DRILL_PLAN_STATUS_NOT_LEGAL","演练计划状态不合法", "状态已变更，请刷新页面", 2),

	/**
	 * 演练内容为空
	 */
	ERROR_SUPERVISE_DRILL_CONTENT_NULL("13006","ERROR_SUPERVISE_DRILL_CONTENT_NULL","演练内容为空", "演练内容为空，请检查", 2),

	/**
	 * 演练计划项状态不合法
	 */
	ERROR_SUPERVISE_DRILL_PLAN_ITEM_STATUS_NOT_LEGAL("13007","ERROR_SUPERVISE_DRILL_PLAN_ITEM_STATUS_NOT_LEGAL","演练计划项状态不合法", "状态已变更，请刷新页面", 2),

	/**
	 * 演练计划项没有全部完成
	 */
	ERROR_SUPERVISE_DRILL_PLAN_ITEM_NOT_ALL_FINISH("13008","ERROR_SUPERVISE_DRILL_PLAN_ITEM_NOT_ALL_FINISH","演练计划项没有全部完成", "计划项未全部完成，请检查", 2),

	/**
	 * 值班状态已变更为未接班，无法进行接班
	 */
	ERROR_SUPERVISE_SHIFT_STATUS_NOACCEPT("13009","ERROR_SUPERVISE_SHIFT_STATUS_NOACCEPT","值班时间已过，无法进行接班操作", "值班时间已过，无法进行接班操作", 2),

	/**
	 * 超时未接班，无法进行接班
	 */
	ERROR_SUPERVISE_SHIFT_DELAY("13010","ERROR_SUPERVISE_SHIFT_DELAY","值班时间已过，无法进行接班操作", "值班时间已过，无法进行接班操作", 2),

	/**
	 * 应急资源类别下存在子项,不能删除
	 */
	ERROR_SUPERVISE_EMERGENCY_RESOURCE_HAS_CHILDREN("13011","ERROR_SUPERVISE_EMERGENCY_RESOURCE_HAS_CHILDREN","应急资源类别下存在子项,不能删除", "应急资源类别下存在子项,不能删除", 2),

	/**
	 * 演练任务类别下存在子项，不能删除
	 */
	ERROR_SUPERVISE_TASK_TYPE_HAS_CHILDREN("13012","ERROR_SUPERVISE_TASK_TYPE_HAS_CHILDREN","演练任务类别下存在子项，不能删除", "任务下存在内容，不允许删除", 2),

	/**
	 * 资源类别下存在子项，不能删除
	 */
	ERROR_HUMAN_RESOURCE_TYPE_HAS_CHILDREN("13013","ERROR_HUMAN_RESOURCE_TYPE_HAS_CHILDREN","资源类别下存在子项，不能删除", "类别下存在内容，不允许删除", 2),


	/** ========== IM14001开始 ========== **/
	/**
	 * 分组名称已存在
	 */
	ERROR_GORUP_NAME_IS_EXIST("14001", "ERROR_GORUP_NAME_IS_EXIST", "分组名称已存在", "分组名称已存在" , 2),


	/** ========== 公告15001开始 ========== **/
	/**
	 * 内容过长
	 */
	ERROR_NOTICE_CONTENT_TOO_LONG("15001", "ERROR_NOTICE_CONTENT_TOO_LONG", "公告内容不能超过255个字符", "公告内容不能超过255个字符" , 2),


	/** ========== 导出16001开始 ========== **/
	/**
	 * 导出无数据
	 */
	ERROR_EXPORT_NO_DATA("16001", "ERROR_EXPORT_NO_DATA", "暂无列表数据，导出失败", "暂无数据，导出失败" , 2),

	/** ========== 航行统计 17001========== **/
	/**
	 * 编号已存在
	 */
	SHIP_SAILING_NUMBER_IS_EXIST("17001", "SHIP_SAILING_NUMBER_IS_EXIST", "编号已存在", "编号已存在" , 2),
	/**
	 * 重复打卡 开始/结束
	 */
	ERROR_FUEL_CLOCK("17001", "ERROR_FUEL_CLOCK", "该操作已执行，请刷新页面后再试！", "该操作已执行，请刷新页面后再试！" , 2);

	;

	/** 错误类型（0:一般结果码；1：系统级的错误；2：应用级的错误；4：其他错误） **/
	private int type;
	/** 响应吗 **/
	private String code;
	/** 响应吗名称 **/
	private String codeName;
	/** 提示消息 **/
	private String message;
	/** 消息备注 **/
	private String remark;

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public int getType() {
		return this.type;
	}

	public String getRemark() {
		return this.remark;
	}

	ResponseCode(String code, String codeName, String remark, String message, int type) {
		this.message = message;
		this.code = code;
		this.codeName = codeName;
		this.type = type;
		this.remark = remark;
	}

	public void setMessage(String message) {
		if (StrUtil.isNotBlank((this.message))) {
			this.message = "";
		}
		this.message = message;
	}

	public static ResponseCode getResultCodeByCode(String code) {
		for (ResponseCode result : ResponseCode.values()) {
			if (result.getCode().equals(code)) {
				return result;
			}
		}
		return null;
	}

	public static ResponseCode getResultCodeByMessage(String message) {
		for (ResponseCode result : ResponseCode.values()) {
			if (result.getMessage().equals(message)) {
				return result;
			}
		}
		return null;
	}

}
