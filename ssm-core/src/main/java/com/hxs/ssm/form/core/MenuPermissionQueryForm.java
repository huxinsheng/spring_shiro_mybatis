package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuPermissionQueryForm
 * @类描述： 菜单权限查询数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 09:04
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class MenuPermissionQueryForm {
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 权限id
     */
    private String permissionId;
}
