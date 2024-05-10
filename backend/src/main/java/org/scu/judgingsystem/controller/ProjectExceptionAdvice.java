package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result doException(){
        return Result.error("系统异常，请联系管理员！");
    }

}
