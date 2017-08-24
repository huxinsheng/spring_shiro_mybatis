package com.hxs.ssm.services.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.dao.core.PermissionDAO;
import com.hxs.ssm.form.core.PermissionAddForm;
import com.hxs.ssm.form.core.PermissionQueryForm;
import com.hxs.ssm.form.core.PermissionUpdateForm;
import com.hxs.ssm.model.core.BillSequnceKey;
import com.hxs.ssm.model.Permission;
import com.hxs.ssm.result.*;
import com.hxs.ssm.utils.CommonUtils;
import com.hxs.ssm.utils.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： PermissionServiceImpl
 * @类描述： 权限管理服务接口实现类
 * @创建人： huxinsheng
 * @创建时间： 2017-04-06 16:32
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Service
public class PermissionService {

    @Resource
    PermissionDAO permissionDAO;

    @Resource
    RoleMenuPermissionService roleMenuPermissionService;

    @Resource
    MenuPermissionService menuPermissionService;

    @Resource
    BillService billService;

    public TableResult<Permission> query(PermissionQueryForm form) {
        if (null != form) {
            long count = permissionDAO.count(form);
            List<Permission> list = permissionDAO.query(form);
            return new TablePaginationResult<Permission>(count, list, form);
        } else {
            return null;
        }
    }

    @Transactional
    public Result add(UserInfo userInfo, PermissionAddForm form) {
        Permission permission = permissionDAO.findByIdAndName(null, form.getName());
        if (null != permission) {
            return Result.error(Result.EXISTS, "权限名称已存在");
        }
        permission = new Permission();
        BeanUtils.copyProperties(form, permission);
        CommonUtils.fillingValue(userInfo, permission, Const.ADD);
        String id = billService.newBillNo(new BillSequnceKey("permissionId"));
        permission.setId(id);
        permissionDAO.insert(permission);
        return Result.ok();
    }

    @Transactional
    public Result update(UserInfo userInfo, PermissionUpdateForm form) {
        Permission permission = permissionDAO.findByIdAndName(form.getId(), form.getName());
        if (null != permission) {
            return Result.error(Result.EXISTS, "权限名称已存在");
        }
        permission = new Permission();
        BeanUtils.copyProperties(form, permission);
        CommonUtils.fillingValue(userInfo, permission, !Const.ADD);
        permissionDAO.update(permission);
        return Result.ok();
    }

    @Transactional
    public Result delete(UserInfo userInfo, String id) {
        Permission permission = permissionDAO.findByIdAndName(id, null);
        //角色菜单权限信息
        roleMenuPermissionService.deleteByRoleIdAndMenuIdAndPermissionId(null, null, id);
        //菜单权限信息
        menuPermissionService.deleteByMenuIdAndPermissionId(id, null);
        permissionDAO.deleteById(id);
        return Result.ok();
    }

}
