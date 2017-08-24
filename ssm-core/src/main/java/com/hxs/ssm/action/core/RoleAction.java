package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.RoleAddForm;
import com.hxs.ssm.form.core.RoleQueryForm;
import com.hxs.ssm.form.core.RoleUpdateForm;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.services.core.RoleService;
import com.hxs.ssm.web.action.ViewAction;
import com.hxs.ssm.web.annotation.Login;
import com.hxs.ssm.web.security.PermissionSign;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleAction
 * @类描述： 角色管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 17:16
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/role")
public class RoleAction extends ViewAction {
    @Resource
    RoleService roleService;

    @RequestMapping
    @RequiresPermissions(PermissionSign.ROLE_QUERY)
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = createModelAndView(userInfo);
        return mv;
    }

    @RequestMapping(value = "query")
    @RequiresPermissions(PermissionSign.ROLE_QUERY)
    @ResponseBody
    public List<Object> query(@Login UserInfo userInfo, RoleQueryForm form) {
        return roleService.query(form);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions(PermissionSign.ROLE_CREATE)
    @ResponseBody
    public Result add(@Login UserInfo userInfo, RoleAddForm form) {
        return roleService.add(userInfo, form);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions(PermissionSign.ROLE_UPDATE)
    @ResponseBody
    public Result update(@Login UserInfo userInfo, RoleUpdateForm form) {
        return roleService.update(userInfo, form);
    }

    @RequestMapping(value = "delete/{id}")
    @RequiresPermissions(PermissionSign.ROLE_DELETE)
    @ResponseBody
    public Result delete(@Login UserInfo userInfo, @PathVariable("id") String id) {
        return roleService.delete(userInfo, id);
    }
}
