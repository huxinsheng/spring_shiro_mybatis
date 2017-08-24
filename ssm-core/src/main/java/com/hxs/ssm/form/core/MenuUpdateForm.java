package com.hxs.ssm.form.core;

import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuUpdateForm
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-04-01 15:14
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class MenuUpdateForm {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单ID
     */
    private String parent;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 菜单级别
     */
    private Integer level;
    /**
     * 菜单顺序
     */
    private Integer seq;
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单视图
     */
    private String view;
}
