package com.sf.ddd.core.facade;

public interface IFacadeService<T, V> {
	public abstract T invoke(V params);
}
