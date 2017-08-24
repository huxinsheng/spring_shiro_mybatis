package com.hxs.ssm.web.security;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.LoginForm;
import com.hxs.ssm.model.Menu;
import com.hxs.ssm.model.core.Role;
import com.hxs.ssm.model.core.User;
import com.hxs.ssm.services.core.MenuService;
import com.hxs.ssm.services.core.RoleMenuPermissionService;
import com.hxs.ssm.services.core.UserService;
import com.hxs.ssm.web.HttpContext;
import com.hxs.ssm.web.HttpSession;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户身份验证,授权 Realm 组件
 *
 * @author HuXinsheng
 * @since 2016-03-15 10:43
 **/
@Component(value = "securityRealm")
@Log4j
public class SecurityRealm extends AuthorizingRealm {

    private static final String OR_OPERATOR = " or ";

    private static final String AND_OPERATOR = " and ";

    private static final String NOT_OPERATOR = "not ";

    @Resource
    UserService userService;

    @Resource
    MenuService menuService;

    @Resource
    RoleMenuPermissionService roleMenuPermissionService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        DefaultAuthorizationInfo authorizationInfo = new DefaultAuthorizationInfo();
        String loginId = String.valueOf(principals.getPrimaryPrincipal());
        try {
            final User user = userService.findByLoginId(loginId);
            if (null != user) {
                final List<Role> roleList = user.getRoles();
                if (CollectionUtils.isNotEmpty(roleList)) {
                    Set<String> roleSigns = new HashSet<String>();
                    Set<String> roleCodes = new HashSet<String>();
                    for (Role role : roleList) {
                        roleSigns.add(role.getSign());
                        roleCodes.add(role.getId());
                    }
                    List<String> list = new ArrayList<String>(roleCodes);
                    List<String> permissionSignList = roleMenuPermissionService.findByRoleIds(list);
                    authorizationInfo.addStringPermissions(permissionSignList);
                    authorizationInfo.addRoles(roleSigns);
                }
            }
            return authorizationInfo;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String loginId = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final User authentication = userService.authentication(new LoginForm(loginId, password));
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        if (authentication.isFirstLogin()) {
            throw new AuthenticationException("firstLogin");
        } else if (authentication.isDisabled()) {
            throw new AuthenticationException("该用户已经禁用.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginId, password, getName());
        UserInfo userInfo = authentication.getLoginInfo();
        if (null == userInfo) {
            userInfo = new UserInfo();
            userInfo.setId(authentication.getId());
            userInfo.setName(authentication.getName());
            userInfo.setLoginId(authentication.getLoginId());
            userInfo.setLoginTime(new Date());
        }
        List<Menu> menuList = menuService.loadUserMenu(userInfo);
        userInfo.setMenus(menuList);
        this.setSession(userInfo);
        return authenticationInfo;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     */
    private void setSession(UserInfo userInfo) {
        //处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for (Session session : sessions) {
            //清除该用户以前登录时保存的session
            if (userInfo.getLoginId().equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                sessionManager.getSessionDAO().delete(session);
            }
        }
        Subject subject = SecurityUtils.getSubject();
        //HttpSession session = HttpContext.getSession();
        userInfo.setIp(HttpContext.getRequestIpAddr(HttpContext.getRequest()));
        subject.getSession().setAttribute(HttpSession.USER_SESSION_KEY, userInfo);
        userService.addLoginInfo(userInfo);
    }

    /**
     * 支持or and not 关键词  不支持and or混用
     *
     * @param principals
     * @param permission
     * @return
     */
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }
}
