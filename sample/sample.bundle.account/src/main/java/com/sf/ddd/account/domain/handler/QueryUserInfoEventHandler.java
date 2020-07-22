package com.sf.ddd.account.domain.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sf.ddd.account.domain.event.QueryUserInfoEvent;
import com.sf.ddd.account.domain.event.QueryUserInfoRespEvent;
import com.sf.ddd.account.repository.UserMapper;
import com.sf.ddd.bundle.order.facade.QueryUserOrderFacadeService;
import com.sf.ddd.bundle.order.facade.dto.QueryUserOrderReqDTO;
import com.sf.ddd.core.DomainEventHandler;
import com.sf.ddd.core.IHandler;
import com.sf.ddd.core.facade.FacadeServiceRegistry;

import lombok.extern.slf4j.Slf4j;

/**
 * 查询用户信息事件处理类
 * 
 * @author lizhuo 2019年12月5日 上午10:17:48
 */
@Slf4j
@DomainEventHandler(eventType = QueryUserInfoEvent.class)
public class QueryUserInfoEventHandler implements IHandler<QueryUserInfoRespEvent, QueryUserInfoEvent> {

    // @Autowired
    private UserMapper            userMapper;

    @Autowired
    private FacadeServiceRegistry facadeServiceRegistry;

    //@Override
    public QueryUserInfoRespEvent handle(QueryUserInfoEvent event) {
        QueryUserInfoRespEvent respEvent = new QueryUserInfoRespEvent();

        String account = event.getAccount();
        respEvent.setAccount(account);
        respEvent.setName("小明");

        QueryUserOrderFacadeService queryUserOrderFacadeService = (QueryUserOrderFacadeService) facadeServiceRegistry.getService(QueryUserOrderFacadeService.class);
        QueryUserOrderReqDTO queryUserOrderReqDto = new QueryUserOrderReqDTO();
        queryUserOrderReqDto.setAccount(event.getAccount());

        List<String> orderIds = queryUserOrderFacadeService.invoke(queryUserOrderReqDto);

        respEvent.setOrderIds(orderIds);

        return respEvent;
    }
}
