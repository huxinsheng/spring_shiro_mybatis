package com.hxs.ssm.services.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.dao.core.StatusDAO;
import com.hxs.ssm.form.core.StatusAddForm;
import com.hxs.ssm.form.core.StatusQueryForm;
import com.hxs.ssm.form.core.StatusUpdateForm;
import com.hxs.ssm.model.core.Status;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.result.TablePaginationResult;
import com.hxs.ssm.result.TableResult;
import com.hxs.ssm.utils.CommonUtils;
import com.hxs.ssm.utils.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： StatusServiceImpl
 * @类描述： 状态信息管理接口实现类
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 13:56
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Service
public class StatusService {

    @Resource
    private StatusDAO statusDAO;

    public TableResult<Status> query(StatusQueryForm form) {
        if (null != form) {
            long count = statusDAO.count(form);
            List<Status> list = statusDAO.query(form);
            return new TablePaginationResult<Status>(count, list, form);
        } else {
            return null;
        }
    }

    public List<Status> queryByType(String type) {
        return statusDAO.queryByType(type);
    }

    @Transactional
    public Result add(UserInfo userInfo, StatusAddForm form) {
        Status status = statusDAO.findExistsByCondition(null, form.getType(), form.getValue(), form.getName());
        if (null != status) {
            return Result.error(Result.EXISTS, "状态值已存在");
        }
        status = new Status();
        BeanUtils.copyProperties(form, status);
        CommonUtils.fillingValue(userInfo, status, Const.ADD);
        statusDAO.insert(status);
        return Result.ok();
    }

    @Transactional
    public Result update(UserInfo userInfo, StatusUpdateForm form) {
        Status status = statusDAO.findExistsByCondition(form.getId(), form.getType(), form.getValue(), form.getName());
        if (null != status) {
            return Result.error(Result.EXISTS, "状态值已存在");
        }
        status = new Status();
        BeanUtils.copyProperties(form, status);
        CommonUtils.fillingValue(userInfo, status, !Const.ADD);
        statusDAO.update(status);
        return Result.ok();
    }

    @Transactional
    public Result updateStatus(UserInfo userInfo, StatusUpdateForm form) {
        Status status = new Status();
        BeanUtils.copyProperties(form, status);
        statusDAO.updateStatus(status);
        return Result.ok();
    }
}
