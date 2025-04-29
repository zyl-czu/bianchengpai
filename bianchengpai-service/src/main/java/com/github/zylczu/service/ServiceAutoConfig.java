package com.github.zylczu.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author YiHui
 * @date 2022/7/6
 */
@Configuration
@ComponentScan("com.github.zylczu.service")
@MapperScan(basePackages = {
        "com.github.zylczu.service.article.repository.mapper",
        "com.github.zylczu.service.user.repository.mapper",
        "com.github.zylczu.service.comment.repository.mapper",
        "com.github.zylczu.service.config.repository.mapper",
        "com.github.zylczu.service.statistics.repository.mapper",
        "com.github.zylczu.service.notify.repository.mapper",})
public class ServiceAutoConfig {


}
