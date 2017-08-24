package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.UserAddForm;
import com.hxs.ssm.form.core.UserQueryForm;
import com.hxs.ssm.form.core.UserUpdateForm;
import com.hxs.ssm.model.core.User;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.result.TableResult;
import com.hxs.ssm.services.core.UserService;
import com.hxs.ssm.web.HttpContext;
import com.hxs.ssm.web.HttpSession;
import com.hxs.ssm.web.action.ViewAction;
import com.hxs.ssm.web.annotation.Login;
import com.hxs.ssm.web.security.PermissionSign;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/core/user")
@Log4j
public class UserAction extends ViewAction {

    private final static int misLimitTimes = 5;
    private final static int lockLimitMinutes = 10;

    @Resource
    private UserService userService;


    @RequestMapping
    @RequiresPermissions(PermissionSign.USER_QUERY)
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = createModelAndView(userInfo);
        return mv;
    }

    @RequestMapping("login")
    public String login(@RequestParam("loginId") String loginId, @RequestParam("password") String password, RedirectAttributes attr) {
        HttpSession session = HttpContext.getSession();
        String userSessionKey = HttpSession.USER_SESSION_KEY;
        String misSessionKey = userSessionKey + "_mistimes_" + loginId;
        Integer misTimes = (Integer) session.getAttribute(misSessionKey);
        if (misTimes == null)
            misTimes = 0;
        String lockSessionKey = userSessionKey + "_locktime";
        if (misTimes >= misLimitTimes) {
            long nowTime = new Date().getTime();
            Long lockTime = (Long) session.getAttribute(lockSessionKey);
            if (lockTime == null) lockTime = 0L;
            long lockedMinutes = (nowTime - lockTime) / 1000 / 60;
            long diff = lockLimitMinutes - lockedMinutes;
            if (diff > 0) {
                attr.addFlashAttribute("errorMsg", "登录次数过多，账户锁定【" + diff + "】分钟");
                return "redirect:/login";
            }
        }
        try {
            Subject subject = SecurityUtils.getSubject();

            String sessionId = "";
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                session.setAttribute(misSessionKey, 0);
                return "redirect:/";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(loginId, password);
            // 身份验证
            subject.login(token);
            sessionId = subject.getSession().getId().toString();
            log.info("登录成功返回的sessionId+++++++++++++" + sessionId);
        } catch (AuthenticationException e) {
            if (e.getMessage().indexOf("firstLogin") != -1) {
                attr.addFlashAttribute("loginId", loginId);
                attr.addFlashAttribute("firstLogin", true);
                session.setAttribute(lockSessionKey, null);
                return "redirect:/login";
            } else if (e.getMessage().indexOf("load") != -1) {
                String s = e.getMessage().split("\\|")[1];
                attr.addFlashAttribute("errorMsg", s);
                session.setAttribute(lockSessionKey, null);
                return "redirect:/login";
            }
            misTimes++;
            session.setAttribute(misSessionKey, misTimes);
            if (misTimes >= misLimitTimes) {
                long nowTime = new Date().getTime();
                session.setAttribute(lockSessionKey, nowTime);
                // 身份验证失败
                attr.addFlashAttribute("errorMsg", "账号已锁定，请10分钟后再尝试登录。");
            } else {
                session.setAttribute(lockSessionKey, null);
                // 身份验证失败
                attr.addFlashAttribute("errorMsg", e.getMessage() + " ！您还有【" + (misLimitTimes - misTimes) + "】次尝试机会;【5】次登录失败将锁定10分钟。");
            }

            return "redirect:/login";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // 身份验证失败
            attr.addFlashAttribute("errorMsg", "登录发生异常【" + e.getMessage() + "】");
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.USER_QUERY)
    @RequestMapping("query")
    public TableResult<User> query(@Login UserInfo userInfo, UserQueryForm form) {
        return userService.query(form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.USER_CREATE)
    @RequestMapping("add")
    public Result query(@Login UserInfo userInfo, UserAddForm form) {
        return userService.add(userInfo, form);
    }

    @ResponseBody
    @RequestMapping("sync/{loginId}/{name}/{status}")
    public Result sync(@PathVariable("loginId") String loginId, @PathVariable("name") String name, @PathVariable("status") String status) {
        return userService.sync(loginId, name, status);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.USER_UPDATE)
    @RequestMapping("update")
    public Result update(@Login UserInfo userInfo, UserUpdateForm form) {
        return userService.update(userInfo, form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.USER_DELETE)
    @RequestMapping(value = "delete/{id}")
    public Result delete(@Login UserInfo userInfo, @PathVariable("id") String id) {
        return userService.delete(userInfo, id);
    }

    @RequestMapping(value = "/checkPassword")
    @ResponseBody
    public Result checkPassword(@Login UserInfo userInfo, String oldPassword) {
        UserQueryForm form = new UserQueryForm();
        form.setPagination(false);
        form.setId(userInfo.getId());
        form.setLoginId(userInfo.getLoginId());
        form.setPassword(oldPassword);
        return userService.checkPassword(form);
    }

    @RequestMapping(value = "/modifyPassword")
    @RequiresPermissions(PermissionSign.USER_MODIFY_PASSWORD)
    @ResponseBody
    public Result modifyPassword(@Login UserInfo userInfo, String password) {
        UserUpdateForm form = new UserUpdateForm();
        form.setId(userInfo.getId());
        form.setPassword(password);
        return userService.update(userInfo, form);
    }

    /**
     * 重置/设置合作商密码
     *
     * @param form
     * @return
     */
    @RequestMapping("resetPassword")
    @ResponseBody
    public Result resetPassword(UserUpdateForm form) {
        Result result = userService.resetPassword(form);
        if (result.isOK()) {
            HttpSession session = HttpContext.getSession();
            String userSessionKey = HttpSession.USER_SESSION_KEY;
            String lockSessionKey = userSessionKey + "_locktime";
            session.setAttribute(lockSessionKey, null);

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(form.getLoginId(), form.getPassword());
            // 身份验证
            subject.login(token);
        }
        return result;
    }
}
