package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginService {
    /**
     * 登录验证
     * @param user 用户信息
     * @return 返回查询结果
     */
    public User login(User user);
}
