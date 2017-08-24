package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;


@Data
public class RoleMenu extends BaseModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
