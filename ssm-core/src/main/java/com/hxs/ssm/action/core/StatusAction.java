package com.hxs.ssm.action.core;


import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.StatusAddForm;
import com.hxs.ssm.form.core.StatusQueryForm;
import com.hxs.ssm.form.core.StatusUpdateForm;
import com.hxs.ssm.model.core.Status;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.result.TableResult;
import com.hxs.ssm.services.core.StatusService;
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
 * @类名称： StatusAction
 * @类描述： 状态信息管理控制器
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 11:10
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Controller
@RequestMapping("/core/status")
public class StatusAction extends ViewAction {
    @Resource
    StatusService statusService;

    @RequestMapping
    @RequiresPermissions(PermissionSign.STATUS_QUERY)
    public ModelAndView index(@Login UserInfo userInfo) {
        ModelAndView mv = createModelAndView(userInfo);
        return mv;
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_QUERY)
    @RequestMapping("query")
    public TableResult<Status> query(@Login UserInfo userInfo, StatusQueryForm form) {
        return statusService.query(form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_CREATE)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@Login UserInfo userInfo, StatusAddForm form) {
        return statusService.add(userInfo, form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_UPDATE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@Login UserInfo userInfo, StatusUpdateForm form) {
        return statusService.update(userInfo, form);
    }

    @ResponseBody
    @RequiresPermissions(PermissionSign.STATUS_UPDATE_STATUS)
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    public Result updateStatus(@Login UserInfo userInfo, StatusUpdateForm form) {
        return statusService.updateStatus(userInfo, form);
    }

    @ResponseBody
    @RequestMapping(value = "queryByType/{type}")
    public List<Status> queryByType(@PathVariable("type") String type) {
        return statusService.queryByType(type);
    }
}
