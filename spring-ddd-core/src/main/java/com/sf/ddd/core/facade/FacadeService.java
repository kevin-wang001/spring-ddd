package com.sf.ddd.core.facade;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 类FacadeService.java的实现描述：TODO 类实现描述
 * 
 * @author lizhuo 2019年6月17日 下午3:07:36
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface FacadeService {

    @SuppressWarnings("rawtypes")
    public Class<? extends IFacadeService> serviceType();
}
