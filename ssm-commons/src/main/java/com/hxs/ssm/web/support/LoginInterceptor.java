package com.hxs.ssm.web.support;

import com.hxs.ssm.result.Result;
import com.hxs.ssm.web.HttpContext;
import com.hxs.ssm.web.HttpSession;
import com.hxs.ssm.web.annotation.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {
    private List<String> excludedUrls;
    private List<String> ajaxExcludedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public void setAjaxExcludedUrls(List<String> ajaxExcludedUrls) {
        this.ajaxExcludedUrls = ajaxExcludedUrls;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HttpContext.setCurrentRequest(request);
            boolean requireLogin;
            HandlerMethod method = (HandlerMethod) handler;
            requireLogin = method.getBeanType().getAnnotation(Login.class) != null;
            if (!requireLogin) {
                requireLogin = method.getMethodAnnotation(Login.class) != null;
            }
            if (!requireLogin) {
                MethodParameter[] parameters = method.getMethodParameters();
                for (MethodParameter param : parameters) {
                    if (param.hasParameterAnnotation(Login.class)) {
                        requireLogin = true;
                        break;
                    }
                }
            }
            if (requireLogin) {
                //HttpSession session = HttpContext.getSession();
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                Object userId = session.getAttribute(HttpSession.USER_SESSION_KEY);
                if (userId == null) {
                    ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
                    if (responseBody == null) {
                        String loginUrl = request.getContextPath() + "/login";
                        response.sendRedirect(loginUrl);
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        Result result = Result.error(Result.LOGIN_REQUIRE);
                        response.setCharacterEncoding("utf-8");
                        mapper.writeValue(response.getWriter(), result);
                    }
                    return false;
                }
            } else {
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                Object userId = session.getAttribute(HttpSession.USER_SESSION_KEY);
                if (userId == null) {
                    String url = request.getRequestURI();// 得到请求URL
                    url = url.replace(request.getContextPath(), "");
                    if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                        for (String urls : ajaxExcludedUrls) {
                            if (url.contains(urls)) {
                                return true;
                            }
                        }
                        //如果是ajax请求响应头会有，x-requested-with
                        response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                        ObjectMapper mapper = new ObjectMapper();
                        Result result = Result.error(Result.LOGIN_REQUIRE);
                        response.setCharacterEncoding("utf-8");
                        mapper.writeValue(response.getWriter(), result);
                    } else {
                        for (String urls : excludedUrls) {
                            if (url.contains(urls)) {
                                return true;
                            }
                        }
                        response.sendRedirect(request.getContextPath() + "/login");
                    }
                }
            }
        }
        return true;
    }


    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }


    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
