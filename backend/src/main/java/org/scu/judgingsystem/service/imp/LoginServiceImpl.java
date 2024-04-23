package org.scu.judgingsystem.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.scu.judgingsystem.dao.UserDao;
import org.scu.judgingsystem.pojo.User;
import org.scu.judgingsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        // 调用查询接口
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername())
                .eq("password", user.getPassword())
                .eq("identity", user.getIdentity());

        // 返回查询结果
        return userDao.selectOne(qw);
    }
}
