package com.hxs.ssm.model;

import lombok.Data;

import java.util.List;

/**
 * @项目名称： portal
 * @类名称： TreeJsonModel
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-1-13 16:23
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 * @Version v1.0
 */
@Data
public class TreeJsonModel {
    private String id;
    private String name;
    private String code;
    private String icon;
    private String sign;
    private String type;
    private String parent;
    private List<TreeJsonModel> nodes;
    private TreeNodeStateModel state;
    private AdditionalParameters additionalParameters;
}
