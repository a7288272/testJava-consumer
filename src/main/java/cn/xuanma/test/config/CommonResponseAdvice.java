package cn.xuanma.test.config;

import cn.xuanma.common.annotation.NoResultFormatter;
import cn.xuanma.common.enums.BizErrorEnum;
import cn.xuanma.common.vo.RetResult;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        NoResultFormatter noResultFormatter = AnnotationUtils.findAnnotation(Objects.requireNonNull(methodParameter.getMethod()), NoResultFormatter.class);
        if(noResultFormatter!=null){
            return o;
        }
        if (o instanceof RetResult) {
            return o;
        } else {
            RetResult<Object> retResult = RetResult.success(o);
            ApiOperation apiOperation = AnnotationUtils.findAnnotation(Objects.requireNonNull(methodParameter.getMethod()), ApiOperation.class);
            if (apiOperation != null) {
                retResult.setMsg(apiOperation.value() + BizErrorEnum.SUCCESS.getMsg());
            }
            if (mediaType.equals(MediaType.TEXT_HTML)) {
                return JSONObject.toJSONString(retResult);
            }
            return retResult;
        }
    }
}
