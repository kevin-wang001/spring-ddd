package com.sf.ddd.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sf.ddd.core.EventDispatcher;
import com.sf.ddd.order.domain.event.QueryOrderInfoEvent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 类OrderController.java的实现描述：TODO 类实现描述
 * 
 * @author lizhuo 2019年12月9日 下午5:00:54
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api(description = "订单管理")
public class OrderController {

    @Autowired
    private EventDispatcher eventDispatcher;

    @ApiOperation(value = "查询订单信息")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "queryOrderInfo", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public String queryOrderInfo(@RequestBody QueryOrderInfoEvent event) {

        String orderInfo = (String) eventDispatcher.handle(event);

        return orderInfo;

    }

}
