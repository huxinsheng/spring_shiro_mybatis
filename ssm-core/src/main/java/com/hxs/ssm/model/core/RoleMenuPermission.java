package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;


@Data
public class RoleMenuPermission extends BaseModel {
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
    /**
     * 菜单权限id
     */
    private String permissionId;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限标识
     */
    private String permissionSign;
    /**
     * 是否选中
     */
    private boolean checked;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
