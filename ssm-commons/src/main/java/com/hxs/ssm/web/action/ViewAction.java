package com.hxs.ssm.web.action;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.web.HttpContext;
import com.hxs.ssm.model.Menu;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ViewAction {
    @Value("#{settings['static.version']}")
    protected String staticVersion;

    protected ModelAndView createModelAndView(UserInfo userInfo) {
        HttpServletRequest request = HttpContext.getRequest();
        String contextPath = request.getContextPath();
        String path = request.getRequestURI();
        if (contextPath.length() > 0) {
            path = path.substring(contextPath.length());
        }
        String viewName = getMenuByUrl(path, userInfo.getMenus());
        //Menu menu = menuService.findByUrl(path);
        if (StringUtils.isEmpty(viewName)) {
            throw new AuthorizationException("菜单视图不存在，请联系管理员添加菜单");
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("user", userInfo);
        mv.addObject("version", staticVersion);
        return mv;
    }

    private String getMenuByUrl(String url, List<Menu> userMenus) {
        String viewName = null;
        if (CollectionUtils.isEmpty(userMenus)) {
            return viewName;
        }
        Set<Menu> menuSet = new LinkedHashSet<Menu>();
        for (Menu menu : userMenus) {
            menuSet.add(menu);
            if(CollectionUtils.isNotEmpty(menu.getSubMenus())){
                menuSet.addAll(menu.getSubMenus());
            }
        }
        for (Menu menu : menuSet) {
            if (!StringUtils.isEmpty(menu.getUrl()) && menu.getUrl().equals(url)) {
                viewName = menu.getView();
            }
        }
        return viewName;
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public boolean authorizationException(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjaxRequest(request)) {
            ObjectMapper mapper = new ObjectMapper();
            Result result = Result.error(Result.MENU_NON_PRIVILEGE_ERROR);
            response.setCharacterEncoding("utf-8");
            mapper.writeValue(response.getWriter(), result);
        }
        return true;
    }

    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return
     * @author SHANHY
     * @create 2017年4月4日
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
