package com.hxs.ssm.form.core;

import com.hxs.ssm.form.PaginationForm;
import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleQueryForm
 * @类描述： 角色查询数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 15:55
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class RoleQueryForm extends PaginationForm {
    /**
     * 主键id
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 父菜单
     */
    private String parent;
    /**
     * 角色标识
     */
    private String sign;
}
