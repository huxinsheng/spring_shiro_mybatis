package com.hxs.ssm.action.core;


import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.RoleMenuPermissionAddForm;
import com.hxs.ssm.form.core.RoleMenuPermissionQueryForm;
import com.hxs.ssm.model.core.RoleMenuPermission;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.services.core.RoleMenuPermissionService;
import com.hxs.ssm.web.annotation.Login;
import com.hxs.ssm.web.security.PermissionSign;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuPermissionAction
 * @类描述： 角色菜单权限管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 18:49
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/roleMenuPermission")
public class RoleMenuPermissionAction {
    @Resource
    RoleMenuPermissionService roleMenuPermissionService;

    @RequestMapping("/query")
    @ResponseBody
    public List<RoleMenuPermission> query(@Login UserInfo userInfo, RoleMenuPermissionQueryForm roleMenuPermission) {
        return roleMenuPermissionService.query(roleMenuPermission);
    }

    @RequestMapping("add")
    @RequiresPermissions(PermissionSign.ROLE_ADD_MENU_PERMISSION)
    @ResponseBody
    public Result add(@Login UserInfo userInfo, RoleMenuPermissionAddForm form) {
        return roleMenuPermissionService.add(userInfo, form);
    }
}
