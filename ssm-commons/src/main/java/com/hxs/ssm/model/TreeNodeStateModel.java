package com.hxs.ssm.model;

/**
 * @项目名称： portal
 * @类名称： TreeNodeStateModel
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2017-1-13 18:39
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 * @Version v1.0
 */
public class TreeNodeStateModel {
    /**
     * 指示一个节点是否处于checked状态，用一个checkbox图标表示。
     */
    private boolean checked;
    /**
     * 指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     */
    private boolean disabled;
    /**
     * 指示一个节点是否处于展开状态。
     */
    private boolean expanded;
    /**
     * 指示一个节点是否处于展开状态。
     */
    private boolean selected;

    /**
     * 获取 checked
     * checked 类型为 boolean
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * 设置 checked 到 checked
     * checked 类型为 boolean
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * 获取 disabled
     * disabled 类型为 boolean
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * 设置 disabled 到 disabled
     * disabled 类型为 boolean
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 获取 expanded
     * expanded 类型为 boolean
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * 设置 expanded 到 expanded
     * expanded 类型为 boolean
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * 获取 selected
     * selected 类型为 boolean
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * 设置 selected 到 selected
     * selected 类型为 boolean
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
