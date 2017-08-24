package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleAddForm
 * @类描述： 角色添加数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 15:55
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class RoleAddForm {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String desc;
    /**
     * 角色标识
     */
    private String sign;
}
