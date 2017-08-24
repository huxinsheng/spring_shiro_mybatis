package com.hxs.ssm;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo<T> {
    /**
     * 用户id
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
     * 登录ip
     */
    private String ip;

    /**
     * 合作城市id
     */
    private String cid;

    /**
     * 合作城市名称
     */
    private String cname;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    private List<T> menus;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
