package com.ygn.yby.common.canal.annotation;

import com.ygn.yby.common.canal.config.CanalClientConfiguration;
import com.ygn.yby.common.canal.config.CanalConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enables the canal client
 *
 * @author duhanmin
 * @date 2018/3/19
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CanalConfig.class, CanalClientConfiguration.class})
public @interface EnableCanalClient {
}
