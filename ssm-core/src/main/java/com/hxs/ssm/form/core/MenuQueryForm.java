package com.hxs.ssm.form.core;

import com.hxs.ssm.form.PaginationForm;
import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuQueryForm
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-04-01 15:14
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class MenuQueryForm extends PaginationForm {
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
}
