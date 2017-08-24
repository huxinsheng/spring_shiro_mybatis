package com.hxs.ssm.form.core;

import com.hxs.ssm.form.TablePaginationForm;
import lombok.Data;

/**
 * @项目名称： hxs-mp
 * @类名称： StatusTypeQueryForm
 * @类描述： 状态类型查询数据实体
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 10:54
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Data
public class StatusTypeQueryForm extends TablePaginationForm {
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
}
