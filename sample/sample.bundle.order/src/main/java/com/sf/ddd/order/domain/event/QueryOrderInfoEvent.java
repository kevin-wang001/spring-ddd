package com.sf.ddd.order.domain.event;

import com.sf.ddd.core.Event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询订单明细事件
 * 
 * @author lizhuo 2019年12月9日 下午3:14:15
 */
@Data
public class QueryOrderInfoEvent implements Event {

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "订单ID")
    private Long   orderId = 0l;

}
