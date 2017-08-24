package com.hxs.ssm.form.core;

/**
 * @项目名称： hxs-mp
 * @类名称： LoginForm
 * @类描述： 登录form
 * @创建人： huxinsheng
 * @创建时间： 2017-04-05 11:06
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
public class LoginForm {
    private String loginId;

    private String password;

    public LoginForm() {

    }

    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
