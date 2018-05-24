package com.wuyifeng.runner.api.controller;

import com.wuyifeng.runner.api.utils.ResultGenerator;
import com.wuyifeng.runner.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局统一异常处理方式
 */
@RestController
public class GolablExceptionEndpoint implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(GolablExceptionEndpoint.class);

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * 全局异常处理
     * 增加@ResponseStatus注解将非200的http状态码转成200
     * @return
     */
    @RequestMapping(value = PATH)
    @ResponseStatus(code= HttpStatus.OK)
    public Result<Object> error(HttpServletRequest request) {
        return handlerError(request, false);
    }

    /**
     * 具体的处理
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Result handlerError(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable e = errorAttributes.getError(requestAttributes);

        Map<String, Object> data = errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);

        String message = null;
        StringBuilder detailMessage = new StringBuilder("");

        HttpStatus status = getStatus(request);
        if(status == HttpStatus.BAD_REQUEST) {
            logger.error("参数校验失败", e);
            message = "请检查数据填写是否合法";
        } else if(status == HttpStatus.INTERNAL_SERVER_ERROR) {
            logger.error("系统错误", e);
            message = "系统繁忙";
        } else if(status == HttpStatus.NOT_FOUND) {
            logger.error("404错误");
            message = "服务或者页面不存在";
        } else {
            logger.error("系统错误", e);
            message = "系统出错,未知错误";
        }
        if(null != data.get("error")) {
            detailMessage.append(String.valueOf(data.get("error"))).append(":");
        }
        if(null == e || null == e.getMessage()) {
            if(null != data.get("message")) {
                detailMessage.append(String.valueOf(data.get("message")));
            }
        } else {
            detailMessage.append(e.getMessage());
        }

        return ResultGenerator.fail(Integer.valueOf(data.get("status").toString()), message, detailMessage.toString());
    }

    /**
     * 获取错误编码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}