package com.sf.ddd.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 类EventDispatcher.java的实现描述：TODO 类实现描述
 * 
 * @author lizhuo 2019年5月28日 下午1:37:00
 */
@Component
public class EventDispatcher extends ApplicationObjectSupport implements ApplicationContextAware {

    /** 领域事件处理类集合 */
    private Map<Class<? extends Event>, IHandler<?, ?>> eventHandlerMap  = new HashMap<Class<? extends Event>, IHandler<?, ?>>();

    private ExecutorService                             cachedThreadPool = Executors.newCachedThreadPool();

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {

        super.initApplicationContext(context);

        Map<String, Object> eventHandlerBeanMap = context.getBeansWithAnnotation(DomainEventHandler.class);
        eventHandlerBeanMap.keySet().forEach(beanName -> {
            Object bean = eventHandlerBeanMap.get(beanName);
            @SuppressWarnings("unchecked")
            Class<IHandler<?, ?>> clazz = (Class<IHandler<?, ?>>) bean.getClass();
            // 事务模式下需要使用此方式获取注解
            DomainEventHandler findAnnotation = AnnotationUtils.findAnnotation(clazz, DomainEventHandler.class);
            Class<? extends Event> eventType = findAnnotation.eventType();

            // Annotation annotation = clazz.getDeclaredAnnotation(DomainEventHandler.class);
            // Class<? extends Event> eventType = ((DomainEventHandler) annotation).eventType();

            eventHandlerMap.put(eventType, (IHandler<?, ?>) bean);

        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object handle(Event event) {
        Class<? extends Event> clazz = event.getClass();
        IHandler eventHandler = eventHandlerMap.get(clazz);

        return eventHandler.handle(event);

    }

    /**
     * 异步处理事件
     * 
     * @param event
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void asynHandle(Event event) {

        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() { 
                try {
                    //Thread.sleep(1000);
                    
                    Class<? extends Event> clazz = event.getClass();
                    IHandler eventHandler = eventHandlerMap.get(clazz);

                    eventHandler.handle(event);
                } catch (Exception e) {
                    logger.info("异步事件调用异常：" + JSON.toJSONString(event), e);
                }
            }
        });
    }

}
