package com.hxs.ssm.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称： portal
 * @类名称： AdditionalParameters
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-1-13 16:23
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 * @Version v1.0
 */
public class AdditionalParameters {

    public AdditionalParameters() {
    }

    public AdditionalParameters(Map<String, TreeJsonModel> maps) {
        this.setChildren(maps);
    }

    public AdditionalParameters(boolean itemSelected) {
        this.setItemSelected(itemSelected);
    }

    /**
     * 子节点列表
     */
    private Map<String, TreeJsonModel> children = new HashMap<String, TreeJsonModel>();

    /**
     * 是否有选中属性
     */
    @JsonProperty("item-selected")
    private boolean itemSelected;

    public boolean isItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        this.itemSelected = itemSelected;
    }

    public Map<String, TreeJsonModel> getChildren() {
        return children;
    }

    public void setChildren(Map<String, TreeJsonModel> children) {
        this.children = children;
    }
}
