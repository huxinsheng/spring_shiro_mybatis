package com.hxs.ssm.services.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.dao.core.RoleMenuPermissionDAO;
import com.hxs.ssm.form.core.RoleMenuPermissionAddForm;
import com.hxs.ssm.form.core.RoleMenuPermissionQueryForm;
import com.hxs.ssm.model.core.RoleMenuPermission;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.utils.CommonUtils;
import com.hxs.ssm.utils.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuPermissionServiceImpl
 * @类描述： 角色菜单权限管理服务接口实现类
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 18:55
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Service
public class RoleMenuPermissionService {
    @Resource
    RoleMenuPermissionDAO roleMenuPermissionDAO;

    public List<RoleMenuPermission> query(RoleMenuPermissionQueryForm form) {
        return roleMenuPermissionDAO.selectByEntity(form);
    }

    public List<String> findByRoleIds(List<String> roleIds) {
        return roleMenuPermissionDAO.selectByRoleIds(roleIds);
    }

    public Result add(UserInfo userInfo, RoleMenuPermissionAddForm form) {
        roleMenuPermissionDAO.deleteByRoleIdAndMenuId(form.getRoleId(), form.getMenuId(), null);
        String temp = form.getPermissionId();
        form.setPermissionId("");
        if (StringUtils.isNotEmpty(temp)) {
            List<RoleMenuPermission> roleMenuPermissionList = new ArrayList<RoleMenuPermission>();
            String[] permissionIdArray = temp.split(",");
            String[] permissionSignArray = form.getPermissionSign().split(",");
            for (int i = 0; i < permissionIdArray.length; i++) {
                RoleMenuPermission roleMenuPermission = new RoleMenuPermission();
                BeanUtils.copyProperties(form, roleMenuPermission);
                roleMenuPermission.setPermissionId(permissionIdArray[i]);
                roleMenuPermission.setPermissionSign(permissionSignArray[i]);
                CommonUtils.fillingValue(userInfo, roleMenuPermission, Const.ADD);
                roleMenuPermissionList.add(roleMenuPermission);
            }
            roleMenuPermissionDAO.insertAll(roleMenuPermissionList);
        }
        return Result.ok();
    }

    public void deleteByRoleIdAndMenuIdAndPermissionId(String roleId, String menuId, String permissionId) {
        roleMenuPermissionDAO.deleteByRoleIdAndMenuId(roleId, menuId, permissionId);
    }
}
