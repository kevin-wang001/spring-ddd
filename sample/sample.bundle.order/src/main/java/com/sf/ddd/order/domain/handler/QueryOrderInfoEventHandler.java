package com.sf.ddd.order.domain.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.sf.ddd.bundle.user.facade.UserAuthFacadeService;
import com.sf.ddd.bundle.user.facade.dto.UserAuthRequestDto;
import com.sf.ddd.core.DomainEventHandler;
import com.sf.ddd.core.IHandler;
import com.sf.ddd.core.facade.FacadeServiceRegistry;
import com.sf.ddd.order.domain.event.QueryOrderInfoEvent;
import com.sf.ddd.order.repository.OrderMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 订单信息查询事件处理类
 * 
 * @author lizhuo 2019年12月5日 上午10:17:48
 */
@Slf4j
@DomainEventHandler(eventType = QueryOrderInfoEvent.class)
public class QueryOrderInfoEventHandler implements IHandler<String, QueryOrderInfoEvent> {

    // @Autowired
    private OrderMapper           orderMapper;

    @Autowired
    private FacadeServiceRegistry facadeServiceRegistry;

    //@Override
    public String handle(QueryOrderInfoEvent event) {

        // 获取外部模块用户认证接口
        UserAuthFacadeService userAuthFacadeService = (UserAuthFacadeService) facadeServiceRegistry.getService(UserAuthFacadeService.class);
        UserAuthRequestDto userAuthRequestDto = new UserAuthRequestDto();
        userAuthRequestDto.setAccount(event.getAccount());
        userAuthRequestDto.setPassword(event.getPassword());

        // 用户认证结果
        Boolean auth = userAuthFacadeService.invoke(userAuthRequestDto);

        if (!auth) {
            return "用户认证失败，无权限查看订单明细！";
        }

        Long orderId = event.getOrderId();
        // Order order = orderMapper.selectById(orderId);
        // String orderInfo = JSON.toJSONString(order);
        
        String orderInfo = "订单号" + orderId + "的信息是...";

        return orderInfo;
    }
}
