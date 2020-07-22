package com.sf.ddd.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sf.ddd.account.domain.event.QueryUserInfoEvent;
import com.sf.ddd.account.domain.event.QueryUserInfoRespEvent;
import com.sf.ddd.core.EventDispatcher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 用户管理控制层 
 * 
 * @author lizhuo 2020年7月22日 下午10:11:30
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户管理控制层")
public class UserController {

    @Autowired
    private EventDispatcher eventDispatcher;

    @ApiOperation(value = "查询用户信息")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "queryUserInfo", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public String queryUserInfo(@RequestBody QueryUserInfoEvent event) {

        QueryUserInfoRespEvent respEvent = (QueryUserInfoRespEvent) eventDispatcher.handle(event);

        return JSON.toJSONString(respEvent);

    }

}
