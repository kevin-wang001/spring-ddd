package com.sf.ddd.order.facade.impl;

import java.util.ArrayList;
import java.util.List;

import com.sf.ddd.bundle.order.facade.QueryUserOrderFacadeService;
import com.sf.ddd.bundle.order.facade.dto.QueryUserOrderReqDTO;
import com.sf.ddd.core.facade.FacadeService;
import com.sf.ddd.order.repository.OrderMapper;

/**
 * 对bundle外提供服务实现类
 * 
 * @author lizhuo 2019年12月9日 下午3:07:17
 */
@FacadeService(serviceType = QueryUserOrderFacadeService.class)
public class QueryUserOrderFacadeServiceImpl implements QueryUserOrderFacadeService {

    // @Autowired
    private OrderMapper orderMapper;

    //@Override
    public List<String> invoke(QueryUserOrderReqDTO params) {
        String account = params.getAccount();

        List<String> orderIds = new ArrayList<String>();
        if ("test".equals(account)) {
            orderIds.add("001");
            orderIds.add("002");
            orderIds.add("003");
        }

        return orderIds;
    }

}
