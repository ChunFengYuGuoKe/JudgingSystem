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

    /**
     * 登录操作
     * @param user 用户信息
     * @param session HTTP会话
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user, HttpSession session) {
        User loginUser = loginService.login(user);

        // 若存在符合条件的用户，则生成JWT令牌并返回
        if (loginUser != null) {
            // 定义JWT令牌加密信息
            Map<String, String> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());
            claims.put("identity", user.getIdentity().toString());

            // 生成JWT令牌
            String token = JwtUtils.generateJwt(claims);

            // 设置HttpSession信息
            session.setAttribute("username", user.getUsername());
            session.setAttribute("identity", user.getIdentity());
            // 只有学生维护班级信息
            if (user.getIdentity().equals(1)) {
                session.setAttribute("class", user.getClazz());
            }

            return Result.success(token);
        }

        // 不存在符合条件的用户
        return Result.error("用户名或密码错误！");
    }

    /**
     * 注销登录
     * @param session Http会话
     */
    @GetMapping("/logout")
    public Result logOut(HttpSession session) {
        session.invalidate();
        return Result.success("注销成功！");
    }
}
