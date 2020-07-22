/**
 * @(#) IHandler.java Created on 2013-11-26 Copyright (c) 2013 Aspire. All Rights Reserved
 */
package com.sf.ddd.core;

/**
 * 事件处理类接口
 * 
 * @author lizhuo 2017年12月27日 下午1:54:12
 */
public interface IHandler<T, V> {

    public T handle(V event);

}
