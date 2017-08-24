package com.hxs.ssm.dao.core;

import com.hxs.ssm.form.core.MenuPermissionDeleteForm;
import com.hxs.ssm.form.core.MenuPermissionQueryForm;
import com.hxs.ssm.model.core.MenuPermission;
import com.hxs.ssm.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuPermissionDAO
 * @类描述： 菜单权限管理数据持久接口
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 08:55
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Repository
public interface MenuPermissionDAO {
    List<Permission> findPermissionByEntity(MenuPermissionQueryForm form);

    void insertAll(List<MenuPermission> menuPermissions);

    void deleteByPrimaryKey(@Param("id") Integer id);

    void deleteBySelective(MenuPermissionDeleteForm form);

    void deleteByMenuId(@Param("menuId") String menuId, @Param("permissionId") String permissionId);

    void deleteByParentMenuId(@Param("menuId") String menuId);
}
