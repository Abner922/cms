package com.briup.cms.exception;


import com.briup.cms.utils.Result;
import com.briup.cms.utils.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 当service层抛出业务异常信息时，使用该对象
 * @author SDX
 * @create 2022-10-26 0:22
 */


public class ServiceException extends RuntimeException{
    //设置属性表示异常的原因
    private ResultCode resultCode;

    //为了在全局异常处理器中获取异常原因的异常状态码
    public ServiceException(ResultCode resultCode) {
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
