package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： PermissionAddForm
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-04-01 15:14
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class PermissionUpdateForm {
    /**
     * 权限Id
     */
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String sign;
    /**
     * 权限描述
     */
    private String desc;
}
