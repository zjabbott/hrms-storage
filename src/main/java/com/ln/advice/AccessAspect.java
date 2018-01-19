package com.ln.advice;

import com.ln.model.User;
import com.ln.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class AccessAspect {

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.ln.advice.AccessCheck)")
    public void serviceBefore() {
    }

    @Before("serviceBefore()")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        //Parameter[] parameters = method.getParameters();
        HttpServletRequest request = (HttpServletRequest) args[0];
        Long id = Long.parseLong(request.getHeader("id"));
        String token = request.getHeader("token");
        User user = userService.get(id);
        if (user == null) {
            throw new IllegalAccessError("用户不存在");
        }
        if (!userService.checkToken(id, token)) {
            throw new IllegalAccessError("登录过期,请重新登录");
        }
        AccessCheck accessCheck = method.getDeclaredAnnotation(AccessCheck.class);
        int permit = accessCheck.value();
        if ((permit & user.getPermit()) != permit) {
            throw new IllegalAccessError("没有操作权限");
        }
    }
}
