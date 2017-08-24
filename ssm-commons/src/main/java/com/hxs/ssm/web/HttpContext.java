package com.hxs.ssm.web;

import com.hxs.ssm.web.session.SessionTomcatImpl;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log4j
public class HttpContext {

    private static ApplicationContext currentContext;

    private static ThreadLocal<HttpServletRequest> requestLocal;

    static {
        requestLocal = new ThreadLocal<HttpServletRequest>();
    }

    static void setApplicationContext(ApplicationContext context) {
        currentContext = context;
    }

    public static void setCurrentRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletRequest getRequest() {
//		ServletRequestAttributes requestAttrs;
//		requestAttrs = (ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes();
//		HttpServletRequest request = requestAttrs.getRequest();
//		return request;
        return requestLocal.get();
    }

    public static ApplicationContext getApplicationContext() {
        return currentContext;
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        return new SessionTomcatImpl(request.getSession());
    }

    public static <T> T getBean(Class<T> cls) {
        ApplicationContext ctx = getApplicationContext();
        return ctx.getBean(cls);
    }

    public static String getUrlRoot() {
        HttpServletRequest request = getRequest();
        String host = request.getHeader("host");
        String path = request.getContextPath();
        return "https://" + host + (path.length() > 0 ? path : "");
    }

    public static String getContextPath() {
        HttpServletRequest request = getRequest();
        return request.getContextPath();
    }

    public static String getRequestUrl(HttpServletRequest request) {
        String host = request.getHeader("host");
        StringBuffer requestUrl = request.getRequestURL();
        int index = requestUrl.indexOf("://") + 3;
        int index2 = requestUrl.indexOf("/", index);
        requestUrl.replace(index, index2, host);
        String queryString = request.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            requestUrl.append("?").append(queryString);
        }
        String url = requestUrl.toString();
        log.info("request url in =" + url);
        String scheme = url.substring(0, url.indexOf(":"));
        if (!scheme.equals("https")) {
            url = "https" + url.substring(url.indexOf(":"), url.length());
        }
        log.info("request url out =" + url);
        return url;
    }

    /**
     * 获取访问者IP
     * <p>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public static String getRequestIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
        } else {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取所有request请求参数key-value
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }
}
