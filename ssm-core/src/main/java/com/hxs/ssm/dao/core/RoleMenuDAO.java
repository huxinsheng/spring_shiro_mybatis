package com.hxs.ssm.dao.core;

import com.hxs.ssm.form.core.RoleMenuQueryForm;
import com.hxs.ssm.model.Menu;
import com.hxs.ssm.model.core.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleMenuDAO
 * @类描述： 角色菜单数据持久接口
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 17:04
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Repository
public interface RoleMenuDAO {
    int deleteByRoleIdAndMenuId(@Param("roleId") String roleId, @Param("menuId") String menuId);

    List<Menu> queryByEntity(RoleMenuQueryForm form);

    void insertAll(List<RoleMenu> roleMenus);
}
