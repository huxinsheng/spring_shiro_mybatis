package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import com.hxs.ssm.model.Menu;
import lombok.Data;

import java.util.List;


@Data
public class Role extends BaseModel {
    /**
     * 主键id
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String desc;
    /**
     * 角色标识
     */
    private String sign;
    /**
     * 角色拥有菜单列表
     */
    private List<Menu> menus;
    /**
     * 是否选中
     */
    private boolean checked;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
