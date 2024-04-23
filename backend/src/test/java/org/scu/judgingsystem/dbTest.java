package org.scu.judgingsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.scu.judgingsystem.dao.UserDao;
import org.scu.judgingsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class dbTest {
    @Autowired
    UserDao userDao;

    @Test
    public void testUser(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", "2021141410001");
        System.out.println(userDao.selectOne(qw));
    }
}
