package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.web.ViewNames;
import com.hxs.ssm.web.action.ViewAction;
import com.hxs.ssm.web.annotation.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class IndexAction extends ViewAction {
    @Value("#{settings['static.version']}")
    private String staticVersion;
    @Login
    @RequestMapping
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = new ModelAndView(ViewNames.INDEX_VIEW);
        mv.addObject("user", userInfo);
        mv.addObject("version", staticVersion);
        return mv;
    }

    @RequestMapping("404")
    public ModelAndView error404(@Login UserInfo userInfo) {
        return new ModelAndView(ViewNames.ERROR_404);
    }

    @RequestMapping("print")
    public String index() {
        return "print";
    }

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            subject.logout();
        }
        return "redirect:/login";
    }

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView(ViewNames.LOGIN_VIEW);
    }
}
