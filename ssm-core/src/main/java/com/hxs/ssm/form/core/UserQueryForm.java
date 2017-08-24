package com.hxs.ssm.form.core;

import com.hxs.ssm.form.TablePaginationForm;
import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： UserQueryForm
 * @类描述： 用户信息查询数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-08 14:36
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class UserQueryForm extends TablePaginationForm {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 登录id
     */
    private String loginId;
    /**
     * 登录密码
     */
    private String password;

    public UserQueryForm() {

    }

    public UserQueryForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
