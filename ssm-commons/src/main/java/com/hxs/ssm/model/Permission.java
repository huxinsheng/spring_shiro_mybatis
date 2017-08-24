package com.hxs.ssm.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;


@Data
public class Permission extends BaseModel {
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
    /**
     * 是否选中
     */
    private boolean checked;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
