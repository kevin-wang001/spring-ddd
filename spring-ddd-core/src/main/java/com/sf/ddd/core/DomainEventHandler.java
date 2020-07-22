package com.sf.ddd.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 领域事件处理类注解
 * 
 * @author lizhuo 2017年12月27日 下午1:59:14
 */
@Target({ ElementType.TYPE,ElementType.PARAMETER,ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DomainEventHandler {

    public Class<? extends Event> eventType();
}
