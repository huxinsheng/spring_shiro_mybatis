package com.hxs.ssm.services.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.dao.core.RoleDAO;
import com.hxs.ssm.form.core.RoleAddForm;
import com.hxs.ssm.form.core.RoleQueryForm;
import com.hxs.ssm.form.core.RoleUpdateForm;
import com.hxs.ssm.model.core.BillSequnceKey;
import com.hxs.ssm.model.Menu;
import com.hxs.ssm.model.core.Role;
import com.hxs.ssm.model.core.TreeJson;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.utils.CommonUtils;
import com.hxs.ssm.utils.Const;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： RoleServiceImpl
 * @类描述： 角色管理服务接口实现类
 * @创建人： huxinsheng
 * @创建时间： 2017-04-07 16:20
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Service
public class RoleService {
    @Resource
    RoleDAO roleDAO;

    @Resource
    BillService billService;

    @Resource
    RoleMenuService roleMenuService;

    @Resource
    RoleMenuPermissionService roleMenuPermissionService;

    @Resource
    UserRoleService userRoleService;

    public List<Object> query(RoleQueryForm form) {
        form.setPagination(false);
        String parent = "0";
        form.setParent(parent);
        List<Role> roles = roleDAO.findByEntity(form);
        List<Object> treeJsons = new ArrayList<Object>();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role r : roles) {

                List<Menu> menuList = r.getMenus();
                TreeJson<Role> roleTreeJson = new TreeJson<Role>();
                roleTreeJson.setId(r.getId());
                roleTreeJson.setLevel(0);
                roleTreeJson.setParent(parent);
                roleTreeJson.setEntity(r);
                treeJsons.add(roleTreeJson);
                if (CollectionUtils.isEmpty(menuList)) {
                    roleTreeJson.setLeaf(true);
                    roleTreeJson.setExpanded(true);
                } else {
                    buildMenuJson(treeJsons, menuList, roleTreeJson);
                }

            }
        }
        return treeJsons;
    }

    private void buildMenuJson(List<Object> roleJsonList, List<Menu> menuList, TreeJson roleJson) {
        for (Menu menu : menuList) {
            TreeJson rj = new TreeJson();
            rj.setId(menu.getId() + "_" + roleJson.getId());
            rj.setIcon(menu.getIcon());
            rj.setParent(roleJson.getId());
            rj.setParent(roleJson.getId());
            rj.setLevel(menu.getLevel() + 1);
            rj.setEntity(menu);
            List<Menu> childMenus = menu.getSubMenus();
            if (CollectionUtils.isEmpty(childMenus)) {
                rj.setLeaf(true);
                rj.setExpanded(true);
            } else {
                rj.setLeaf(false);
                rj.setExpanded(false);
                //menu.setChildMenu(null);
            }
            roleJsonList.add(rj);
            if (CollectionUtils.isNotEmpty(childMenus)) {
                buildMenuJson(roleJsonList, childMenus, rj);
            }
        }
    }

    public Result add(UserInfo userInfo, RoleAddForm form) {
        Role role = roleDAO.findExistsByName(null, form.getName(), form.getSign());
        if (null != role) {
            return Result.error(Result.EXISTS, "角色信息已存在.");
        }
        role = new Role();
        BeanUtils.copyProperties(form, role);
        String id = billService.newBillNo(new BillSequnceKey("roleId"));
        role.setId(id);
        CommonUtils.fillingValue(userInfo, role, Const.ADD);
        roleDAO.insert(role);
        return Result.ok(role);
    }

    public Result update(UserInfo userInfo, RoleUpdateForm form) {
        Role role = roleDAO.findExistsByName(form.getId(), form.getName(), form.getSign());
        if (null != role) {
            return Result.error(Result.EXISTS, "角色信息已存在.");
        }
        role = new Role();
        BeanUtils.copyProperties(form, role);
        CommonUtils.fillingValue(userInfo, role, !Const.ADD);
        roleDAO.update(role);
        return Result.ok(role);
    }

    public Result delete(UserInfo userInfo, String id) {
        //角色菜单资源
        roleMenuPermissionService.deleteByRoleIdAndMenuIdAndPermissionId(id, null, null);
        //角色菜单信息
        roleMenuService.deleteByRoleIdAndMenuId(id, null);
        //用户角色信息
        userRoleService.deleteByUserIdAndRoleId(null, id);
        //角色信息
        roleDAO.deleteById(id);
        return Result.ok();
    }
}
