/**
 * *@Copyright: 2019 www.pingshiedu.com inc . All rights reserved.
 * *注意：本内容仅限于雅格纳内部传阅，禁止外泄以及用于其他的商业目
 */
package com.ygn.yby.common.canal.util;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import com.ygn.yby.common.canal.annotation.DataSource;
import com.ygn.yby.common.canal.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 注解：AdmBaseAnnotion
 * 获取类的Class对象和类上的注解
 */
@Slf4j
public class AnnotationUtils {

    /**
     * 获取类上的注解
     * @param clazz
     * @param annotationType
     * @return
     */
    public static String clazzAnnotation(Class<?> clazz ,Class<? extends Annotation> annotationType) {
        try {
            return AnnotationUtil.getAnnotationValue(clazz, annotationType);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取类 DataSource 注解
     * @param clazz
     * @return
     */
    public static String dataSourceAnnotation(Class<?> clazz) {
        String value = clazzAnnotation(clazz, DataSource.class);
        if (StringUtils.isBlank(value))
            return methodDeepnessAnnotation();
        else
            return value;
    }

    /**
     * 获取指定深度调用链路方法上的注解DataSource
     * @return
     */
    public static String methodDeepnessAnnotation() {
        String value = null;
        try {
            List<StackTraceElement> colList = CollUtil.newArrayList(Thread.currentThread().getStackTrace());
            for (StackTraceElement ste :colList){
                String methodName = ste.getMethodName();
                Class<?> clazz = ClassUtil.loadClass(ste.getClassName());
                try {
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method:methods) {
                        try {
                            if (method.getName().equals(methodName)){
                                DataSource myMethodAnnotation = method.getAnnotation(DataSource.class);
                                value = myMethodAnnotation.value();
                                if (value != null)
                                    break;
                            }

                        } catch (Exception e) {
                        }
                    }
                    if (value != null)
                        break;
                }catch (Exception e){
                    value = null;
                }
            }
        }catch (Exception e){
            value = "default";
        }finally {
            if (StringUtils.isBlank(value))
                value = "default";
        }
        return value;
    }

    /**
     * 获取指定深度调用链路方法上的注解Service
     * @return
     */
    public static Class methodDeepnessAnnotationService() {
        Class value = null;
        try {
            List<StackTraceElement> colList = CollUtil.newArrayList(Thread.currentThread().getStackTrace());
            for (StackTraceElement ste :colList){
                String methodName = ste.getMethodName();
                Class<?> clazz = ClassUtil.loadClass(ste.getClassName());
                try {
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method:methods) {
                        try {
                            if (method.getName().equals(methodName)){
                                Service myMethodAnnotation = method.getAnnotation(Service.class);
                                value = myMethodAnnotation.value();
                                if ( value != null)
                                    break;
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (value != null)
                        break;
                }catch (Exception e){
                    value = null;
                }
            }
        }catch (Exception e){
            value = null;
        }
        return value;
    }
}