package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.PermissionAddForm;
import com.hxs.ssm.form.core.PermissionQueryForm;
import com.hxs.ssm.form.core.PermissionUpdateForm;
import com.hxs.ssm.model.Permission;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.result.TableResult;
import com.hxs.ssm.services.core.PermissionService;
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

/**
 * @项目名称： hxs-mp
 * @类名称： PermissionAction
 * @类描述： 权限管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-06 16:17
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/permission")
public class PermissionAction extends ViewAction {
    @Resource
    PermissionService permissionService;

    @RequestMapping
    @RequiresPermissions(PermissionSign.PERMISSION_QUERY)
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = createModelAndView(userInfo);
        return mv;
    }

    @ResponseBody
    @RequestMapping("query")
    @RequiresPermissions(PermissionSign.PERMISSION_QUERY)
    public TableResult<Permission> query(@Login UserInfo userInfo, PermissionQueryForm form) {
        return permissionService.query(form);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions(PermissionSign.PERMISSION_CREATE)
    public Result add(@Login UserInfo userInfo, PermissionAddForm form) {
        return permissionService.add(userInfo, form);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions(PermissionSign.PERMISSION_UPDATE)
    public Result update(@Login UserInfo userInfo, PermissionUpdateForm form) {
        return permissionService.update(userInfo, form);
    }

    @ResponseBody
    @RequestMapping(value = "delete/{id}")
    @RequiresPermissions(PermissionSign.PERMISSION_DELETE)
    public Result delete(@Login UserInfo userInfo, @PathVariable("id") String id) {
        return permissionService.delete(userInfo, id);
    }
}
