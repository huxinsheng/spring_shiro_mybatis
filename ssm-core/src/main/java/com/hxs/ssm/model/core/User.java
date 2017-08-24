package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.UserInfo;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;

import java.util.List;


@Data
public class User extends BaseModel {
    public User() {

    }

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    /**
     * 主键id
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
     * 密码
     */
    private String password;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 是否禁用
     */
    private boolean disabled;
    /**
     * 用户角色列表
     */
    private List<Role> roles;

    /**
     * 登录信息
     */
    private UserInfo loginInfo;

    /**
     * 是否首次登录
     */
    private boolean isFirstLogin;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
