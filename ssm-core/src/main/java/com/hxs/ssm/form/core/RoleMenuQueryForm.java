package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuQueryForm
 * @类描述： 角色菜单查询数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 10:15
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class RoleMenuQueryForm {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 父菜单
     */
    private String parent;
}
