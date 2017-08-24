package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;


@Data
public class UserRole extends BaseModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
