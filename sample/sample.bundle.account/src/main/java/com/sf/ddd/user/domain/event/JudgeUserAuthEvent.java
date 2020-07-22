package com.sf.ddd.user.domain.event;

import com.sf.ddd.core.Event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户认证事件
 * 
 * @author lizhuo 2019年12月9日 下午4:25:06
 */
@Data
public class JudgeUserAuthEvent implements Event {

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;
}
