package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuPermissionAddForm
 * @类描述： 角色菜单权限添加数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 18:49
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class RoleMenuPermissionAddForm {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 菜单权限id
     */
    private String permissionId;
    /**
     * 菜单权限标识
     */
    private String permissionSign;

}
