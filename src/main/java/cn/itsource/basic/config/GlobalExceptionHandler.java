package cn.itsource.basic.config;

import cn.itsource.basic.uitl.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Spring统一处理异常（全局异常处理），底层aop
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * 请求方式不支持
     * 405被他处理
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public JsonResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     * 只要程序处理RuntimeException，就会进入这里处理
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return JsonResult.fail("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail("服务器错误，请联系管理员");
    }

    /**
     * 校验异常
     * 400进入这里
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResult exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return JsonResult.fail(errorMesssage);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public JsonResult validationExceptionHandler(BindException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return JsonResult.fail(errorMesssage);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public JsonResult ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return JsonResult.fail(String.join(",",msgList));
    }

    /**
     * 业务异常
     * 自定义异常
     */
//    @ExceptionHandler(BusinessException.class)
//    public JsonResult businessException(BusinessException e) {
//        log.error(e.getMessage(), e);
//        return JsonResult.createError(e.getMessage());
//    }
}
