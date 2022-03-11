package com.tams.apsectj;

import com.tams.annotation.Permission;
import com.tams.dto.AjaxResult;
import com.tams.exception.base.BusinessException;
import com.tams.service.RightTypeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author swiChen
 * @date 2022/3/11
 **/

@Configuration
@Aspect
@Slf4j
public class RightAuthAspect {


    @Resource
    private RightTypeService rightTypeService;

    @Pointcut("execution( * com.tams.controller.*.* (..))")
    public void rightPointcut(){};

    @Around("rightPointcut()")
    public Object rightAuthPreHandle(ProceedingJoinPoint jp)  {

        Signature signature = jp.getSignature();
        Method method = ( (MethodSignature) signature).getMethod();
        boolean isPerAuth = method.isAnnotationPresent(Permission.class);
        if (isPerAuth){
             Permission permission = method.getDeclaredAnnotation(Permission.class);
             if (permission.isLog()){
                 log.info("permission --> class: [{}] method: [{}]  right: [{}] operation: [{}]", jp.getTarget().getClass().getName() , method.getName() , permission.right() , permission.operation());
             }
            boolean b = rightTypeService.verifyRight(permission);
            if (!b){
                return AjaxResult.error(HttpStatus.FORBIDDEN.value(), "权限不足 请联系管理员");
            }
        }

        Object result = null;
        try {
           result = jp.proceed(jp.getArgs());
        }
        catch (BusinessException e){
            throw new BusinessException( e.getMsg() ,e.getCode() );
        }
        catch (Throwable e) {
            throw new BusinessException(e.getMessage());
        }
        return result;
    }


}
