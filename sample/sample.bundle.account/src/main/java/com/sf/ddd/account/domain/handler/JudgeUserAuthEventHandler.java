package com.sf.ddd.account.domain.handler;

import com.sf.ddd.account.domain.event.JudgeUserAuthEvent;
import com.sf.ddd.account.repository.UserMapper;
import com.sf.ddd.core.DomainEventHandler;
import com.sf.ddd.core.IHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户认证事件处理类
 * 
 * @author lizhuo 2019年12月5日 上午10:17:48
 */
@Slf4j
@DomainEventHandler(eventType = JudgeUserAuthEvent.class)
public class JudgeUserAuthEventHandler implements IHandler<Boolean, JudgeUserAuthEvent> {

    // @Autowired
    private UserMapper userMapper;

    //@Override
    public Boolean handle(JudgeUserAuthEvent event) {
        String account = event.getAccount();
        String password = event.getPassword();

        // 此处添加用户认证业务逻辑
        if ("test".equals(account) && "test123".equals(password)) {
            return true;
        }

        return false;
    }
}
