package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;


@Data
public class MenuPermission extends BaseModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 权限id
     */
    private String permissionId;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
