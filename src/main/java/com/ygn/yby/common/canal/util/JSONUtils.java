/**
 * *@Copyright: 2019 www.yagena.com.cn inc . All rights reserved.
 * *注意：本内容仅限于雅格纳内部传阅，禁止外泄以及用于其他的商业目
 */
package com.ygn.yby.common.canal.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ygn.yby.common.canal.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class JSONUtils {
    private JSONUtils() {
    }

    /**
     * json转对象
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T>  json2ListEntity(List text, Class clazz) {
        try {
            if (CollUtil.isNotEmpty(text)){
                JSONArray arr = new JSONArray();
                arr.addAll(text);
                return json2ListEntity(arr.toJSONString(),clazz);
            }
        } catch (Exception e) {
            log.error("转换对象错误data：{}.",text);
        }
        return null;
    }

    /**
     * json转对象
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T>  json2ListEntity(String text, Class clazz) {
        try {
            return JSON.parseArray(text,clazz);
        } catch (Exception e) {
            log.error("转换对象错误data：{}.",text);
            return null;
        }
    }

    /**
     * 判断是否是JSONObject
     * @param content
     * @return
     */
    public static boolean isJson(String content) {
        try {
            JSON.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否是JSONArray
     * @param content
     * @return
     */
    public static boolean isArray(String content) {
        try {
            JSON.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * str转JSONObject
     *
     * @param json
     * @return
     */
    public static JSONObject str2Json(String json) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        try {
            return JSON.parseObject(json);
        } catch (Exception e) {
            log.error("json内部错误 -> {}",json, e);
            return null;
        }
    }

    /**
     * str转JSONArray
     *
     * @param json
     * @return
     */
    public static JSONArray str2JSONArray(String json) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        try {
            return JSON.parseArray(json);
        } catch (Exception e) {
            log.error("json内部错误 -> {}",json, e);
            return null;
        }
    }

    /**
     * 获取JSONObject多级
     * @param json
     * @param key1
     * @param key2
     * @return
     */
    public static JSONObject getJSONObject(String json, String key1, String key2) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        return getJSONObject(str2Json(json),key1,key2);
    }

    /**
     * 获取JSONObject多级
     * @param json
     * @param key1
     * @param key2
     * @return
     */
    public static JSONObject getJSONObject(JSONObject json, String key1, String key2) {
        if (CollUtil.isEmpty(json)) return null;
        try {
            return getJSONObject(getJSONObject(json,key1),key2);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取JSONObject多级
     * @param json
     * @param key1
     * @param key2
     * @return
     */
    public static JSONObject getJSONObject(String json, String key1, String key2, String key3) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        return getJSONObject(str2Json(json),key1,key2,key3);
    }

    /**
     * 获取JSONObject多级
     * @param json
     * @param key1
     * @param key2
     * @return
     */
    public static JSONObject getJSONObject(JSONObject json, String key1, String key2, String key3) {
        if (CollUtil.isEmpty(json)) return null;
        try {
            return getJSONObject(getJSONObject(json,key1,key2),key3);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取JSONObject多级
     * @param json
     * @param keys
     * @return
     */
    public static JSONObject getJSONObjects(JSONObject json, String... keys) {
        if (CollUtil.isEmpty(json)) return null;
        Object obj = JSON.parse(json.toJSONString());
        for (String key:keys){
            if (obj == null) return null;
            else if (obj instanceof JSONObject)
                obj = instanceofJSONObject(obj,key);
            else if (obj instanceof JSONArray)
                obj = instanceofJSONArray(obj,key);
            else if (obj instanceof String)
                obj = identifyJSON(obj,key);
            else obj = null;
        }
        return obj == null ? null : JSONUtils.str2Json(obj.toString());
    }

    /**
     * 判断字符串是json或者Array
     * @param obj
     * @param key
     * @return
     */
    private static Object identifyJSON(Object obj,String key){
        String objStr = obj.toString();
        try{
            JSON.parseObject(objStr);
            obj = instanceofJSONObject(obj,key);
        }catch (Exception e){
            try{
                JSON.parseArray(objStr);
                obj = instanceofJSONArray(obj,key);
            }catch (Exception e1){
                obj = null;
            }
        }
        return obj;
    }

    /**
     * 获取JSONObject
     * @param obj
     * @param key
     * @return
     */
    private static Object instanceofJSONObject(Object obj,String key){
        if (ObjectUtil.isNotEmpty(obj)){
            JSONObject json = JSONUtils.str2Json(obj.toString());
            if (CollUtil.isNotEmpty(json))
                return json.get(key);
        }
        return null;
    }

    /**
     * 获取JSONArray
     * @param obj
     * @param key
     * @return
     */
    private static Object instanceofJSONArray(Object obj,String key){
        JSONArray objs = JSONUtils.str2JSONArray(obj.toString());
        if (CollUtil.isNotEmpty(objs)){
            Object tmp = null;
            for(int i=0; i<objs.size(); i++){
                JSONObject jsonObject = JSONUtils.str2Json(objs.getString(i));
                if (CollUtil.isNotEmpty(jsonObject) && jsonObject.containsKey(key)){
                    tmp = jsonObject.get(key);
                    break;
                }
            }
            obj = tmp;
        }else obj = null;
        return obj;
    }

    /**
     * 获取JSONObject
     * @param json
     * @param key
     * @return
     */
    public static JSONObject getJSONObject(String json, String key) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        try {
            return getJSONObject(str2Json(json),key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取JSONObject
     * @param json
     * @param sub
     * @return
     */
    public static JSONObject getJSONObject(JSONArray json, int sub) {
        if (CollUtil.isEmpty(json)) return null;
        try {
            return json.getJSONObject(sub);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取JSONObject
     * @param json
     * @param sub
     * @return
     */
    public static JSONObject getJSONObject(String json, int sub) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        try {
            return getJSONObject(str2JSONArray(json),sub);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取JSONObject
     * @param json
     * @param key
     * @return
     */
    public static JSONObject getJSONObject(JSONObject json, String key) {
        if (CollUtil.isEmpty(json)) return null;
        try {
            return json.getJSONObject(key);
        } catch (Exception e) {
            log.error("json内部错误 -> {}",json, e);
            return null;
        }
    }

    /**
     * 获取JSONArray
     * @param json
     * @param key
     * @return
     */
    public static JSONArray getJSONArray(String json, String key) {
        if (StringUtils.isBlank(json) || Constants.DEFAULT_RETURN.equals(json)) return null;
        try {
            return getJSONArray(str2Json(json),key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取JSONArray
     * @param json
     * @param key
     * @return
     */
    public static JSONArray getJSONArray(JSONObject json, String key) {
        if (CollUtil.isEmpty(json)) return null;
        try {
            return json.getJSONArray(key);
        } catch (Exception e) {
            log.error("json内部错误 -> {}",json, e);
            return null;
        }
    }

    /**
     * 不安全的json转对象通用方法
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            log.error("json内部错误 -> {}",json, e);
            return null;
        }
    }

    /**
     * JSONObject合并
     *
     * @param json1
     * @param json2
     */
    public static void jsonUnion(JSONObject json1, JSONObject json2) {
        if (CollUtil.isEmpty(json2)) return ;
        json1.putAll(json2);
    }

    /**
     * 对象转json
     *
     * @param obj
     * @return
     */
    public static JSONObject convertToJSONObject(Object obj) {
        JSONObject map = new JSONObject();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String varName = field.getName();
                boolean accessFlag = field.isAccessible();
                field.setAccessible(true);

                Object o = field.get(obj);
                if (o != null)
                    map.put(varName, o.toString());

                field.setAccessible(accessFlag);
            }
        } catch (Exception e) {
            log.error("对象转json失败！！！", e);
        }
        return map;
    }

    /**
     * 交换
     * @param kv
     * @param dbColumns
     * @return
     */
    public static JSONObject exchange(JSONObject kv, String[] dbColumns) {
        JSONObject json = new JSONObject();
        if (CollUtil.isNotEmpty(json)){
            for (String dbColumn:dbColumns){
                Object ob = kv.get(dbColumn);
                if (ob != null)
                    json.put(dbColumn,kv.get(dbColumn));
            }
        }
        return json;
    }

    /**
     * 交换
     * @param kv
     * @param dbColumns
     * @return
     */
    public static JSONArray exchanges(JSONArray kv, String[] dbColumns) {
        JSONArray arr = new JSONArray();
        if (CollUtil.isNotEmpty(kv)){
            for (int j = 0; j < kv.size(); j++) {
                arr.add(exchange(kv.getJSONObject(j),dbColumns));
            }
        }
        return arr;
    }
}

