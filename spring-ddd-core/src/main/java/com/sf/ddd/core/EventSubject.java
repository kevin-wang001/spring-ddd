package com.sf.ddd.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件主题，每种类型的事件对应一个消费主题
 * 
 * @author lizhuo 2017年12月27日 下午4:13:45
 */
public abstract class EventSubject<T extends EventListener, V extends Event> {

    /** 事件监听者队列 */
    protected List<T> eventListenerQueue = new ArrayList<T>();

    abstract public void notifyListener(V event);

    public void attach(T t) {
        eventListenerQueue.add(t);
    };

    public void detach(T t) {
        eventListenerQueue.remove(t);
    };

    public boolean contains(T t) {
        return eventListenerQueue.contains(t);
    }

    public List<T> getEventListenerQueue() {
        return eventListenerQueue;
    }

}
