package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class TreeJson<T> {
    private String id;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 父ID
     */
    private String parent;
    /**
     * 是否叶子节点
     */
    private boolean leaf;
    /**
     * 是否展开
     */
    private boolean expanded;
    /**
     * 节点图标
     */
    private String icon;
    /**
     * 数据对象
     */
    private T entity;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
