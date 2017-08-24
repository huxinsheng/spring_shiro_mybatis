package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： UserAddForm
 * @类描述： 用户信息添加数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 15:04
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class UserAddForm {
    /**
     * 登录id
     */
    private String loginId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 用户邮箱
     */
    private String email;
}
