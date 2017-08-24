package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： UserRoleQueryForm
 * @类描述： 用户角色查询数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 15:34
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class UserRoleQueryForm {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
