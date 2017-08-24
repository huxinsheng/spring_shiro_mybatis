package com.hxs.ssm.services.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.dao.core.MenuDAO;
import com.hxs.ssm.form.core.MenuAddForm;
import com.hxs.ssm.form.core.MenuQueryForm;
import com.hxs.ssm.form.core.MenuUpdateForm;
import com.hxs.ssm.model.core.BillSequnceKey;
import com.hxs.ssm.model.Menu;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.utils.CommonUtils;
import com.hxs.ssm.utils.Const;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuService
 * @类描述： 菜单管理服务
 * @创建人： huxinsheng
 * @创建时间： 2017-03-31 18:07
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Service("menuService")
public class MenuService {

    @Resource(name = "menuDAO")
    MenuDAO menuDAO;

    @Resource
    RoleMenuPermissionService roleMenuPermissionService;

    @Resource
    RoleMenuService roleMenuService;

    @Resource
    MenuPermissionService menuPermissionService;

    @Resource
    BillService billService;

    public List<Menu> query(MenuQueryForm form) {
        form.setPagination(false);
        List<Menu> parentMenus = menuDAO.findByEntity(form);
        findSubMenus(parentMenus, form);
        List<Menu> treeMenus = new ArrayList<Menu>();
        buildMenus(parentMenus, treeMenus);
        return treeMenus;
    }

    private void buildMenus(List<?> menus, List<Menu> treeMenus) {
        for (Object obj : menus) {
            Menu menu = (Menu) obj;
            List<?> childMenus = menu.getSubMenus();
            if (CollectionUtils.isEmpty(childMenus)) {
                menu.setLeaf(true);
                menu.setExpanded(true);
            }
            treeMenus.add(menu);
            if (CollectionUtils.isNotEmpty(childMenus)) {
                buildMenus(childMenus, treeMenus);
            }
        }
    }

    private void findSubMenus(List<Menu> menus, MenuQueryForm form) {
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu menu : menus) {
                form.setParent(menu.getId());
                List<Menu> subMenus = menuDAO.findByEntity(form);
                if (CollectionUtils.isNotEmpty(subMenus)) {
                    findSubMenus(subMenus, form);
                }
                menu.setSubMenus(subMenus);
            }
        }
    }

    public List<Menu> findByEntity(MenuQueryForm form) {
        return menuDAO.findByEntity(form);
    }

    public Menu findByUrl(String url) {
        return menuDAO.findByUrl(url);
    }

    public List<Menu> loadUserMenu(UserInfo userInfo) {
        return menuDAO.findByUserIdAndParent(userInfo.getId(), "0");
    }

    @Transactional
    public Result add(UserInfo userInfo, MenuAddForm form) {
        Menu menu = menuDAO.findByMenuName(null, form.getName());
        if (null != menu) {
            return Result.error(Result.EXISTS, "菜单名称已存在");
        }
        menu = new Menu();
        BeanUtils.copyProperties(form, menu);
        CommonUtils.fillingValue(userInfo, menu, Const.ADD);
        String id = billService.newBillNo(new BillSequnceKey("menuId"));
        menu.setId(id);
        menuDAO.insert(menu);
        return Result.ok();
    }

    @Transactional
    public Result update(UserInfo userInfo, MenuUpdateForm form) {
        Menu menu = menuDAO.findByMenuName(form.getId(), form.getName());
        if (null != menu) {
            return Result.error(Result.EXISTS, "菜单名称已存在");
        }
        menu = new Menu();
        BeanUtils.copyProperties(form, menu);
        CommonUtils.fillingValue(userInfo, menu, !Const.ADD);
        menuDAO.update(menu);
        return Result.ok();
    }

    @Transactional
    public Result delete(UserInfo userInfo, String id) {
        //删除相应关联数据
        //角色菜单资源
        deleteChildMenus(id);
        //角色菜单权限
        roleMenuPermissionService.deleteByRoleIdAndMenuIdAndPermissionId(null, id, null);
        //角色菜单
        roleMenuService.deleteByRoleIdAndMenuId(null, id);
        //菜单资源
        menuPermissionService.deleteByMenuIdAndPermissionId(id, null);
        //删除子菜单
        menuDAO.deleteByParent(id);
        //删除本菜单
        menuDAO.deleteById(id);
        return Result.ok();
    }

    @Transactional
    private void deleteChildMenus(String id) {
        MenuQueryForm menuQueryForm = new MenuQueryForm();
        menuQueryForm.setParent(id);
        List<Menu> menuList = menuDAO.findByEntity(menuQueryForm);
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                String menuId = menu.getId();
                roleMenuPermissionService.deleteByRoleIdAndMenuIdAndPermissionId(null, menuId, null);
                //角色菜单
                roleMenuService.deleteByRoleIdAndMenuId(null, menuId);
                //菜单资源
                menuPermissionService.deleteByMenuIdAndPermissionId(menuId, null);
                deleteChildMenus(menuId);
            }
        }
    }
}
