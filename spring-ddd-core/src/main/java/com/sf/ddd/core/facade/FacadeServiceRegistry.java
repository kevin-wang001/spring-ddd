package com.sf.ddd.core.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * 对外服务实现类注册中心
 * 
 * @author lizhuo 2019年6月17日 下午1:48:18
 */
@Component
public class FacadeServiceRegistry extends ApplicationObjectSupport implements ApplicationContextAware {

    /** 对外服务处理类集合 */
    @SuppressWarnings("rawtypes")
    private Map<Class<? extends IFacadeService>, IFacadeService> facadeServiceMap = new HashMap<Class<? extends IFacadeService>, IFacadeService>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {

        super.initApplicationContext(context);

        Map<String, Object> eventHandlerBeanMap = context.getBeansWithAnnotation(FacadeService.class);

        eventHandlerBeanMap.keySet().forEach(beanName -> {
            Object bean = eventHandlerBeanMap.get(beanName);
            Class<? extends IFacadeService> clazz = (Class<? extends IFacadeService>) bean.getClass();

            // 事务模式下需要使用此方式获取注解
            FacadeService findAnnotation = AnnotationUtils.findAnnotation(clazz, FacadeService.class);
            Class<? extends IFacadeService> serviceType = findAnnotation.serviceType();

            facadeServiceMap.put(serviceType, (IFacadeService)bean);

        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T extends IFacadeService> T getService(Class<T> serviceType) {
        IFacadeService facadeService = facadeServiceMap.get(serviceType);
        return (T)facadeService;
    }

}
