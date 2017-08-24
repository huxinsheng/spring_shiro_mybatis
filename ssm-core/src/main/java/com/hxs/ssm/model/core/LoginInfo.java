package com.hxs.ssm.model.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @项目名称： hxs-ssm
 * @类名称： LoginInfo
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-06-21 11:10
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class LoginInfo {
    private Integer id;

    private String loginId;

    private String loginName;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
}
