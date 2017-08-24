package com.hxs.ssm.form.core;

import com.hxs.ssm.form.TablePaginationForm;
import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： PermissionQueryForm
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-04-01 15:14
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class PermissionQueryForm extends TablePaginationForm {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String sign;
}
