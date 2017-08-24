package com.hxs.ssm.action.core;


import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.UserRoleAddForm;
import com.hxs.ssm.form.core.UserRoleQueryForm;
import com.hxs.ssm.model.core.Role;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.services.core.UserRoleService;
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
 * @类名称： UserRoleAction
 * @类描述： 用户角色管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 15:30
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/userRole")
public class UserRoleAction {
    @Resource
    UserRoleService userRoleService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public List<Role> query(@Login UserInfo userInfo, UserRoleQueryForm form) {
        return userRoleService.findByEntity(form);
    }

    @RequestMapping(value = "/add")
    @RequiresPermissions(PermissionSign.USER_ADD_ROLE)
    @ResponseBody
    public Result add(@Login UserInfo userInfo, UserRoleAddForm form) {
        return userRoleService.add(userInfo, form);
    }
}
