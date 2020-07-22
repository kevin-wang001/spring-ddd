package com.sf.ddd.bundle.user.facade.dto;

import lombok.Data;

/**
 * 模块2接口服务请求参数
 * 
 * @author lizhuo 2019年12月9日 下午2:25:39
 */
@Data
public class UserAuthRequestDto {

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;
}
