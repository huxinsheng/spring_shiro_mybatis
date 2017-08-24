package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.MenuPermissionAddForm;
import com.hxs.ssm.form.core.MenuPermissionQueryForm;
import com.hxs.ssm.model.Permission;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.services.core.MenuPermissionService;
import com.hxs.ssm.web.annotation.Login;
import com.hxs.ssm.web.security.PermissionSign;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuPermissionAction
 * @类描述： 菜单权限管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 08:52
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/menuPermission")
public class MenuPermissionAction {
    @Resource
    MenuPermissionService menuPermissionService;

    @RequestMapping("query")
    @ResponseBody
    public List<Permission> query(@Login UserInfo userInfo, MenuPermissionQueryForm form) {
        return menuPermissionService.query(form);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions(PermissionSign.MENU_ADD_PERMISSION)
    @ResponseBody
    public Result add(@Login UserInfo userInfo, MenuPermissionAddForm form) {
        return menuPermissionService.add(userInfo, form);
    }
}
