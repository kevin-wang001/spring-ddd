package com.sf.ddd.account.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sf.ddd.account.domain.event.JudgeUserAuthEvent;
import com.sf.ddd.bundle.user.facade.UserAuthFacadeService;
import com.sf.ddd.bundle.user.facade.dto.UserAuthRequestDto;
import com.sf.ddd.core.EventDispatcher;
import com.sf.ddd.core.facade.FacadeService;

/**
 * 对bundle外提供服务实现类
 * 
 * @author lizhuo 2019年12月9日 下午3:07:17
 */
@FacadeService(serviceType = UserAuthFacadeService.class)
public class UserAuthFacadeServiceImpl implements UserAuthFacadeService {

    @Autowired
    private EventDispatcher eventDispatcher;

    //@Override
    public Boolean invoke(UserAuthRequestDto params) {

        JudgeUserAuthEvent judgeUserAuthEvent = new JudgeUserAuthEvent();
        judgeUserAuthEvent.setAccount(params.getAccount());
        judgeUserAuthEvent.setPassword(params.getPassword());
        
        Boolean result = (Boolean) eventDispatcher.handle(judgeUserAuthEvent);
        
        return result;
    }

}
