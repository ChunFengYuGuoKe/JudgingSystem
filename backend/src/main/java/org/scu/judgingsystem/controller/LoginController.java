package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.User;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.LoginService;
import org.scu.judgingsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user, HttpSession session) {
        // 查询用户数据库
        User loginUser = loginService.login(user);

        // 判断用户是否存在
        if (loginUser != null) {
            // 自定义信息
            Map<String, String> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());
            claims.put("identity", user.getIdentity().toString());

            // 使用JWT工具类，生成身份令牌
            String token = JwtUtils.generateJwt(claims);

            session.setAttribute("username", user.getUsername());
            session.setAttribute("identity", user.getIdentity());
            if (user.getIdentity().equals(1)) {
                // 只有学生维护班级信息
                session.setAttribute("class", user.getClazz());
            }

            return Result.success(token);
        }

        return Result.error("用户名或密码错误！");
    }

    @GetMapping
    public Result logOut(HttpSession session) {
        session.invalidate();
        return Result.success("注销成功！");
    }
}
