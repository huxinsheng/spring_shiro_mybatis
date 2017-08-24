package com.hxs.ssm.action.core;


import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.StatusTypeAddForm;
import com.hxs.ssm.form.core.StatusTypeQueryForm;
import com.hxs.ssm.form.core.StatusTypeUpdateForm;
import com.hxs.ssm.model.core.StatusType;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.result.TableResult;
import com.hxs.ssm.services.core.StatusTypeService;
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
 * @类名称： StatusTypeAction
 * @类描述： 状态类型管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 11:10
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/type")
public class StatusTypeAction extends ViewAction {
    @Resource
    StatusTypeService statusTypeService;

    @RequestMapping
    @RequiresPermissions(PermissionSign.STATUS_TYPE_QUERY)
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = createModelAndView(userInfo);
        return mv;
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_TYPE_QUERY)
    @RequestMapping("query")
    public TableResult<StatusType> query(@Login UserInfo userInfo, StatusTypeQueryForm form) {
        return statusTypeService.query(form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_TYPE_QUERY)
    @RequestMapping("queryAll")
    public List<StatusType> queryAll(StatusTypeQueryForm form) {
        return statusTypeService.queryAll(form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_TYPE_CREATE)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@Login UserInfo userInfo, StatusTypeAddForm form) {
        return statusTypeService.add(userInfo, form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_TYPE_UPDATE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@Login UserInfo userInfo, StatusTypeUpdateForm form) {
        return statusTypeService.update(userInfo, form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_TYPE_DELETE)
    @RequestMapping(value = "delete/{id}")
    public Result delete(@Login UserInfo userInfo, @PathVariable("id") String id) {
        return statusTypeService.delete(userInfo, id);
    }
}
