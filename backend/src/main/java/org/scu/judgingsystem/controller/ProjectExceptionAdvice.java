package org.scu.judgingsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.scu.judgingsystem.Exception.BusinessException;
import org.scu.judgingsystem.Exception.SystemException;
import org.scu.judgingsystem.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e){
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e){
        e.printStackTrace();
        log.error("An exception occurred at {}:{}. Exception: {}",
                e.getStackTrace()[0].getClassName(),
                e.getStackTrace()[0].getMethodName(),
                e.getMessage(),
                e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception e){
        e.printStackTrace();
        log.error("An exception occurred at {}:{}. Exception: {}",
                e.getStackTrace()[0].getClassName(),
                e.getStackTrace()[0].getMethodName(),
                e.getMessage(),
                e);
        return Result.error("系统异常，请联系管理员！");
    }
}
