package com.briup.cms.exception;


import com.briup.cms.utils.Result;
import com.briup.cms.utils.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author SDX
 * @create 2022-10-26 10:11
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(Exception ex){
        //当系统发生错误，进行错误信息打印
        ex.printStackTrace();


        //当异常为Service层抛出自定义的类型ServiceException
        if(ex instanceof ServiceException){
            return Result.failure(((ServiceException) ex).getResultCode());
        }

        //当异常为运行时异常，返回用户错误的原因。
        return Result.failure(1,ex.getMessage());
    }
}
