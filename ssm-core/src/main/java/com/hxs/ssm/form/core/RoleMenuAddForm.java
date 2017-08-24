package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuAddForm
 * @类描述： 角色菜单添加数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 10:54
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class RoleMenuAddForm {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
}
