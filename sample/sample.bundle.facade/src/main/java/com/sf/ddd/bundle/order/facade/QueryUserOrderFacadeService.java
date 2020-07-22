package com.sf.ddd.bundle.order.facade;

import java.util.List;

import com.sf.ddd.bundle.order.facade.dto.QueryUserOrderReqDTO;
import com.sf.ddd.core.facade.IFacadeService;

/**
 * 订单模块对外服务接口，在此定义方法名及参数
 * 
 * @author lizhuo 2019年12月9日 上午11:41:08
 */
public interface QueryUserOrderFacadeService extends IFacadeService<List<String>, QueryUserOrderReqDTO> {
}
