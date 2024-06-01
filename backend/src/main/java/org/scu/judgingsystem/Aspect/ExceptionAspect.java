package org.scu.judgingsystem.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.scu.judgingsystem.Exception.BusinessException;
import org.scu.judgingsystem.Exception.SystemException;
import org.scu.judgingsystem.result.Code;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ExceptionAspect {
    // 切入点表达式
    @Pointcut("execution(* org.scu.judgingsystem.service.imp.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            log.info("执行方法：" + methodName);
            log.info("方法参数：" + args);
            result = joinPoint.proceed(); // 执行目标方法
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw new SystemException(Code.SYSTEM_ERR, "文件操作失败，请重试！");
            }
            if (e instanceof SQLException) {
                throw new SystemException(Code.SYSTEM_SQL_ERR, "数据库连接超时，请重试！");
            }
            else {
                throw e;
            }
        }
        return result;
    }
}
