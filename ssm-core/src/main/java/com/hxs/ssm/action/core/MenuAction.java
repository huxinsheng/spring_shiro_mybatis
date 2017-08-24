package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.MenuAddForm;
import com.hxs.ssm.form.core.MenuQueryForm;
import com.hxs.ssm.form.core.MenuUpdateForm;
import com.hxs.ssm.model.Menu;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.services.core.MenuService;
import com.hxs.ssm.web.HttpContext;
import com.hxs.ssm.web.HttpSession;
import com.hxs.ssm.web.action.ViewAction;
import com.hxs.ssm.web.annotation.Login;
import com.hxs.ssm.web.security.PermissionSign;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuAction
 * @类描述： 系统菜单管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-06 09:00
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/menu")
public class MenuAction extends ViewAction {

    @Resource(name = "menuService")
    MenuService menuService;

    @RequestMapping
    @RequiresPermissions(PermissionSign.MENU_QUERY)
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = createModelAndView(userInfo);
        return mv;
    }

    @RequestMapping(value = "query")
    @RequiresPermissions(PermissionSign.MENU_QUERY)
    @ResponseBody
    public List<Menu> query(@Login UserInfo userInfo, MenuQueryForm form) {
        return menuService.query(form);
    }

    @RequestMapping(value = "loadUserMenus")
    @ResponseBody
    public List<Menu> loadUserMenus(@Login UserInfo userInfo) {
        List<Menu> menuList = (List<Menu>) HttpContext.getSession().getAttribute(HttpSession.USER_MENU_LIST);
        if (CollectionUtils.isNotEmpty(menuList)) {
            return menuList;
        } else {
            menuList = menuService.loadUserMenu(userInfo);
            HttpContext.getSession().setAttribute(HttpSession.USER_MENU_LIST, menuList);
        }
        return menuList;
    }

    @RequestMapping(value = "add")
    @RequiresPermissions(value = {PermissionSign.MENU_CREATE, PermissionSign.MENU_ADD_SUB_MENU})
    @ResponseBody
    public Result add(@Login UserInfo userInfo, MenuAddForm form) {
        return menuService.add(userInfo, form);
    }

    @RequestMapping(value = "update")
    @RequiresPermissions(PermissionSign.MENU_UPDATE)
    @ResponseBody
    public Result update(@Login UserInfo userInfo, MenuUpdateForm form) {
        return menuService.update(userInfo, form);
    }

    @RequestMapping(value = "delete/{id}")
    @RequiresPermissions(PermissionSign.MENU_DELETE)
    @ResponseBody
    public Result delete(@Login UserInfo userInfo, @PathVariable("id") String id) {
        return menuService.delete(userInfo, id);
    }
}
