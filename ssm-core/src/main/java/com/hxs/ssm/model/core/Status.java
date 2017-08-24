package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;


@Data
public class Status extends BaseModel {
    /**
     * 状态id
     */
    private Integer id;
    /**
     * 状态名称
     */
    private String name;
    /**
     * 状态值
     */
    private String value;
    /**
     * 状态类型
     */
    private Integer type;
    /**
     * 数据状态
     */
    private boolean status;
    /**
     * 状态描述
     */
    private String desc;

    /**
     * 状态类型实体
     */
    private StatusType statusType;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
