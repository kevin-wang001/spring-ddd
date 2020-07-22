package com.sf.ddd.user.domain.event;

import java.util.List;

import com.sf.ddd.core.Event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户认证事件
 * 
 * @author lizhuo 2019年12月9日 下午4:25:06
 */
@Data
public class QueryUserInfoRespEvent implements Event {

    @ApiModelProperty(value = "用户账号")
    private String       account;

    @ApiModelProperty(value = "用户姓名")
    private String       name;

    @ApiModelProperty(value = "用户关联订单号")
    private List<String> orderIds;
}
