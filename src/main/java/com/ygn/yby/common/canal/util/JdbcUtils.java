/**
 * *@Copyright: 2019 www.yagena.com.cn inc . All rights reserved.
 * *注意：本内容仅限于雅格纳内部传阅，禁止外泄以及用于其他的商业目
 */
package com.ygn.yby.common.canal.util;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多库查询
 * 只初始化一次方法map
 * 数据库增删改查方法
 */
@Slf4j
public class JdbcUtils {
    private static Map<String, Db> collection = new ConcurrentHashMap();
    static{
        List<String> groups = new Setting("db.setting").getGroups();
        for (String group:groups){
            try {
                collection.put(group, Db.use(group));
            }catch (Exception e){
                log.error("数据库连接出现问题 {}",group,e);
            }
        }
    }

    /**
     * 插入：返回插入行数
     * @param map
     * @param tablename
     * @return
     */
    public static int insert(String tablename, Map<String, Object> map){
        return insert(getEntity(map,tablename));
    }

    /**
     * 插入：返回插入行数
     * @param entity
     * @return
     */
    public static int insert(Entity entity) {
        int key = 0;
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            key = use(db).insert(entity);
            log.info("insert返回插入行数：{},cost:{}",key,cost(start));
        } catch (SQLException e) {
            log.error("insert数据插入（返回插入行数）:{},错误",entity,e);
        }
        return key;
    }

    /**
     * 批量插入：返回插入行数数组
     * @param maps
     * @param tablename
     * @return
     */
    @SafeVarargs
    public static int[] insertAll(String tablename, Map<String, Object>... maps){
        Collection<Entity> coll = new ArrayList<>();
        for (Map<String, Object> map:maps)
            coll.add(getEntity(map,tablename));
        return insertAll(coll);
    }

    /**
     * 批量插入：返回插入行数数组
     * @param maps
     * @param tablename
     * @return
     */
    public static int[] insertAll(String tablename,List<Object> maps){
        Collection<Entity> coll = new ArrayList<>();
        for (Object map :maps)
            coll.add(getEntity(JSONUtil.parseObj(map),tablename));
        return insertAll(coll);
    }

    /**
     * 批量插入：返回插入行数数组
     * @param records
     * @return
     */
    public static int[] insertAll(Collection<Entity> records) {
        int[] key = null;
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            key = use(db).insert(records);
            log.info("insertAll返回插入行数数组：{},cost:{}",key,cost(start));
        } catch (SQLException e) {
            log.error("insert数据插入（返回插入行数数组）:{},错误",records,e);
        }
        return key;
    }

    /**
     * 插入或更新：返回插入行数
     * @param map
     * @param tablename
     * @return
     */
    public static int insertOrUpdate(String tablename, Map<String, Object> map, String... keys){
        return insertOrUpdate(getEntity(map,tablename),keys);
    }

    /**
     * 插入或更新：返回插入行数
     * @param entity
     * @return
     */
    public static int insertOrUpdate(Entity entity, String... keys) {
        int key = 0;
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            key = use(db).insertOrUpdate(entity,keys);
            log.info("insertOrUpdate返回插入行数：{},cost:{}",key,cost(start));
        } catch (SQLException e) {
            log.error("insert数据插入（返回插入行数）:{},错误",entity,e);
        }
        return key;
    }

    /**
     * 插入：返回主键
     * @param map
     * @param tablename
     * @return
     */
    public static Long insertForGeneratedKey(String tablename, Map<String, Object> map){
        return insertForGeneratedKey(getEntity(map,tablename));
    }

    /**
     * 插入：返回主键
     * @param entity
     * @return
     */
    public static Long insertForGeneratedKey(Entity entity) {
        Long key = 0L;
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            key = use(db).insertForGeneratedKey(entity);
            log.info("insertForGeneratedKey返回主键：{},cost:{}",key,cost(start));
        } catch (SQLException e) {
            log.error("insertForGeneratedKey数据插入（返回自增主键）：{},错误",entity,e);
        }
        return key;
    }

    /**
     * 删除：返回影响行数
     * @param map
     * @param tablename
     * @return
     */
    public static int delete(String tablename, Map<String, Object> map){
        return delete(getEntity(map,tablename));
    }

    /**
     * 删除：返回影响行数
     * @param where
     * @return
     */
    public static int delete(Entity where) {
        int key = 0;
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            key = use(db).del(where);
            log.info("delete返回影响行数：{},cost:{}",key,cost(start));
        } catch (SQLException e) {
            log.error("delete数据删除（返回影响行数）:{},错误",where,e);
        }
        return key;
    }

    /**
     * 更新：返回影响行数
     * @param map
     * @param tablename
     * @return
     */
    public static int update(String tablename, Map<String, Object> map, Map<String, Object> whereMap){
        return update(getEntity(map),getEntity(whereMap,tablename));
    }

    /**
     * 更新：返回影响行数
     * @param entity
     * @param where
     * @return
     */
    public static int update(Entity entity, Entity where) {
        int key = 0;
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            key = use(db).update(entity,where);
            log.info("update返回影响行数：{},cost:{}",key,cost(start));
        } catch (SQLException e) {
            log.error("update数据更新（返回影响行数）：{},条件：{}",entity,where,e);
        }
        return key;
    }

    /**
     * 查询:条件查询,需要注解注入Class
     * @param map
     * @param tablename
     * @param <T>
     * @return
     */
    public static <T> List<T> find2Bean(String tablename, Map<String, Object> map){
        return find2Bean(getEntity(map,tablename));
    }

    /**
     * 查询:条件查询,需要注解注入Class
     * @param where
     * @param <T>
     * @return
     */
    public static <T> List<T> find2Bean(Entity where){
        List<T> lts = null;
        Class<T> clazz = AnnotationUtils.methodDeepnessAnnotationService();
        String db = AnnotationUtils.dataSourceAnnotation(clazz);
        try {
            long start = start();
            List<Entity> list = use(db).findAll(where);
            lts = template(list,clazz);
            log.info("find2Bean查询数据：{},cost:{}",lts,cost(start));
        } catch (SQLException e) {
            log.error("find2Bean查询数据:{},错误",where,e);
        }
        return lts;
    }

    /**
     * 查询:查询所有,需要注解注入Class
     * @param dbAndTableName
     * @param <T>
     * @return
     */
    public static <T> List<T> findAll2Bean(String dbAndTableName){
        Class<T> clazz = AnnotationUtils.methodDeepnessAnnotationService();
        return findAll2Bean(dbAndTableName,clazz);
    }

    /**
     * 查询:查询所有,需要注解注入Class
     * @param dbAndTableName
     * @param <T>
     * @return
     */
    public static <T> List<T> findAll2Bean(String dbAndTableName,Class<T> clazz){
        List<T> lts = null;
        String db = AnnotationUtils.dataSourceAnnotation(clazz);
        try {
            long start = start();
            List<Entity> list = use(db).findAll(dbAndTableName);
            lts = template(list,clazz);
            log.info("findAll2Bean查询数据：{},cost:{}",lts,cost(start));
        } catch (SQLException e) {
            log.error("findAll2Bean查询数:{},据错误",dbAndTableName,e);
        }
        return lts;
    }

    /**
     * 查询:条件查询,需要注解注入Class
     * @param where
     * @return
     */
    public static JSONArray find2JSONArray(Entity where){
        JSONArray lts = new JSONArray();
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            List<Entity> list = use(db).findAll(where);
            for (Entity entity:list){
                if (CollUtil.isNotEmpty(entity)){
                    JSONObject map = new JSONObject(entity);
                    lts.add(map);
                }

            }
            log.info("find2JSONArray查询数据：{},cost:{}",lts,cost(start));
        } catch (SQLException e) {
            log.error("find2JSONArray查询数据:{},错误",where,e);
        }
        return lts;
    }

    /**
     * 查询:条件查询,需要注解注入Class
     * @param where
     * @return
     */
    public static JSONObject find2JSONObject(Entity where){
        JSONObject lts = new JSONObject();
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            List<Entity> list = use(db).findAll(where);
            if (CollUtil.isNotEmpty(list))
                lts.putAll(list.get(0));
            log.info("find2JSONObject查询数据：{},cost:{}",lts,cost(start));
        } catch (SQLException e) {
            log.error("find2JSONObject查询数据:{},错误",where,e);
        }
        return lts;
    }

    /**
     * 自定义执行sql,需要注解注入Class
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> querySql(String sql, Object... params){
        List<T> lts = null;
        Class<T> clazz = AnnotationUtils.methodDeepnessAnnotationService();
        String db = AnnotationUtils.dataSourceAnnotation(clazz);
        try {
            long start = start();
            List<Entity> list = use(db).query(sql, params);
            lts = template(list,clazz);
            log.info("querySql执行sql,返回数据：{},cost:{}",lts,cost(start));
        } catch (SQLException e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql,Arrays.toString(params),e);
        }
        return lts;
    }

    /**
     * 自定义执行sql
     * @param sql
     * @param params
     * @return
     */
    public static void querySqlBowOut(String sql, Object... params){
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            List<Entity> list = use(db).query(sql, params);
            log.info("querySql执行sql,返回数据：{},cost:{}",list,cost(start));
        } catch (SQLException e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
        }
    }


    /**
     * 自定义执行sql
     * @param sql
     * @param params
     * @return
     */
    public static <T> List<T> querySqlList(String sql, Object... params){
        Class clazz = AnnotationUtils.methodDeepnessAnnotationService();
        String db = AnnotationUtils.methodDeepnessAnnotation();
        List<T> list = null;
        try {
            long start = start();
            list = use(db).query(sql, clazz, params);
            log.info("querySql执行sql,返回数据：{},cost:{}",list,cost(start));
        } catch (SQLException e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
        }
        return list;
    }

    /**
     * 查询单条记录,需要注解注入Class
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static <T> T queryOne(String sql, Object... params)  {
        Class<T> clazz = AnnotationUtils.methodDeepnessAnnotationService();
        String db = AnnotationUtils.dataSourceAnnotation(clazz);
        try {
            long start = start();
            Entity entity = use(db).queryOne(sql, params);
            log.info("queryOne执行sql,返回数据：{},cost:{}",entity,cost(start));
            if (CollUtil.isEmpty(entity))
                return null;
            else return template(entity,clazz);
        } catch (Exception e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
            return null;
        }
    }

    /**
     * 查询单条记录
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Map<String,Object> queryOneAnnotationMap(String sql, Object... params)  {
        String db = AnnotationUtils.methodDeepnessAnnotation();
        return queryOneMap(db,sql,params);
    }

    /**
     * 查询单条记录
     *
     * @param db
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Map<String,Object> queryOneMap(String db, String sql, Object... params)  {
        Map<String,Object> map = new HashMap<>();
        try {
            long start = start();
            Entity entity = use(db).queryOne(sql, params);
            log.warn("queryOneMap执行sql,返回数据：{},cost:{}",entity,cost(start));
            if (CollUtil.isNotEmpty(entity))
                map.putAll(entity);
            return map;
        } catch (Exception e) {
            log.error("qqueryOneMap执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
            return null;
        }
    }

    /**
     * 查询多条记录
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static List<Map<String,Object>> queryListAnnotationMap(String sql, Object... params)  {
        String db = AnnotationUtils.methodDeepnessAnnotation();
        return queryListMap(db,sql,params);
    }

    /**
     * 查询多条记录
     *
     * @param db
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static List<Map<String,Object>> queryListMap(String db, String sql, Object... params)  {
        List<Map<String,Object>> lts = new ArrayList<>();
        try {
            long start = start();
            List<Entity> list = use(db).query(sql, params);
            for (Entity entitys:list){
                if (CollUtil.isNotEmpty(entitys)){
                    Map<String,Object> map = new HashMap<>(entitys.size());
                    map.putAll(entitys);
                    lts.add(map);
                }
            }
            log.info("queryListMap执行sql,返回数据：{},cost:{}",list,cost(start));
            return lts;
        } catch (Exception e) {
            log.error("queryListMap执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
            return null;
        }
    }

    /**
     * 查询单条单个字段记录,并将其转换为String
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static String queryStr(String sql, Object... params)  {
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            String entity = use(db).queryString(sql, params);
            log.info("查询单条单个字段记录,queryStr执行sql,返回数据：{},cost:{}",entity,cost(start));
            return entity;
        } catch (Exception e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
            return null;
        }
    }

    /**
     * 查询单条单个字段记录,并将其转换为Boolean
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Boolean queryBoolean(String sql, Object... params)  {
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            Boolean entity = use(db).query(sql, new BooleanHandler(), params);
            log.info("查询单条单个字段记录,queryBoolean执行sql,返回数据：{},cost:{}",entity,cost(start));
            return entity;
        } catch (Exception e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
            return null;
        }
    }

    /**
     * 查询单条单个字段记录 Int
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Integer queryInt(String sql, Object... params)  {
        try {
            return queryNumber(sql,params).intValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查询单条单个字段记录 byte
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Byte queryByte(String sql, Object... params)  {
        try {
            return queryNumber(sql,params).byteValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查询单条单个字段记录 Double
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Double queryDouble(String sql, Object... params)  {
        try {
            return queryNumber(sql,params).doubleValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查询单条单个字段记录 Float
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Float queryFloat(String sql, Object... params)  {
        try {
            return queryNumber(sql,params).floatValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查询单条单个字段记录 Long
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Long queryLong(String sql, Object... params)  {
        try {
            return queryNumber(sql,params).longValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查询单条单个字段记录 Short
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    public static Short queryShort(String sql, Object... params)  {
        try {
            return queryNumber(sql,params).shortValue();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查询单条单个字段记录,并将其转换为 String
     *
     * @param sql 查询语句
     * @param params 参数
     * @return 结果对象
     * @return
     */
    private static Number queryNumber(String sql, Object... params)  {
        String db = AnnotationUtils.methodDeepnessAnnotation();
        try {
            long start = start();
            Number entity = use(db).queryNumber(sql, params);
            log.info("查询单条单个字段记录,queryNumber执行sql,返回数据：{},cost:{}",entity,cost(start));
            return entity;
        } catch (Exception e) {
            log.error("querySql执行sql错误，sql:{},params{}.",sql, Arrays.toString(params),e);
            return null;
        }
    }

    /**
     * 获取链接
     * @param db
     * @return
     */
    private static Db use(String db){
        Db dbs = null;
        if(CollUtil.isNotEmpty(collection) && collection.containsKey(db))
            dbs = collection.get(db);
        if (StringUtils.isBlank(db)){
            log.info("切换数据源到：{} 失败，重定向到默认数据源：default。",db);
            dbs = collection.get("default");
        }
        return dbs;
    }

    /**
     * sql时间记录
     * @param start
     * @return
     */
    private static long cost(long start){
        return System.currentTimeMillis() - start;
    }

    /**
     * sql开始时间记录
     * @return
     */
    private static long start(){
        return System.currentTimeMillis();
    }

    /**
     * 结果转对象
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> List<T> template(List<Entity> list, Class<T> clazz){
        List<T> lts = null;
        try {
            lts = new ArrayList<>();
            for (Entity entity:list)
                lts.add(entity.toBean(clazz));
        } catch (Exception e) {
            log.error("转换对象出错,请检查Service注解上的类是否正确或没有配置该注解",e);
        }
        return lts;
    }

    /**
     * 结果转对象
     * @param entity
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> T template(Entity entity, Class<T> clazz){
        try {
            return entity.toBean(clazz);
        } catch (Exception e) {
            log.error("转换对象出错,请检查Service注解上的类是否正确或没有配置该注解",e);
        }
        return null;
    }

    /**
     * 通过JSONObject拼接Entity
     * @param tablename
     * @param jsonObject
     * @return
     */
    private static Entity getEntity(Map<String, Object> jsonObject, String... tablename) {
        Entity entity;
        if (tablename.length == 0) entity = Entity.create();
        else entity = Entity.create(tablename[0]);
        entity.putAll(jsonObject);
        return entity;
    }
}

/**
 * 处理为Boolean结果，当查询结果为单个Boolean时使用此处理器
 *
 * @author  duhanmin
 */
class BooleanHandler implements RsHandler<Boolean> {
    /**
     * 创建一个 BooleanHandler对象
     * @return BooleanHandler对象
     */
    public static BooleanHandler create() {
        return new BooleanHandler();
    }

    @Override
    public Boolean handle(ResultSet rs) throws SQLException {
        return rs.next() ? rs.getBoolean(1) : null;
    }
}