package com.hxs.ssm.web.security;

/**
 * 权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 * <p/>
 * <pre>
 * &#064;RequiresPermissions(value = PermissionConfig.USER_CREATE)
 * public String create() {
 *     return &quot;拥有user:create权限,能访问&quot;;
 * }
 * </pre>
 *
 * @author HuXinsheng
 * @since 2016-03-15 10:43
 **/
public class PermissionSign {

    /**
     * 分析查询
     */
    public static final String ANALYSIS_QUERY = "/analysis:query";

    /**
     * 分析导出
     */
    public static final String ANALYSIS_EXPORT = "/analysis:export";

    /**
     * 区域查询
     */
    public static final String DISTRICT_QUERY = "/personalCenter/district:query";

    /**
     * 员工查询
     */
    public static final String EMPLOYEE_QUERY = "/employee:query";

    /**
     * 设置查询
     */
    public static final String SETTING_QUERY = "/personalCenter/setting:query";

    /**
     * 设置接口查询
     */
    public static final String SETTING_WEB_QUERY = "/personalCenter/setting:querySetting";

    /**
     * 设置城市接口查询
     */
    public static final String SETTING_CITY_WEB_QUERY = "/personalCenter/setting:queryCityList";

    /**
     * 设置城市接口查询
     */
    public static final String SETTING_WEB_SAVE = "/personalCenter/setting:saveSetting";

    public static final String DISTRICT_CITY_WEB_QUERY = "/personalCenter/district:queryCityList";

    public static final String DISTRICT_AREA_WEB_QUERY = "/personalCenter/district:queryAreaList";

    public static final String DISTRICT_SUBDISTRICT_WEB_QUERY = "/personalCenter/district:querySubdistrictList";

    public static final String DISTRICT_VILLAGE_WEB_QUERY = "/personalCenter/district:queryVillageList";

    public static final String DISTRICT_AREA_WEB_SAVE = "/personalCenter/district:saveArea";

    public static final String DISTRICT_SUBDISTRICT_WEB_SAVE = "/personalCenter/district:saveSubdistrict";

    public static final String DISTRICT_VILLAGE_WEB_SAVE = "/personalCenter/district:saveVillage";

    public static final String EMPLOYEE_CITY_WEB_QUERY = "/employee:queryCityList";

    public static final String EMPLOYEE_AREA_WEB_QUERY = "/employee:queryAreaList";

    public static final String EMPLOYEE_SUBDISTRICT_WEB_QUERY = "/employee:querySubdistrictList";

    public static final String EMPLOYEE_VILLAGE_WEB_QUERY = "/employee:queryVillageList";

    public static final String EMPLOYEE_WEB_SAVE = "/employee:update";

    public static final String EMPLOYEE_SUM_QUERY = "/employee:sum";

    public static final String DIMENSION_SUM_QUERY = "/analysis:sum";
    /**
     * 菜单查询
     */
    public static final String MENU_QUERY = "/core/menu:query";
    /**
     * 菜单新增
     */
    public static final String MENU_CREATE = "/core/menu:create";
    /**
     * 菜单更新
     */
    public static final String MENU_UPDATE = "/core/menu:update";
    /**
     * 菜单删除
     */
    public static final String MENU_DELETE = "/core/menu:delete";
    /**
     * 添加菜单权限
     */
    public static final String MENU_ADD_PERMISSION = "/core/menu:addPermission";
    /**
     * 添加子菜单
     */
    public static final String MENU_ADD_SUB_MENU = "/core/menu:addSubMenu";
    /**
     * 权限查询
     */
    public static final String PERMISSION_QUERY = "/core/permission:query";
    /**
     * 权限新增
     */
    public static final String PERMISSION_CREATE = "/core/permission:create";
    /**
     * 权限更新
     */
    public static final String PERMISSION_UPDATE = "/core/permission:update";
    /**
     * 权限删除
     */
    public static final String PERMISSION_DELETE = "/core/permission:delete";
    /**
     * 角色查询
     */
    public static final String ROLE_QUERY = "/core/user:query";
    /**
     * 角色新增
     */
    public static final String ROLE_CREATE = "/core/user:create";
    /**
     * 角色更新
     */
    public static final String ROLE_UPDATE = "/core/user:update";
    /**
     * 角色删除
     */
    public static final String ROLE_DELETE = "/core/user:delete";
    /**
     * 角色添加菜单
     */
    public static final String ROLE_ADD_MENU = "/core/role:addMenu";
    /**
     * 角色添加菜单权限
     */
    public static final String ROLE_ADD_MENU_PERMISSION = "/core/role:addMenuPermission";

    /**
     * 状态查询
     */
    public static final String STATUS_QUERY = "/core/status:query";
    /**
     * 状态新增
     */
    public static final String STATUS_CREATE = "/core/status:create";
    /**
     * 状态更新
     */
    public static final String STATUS_UPDATE = "/core/status:update";
    /**
     * 状态更新状态
     */
    public static final String STATUS_UPDATE_STATUS = "/core/status:updateStatus";
    /**
     * 状态类型查询
     */
    public static final String STATUS_TYPE_QUERY = "/core/type:query";
    /**
     * 状态类型新增
     */
    public static final String STATUS_TYPE_CREATE = "/core/type:create";
    /**
     * 状态类型更新
     */
    public static final String STATUS_TYPE_UPDATE = "/core/type:update";
    /**
     * 状态类型删除
     */
    public static final String STATUS_TYPE_DELETE = "/core/type:delete";
    /**
     * 用户查询
     */
    public static final String USER_QUERY = "/core/user:query";
    /**
     * 用户新增
     */
    public static final String USER_CREATE = "/core/user:create";
    /**
     * 用户更新
     */
    public static final String USER_UPDATE = "/core/user:update";
    /**
     * 用户删除
     */
    public static final String USER_DELETE = "/core/user:delete";
    /**
     * 用户状态修改
     */
    public static final String USER_UPDATE_STATUS = "/core/user:updateStatus";
    /**
     * 用户添加角色
     */
    public static final String USER_ADD_ROLE = "/core/user:addRole";
    /**
     * 用户修改密码
     */
    public static final String USER_MODIFY_PASSWORD = "/core/user:modifyPwd";
}
