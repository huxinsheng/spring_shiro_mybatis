package com.hxs.ssm.web.support;

import com.hxs.ssm.web.HttpSession;
import com.hxs.ssm.web.annotation.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SessionArgumentResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class);
    }

    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Object value = null;
        //HttpSession session = HttpContext.getSession();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (parameter.hasParameterAnnotation(Login.class)) {
            value = session.getAttribute(HttpSession.USER_SESSION_KEY);
        }
        return value;
    }

}
