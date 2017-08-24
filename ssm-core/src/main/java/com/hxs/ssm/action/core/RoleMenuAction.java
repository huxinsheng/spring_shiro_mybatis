package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.RoleMenuAddForm;
import com.hxs.ssm.form.core.RoleMenuQueryForm;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.services.core.RoleMenuService;
import com.hxs.ssm.web.annotation.Login;
import com.hxs.ssm.web.security.PermissionSign;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuAction
 * @类描述： 角色菜单管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 09:50
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/roleMenu")
public class RoleMenuAction {
    @Resource
    RoleMenuService roleMenuService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public Object query(@Login UserInfo userInfo, RoleMenuQueryForm form) {
        Object treeJsonModels = roleMenuService.queryByEntity(form);
        return treeJsonModels;
    }

    @RequestMapping(value = "/save")
    @RequiresPermissions(PermissionSign.ROLE_ADD_MENU)
    @ResponseBody
    public Result save(@Login UserInfo userInfo, RoleMenuAddForm form) {
        return roleMenuService.add(userInfo, form);
    }
}
