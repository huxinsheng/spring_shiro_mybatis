package com.hxs.ssm.model.core;

import com.alibaba.fastjson.JSON;
import com.hxs.ssm.model.BaseModel;
import lombok.Data;


@Data
public class StatusType extends BaseModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 状态类型名称
     */
    private String name;
    /**
     * 状态类型值
     */
    private String value;
    /**
     * 状态类型描述
     */
    private String desc;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
