package org.scu.judgingsystem.controller.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // System.out.println("preHandle .... ");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // 获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("从请求头中获取的令牌：{}",token);

        // 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("Token不存在");
            //创建响应结果对象
            Result result = Result.error(HttpServletResponse.SC_UNAUTHORIZED, "用户未登录，请登录后进行操作！");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(request);
            //设置响应（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return false;
        }

        // 解析token，如果解析失败，返回错误结果（未登录）
        try {
            DecodedJWT decodedJWT = JwtUtils.decodeJwt(token);
            String usernameJWT = decodedJWT.getClaim("username").asString();
            // 判断JWT解析信息与当前会话用户信息是否相符
            if (!username.equals(usernameJWT)) {
                Result result = Result.error(HttpServletResponse.SC_UNAUTHORIZED, "登录信息错误，请重新登录！");
                String json = JSONObject.toJSONString(request);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(json);
                return false;
            }
        } catch (Exception e){
            log.info("JWT令牌解析失败!");
            Result result = Result.error(HttpServletResponse.SC_UNAUTHORIZED, "登录过期，请重新登录！");
            String json = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return false;
        }

        // 放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion...");
    }
}
