package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： StatusAddForm
 * @类描述： 状态信息添加数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 13:43
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class StatusAddForm {
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

}
