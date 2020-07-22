package com.sf.ddd.bundle.user.facade;

import com.sf.ddd.bundle.user.facade.dto.UserAuthRequestDto;
import com.sf.ddd.core.facade.IFacadeService;

/**
 * 用户认证服务接口，在此定义方法名及参数
 * 
 * @author lizhuo 2019年12月9日 上午11:41:08
 */
public interface UserAuthFacadeService extends IFacadeService<Boolean, UserAuthRequestDto> {
}
