package com.hxs.ssm.services.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.dao.core.UserDAO;
import com.hxs.ssm.dao.core.UserRoleDAO;
import com.hxs.ssm.form.core.LoginForm;
import com.hxs.ssm.form.core.UserAddForm;
import com.hxs.ssm.form.core.UserQueryForm;
import com.hxs.ssm.form.core.UserUpdateForm;
import com.hxs.ssm.model.BaseModel;
import com.hxs.ssm.model.core.User;
import com.hxs.ssm.model.core.UserRole;
import com.hxs.ssm.result.Result;
import com.hxs.ssm.result.TablePaginationResult;
import com.hxs.ssm.result.TableResult;
import com.hxs.ssm.utils.CommonUtils;
import com.hxs.ssm.utils.Const;
import com.hxs.ssm.utils.digest.DigestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： UserServiceImpl
 * @类描述： 用户服务接口实现
 * @创建人： huxinsheng
 * @创建时间： 2017-04-05 11:04
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Service
public class UserService {

    @Resource
    UserDAO userDAO;

    @Resource
    UserRoleService userRoleService;

    @Resource
    UserRoleDAO userRoleDAO;

    public Result login(LoginForm form) {
        String loginId = form.getLoginId();
        User user = userDAO.findByLoginId(loginId);
        if (null != user) {
            String password = DigestUtil.getSha1PassCount2(form.getPassword(), form.getLoginId());
            if (!password.equals(user.getPassword())) {
                return Result.error(Result.LOGIN_PWD_ERROR);
            }
        }
        return Result.ok(user);
    }

    public User authentication(LoginForm loginForm) {
        String password = DigestUtil.getSha1PassCount2(loginForm.getPassword(), loginForm.getLoginId());
        loginForm.setPassword(password);
        return userDAO.authentication(loginForm);
    }

    public User findByLoginId(String loginId) {
        return userDAO.findByLoginId(loginId);
    }

    public Result checkPassword(UserQueryForm form) {
        String password = DigestUtil.getSha1PassCount2(form.getPassword(), form.getLoginId());
        form.setPassword(password);
        List<User> list = userDAO.query(form);
        if (CollectionUtils.isNotEmpty(list)) {
            return Result.ok(true);
        }
        return Result.ok(false);
    }

    public TableResult<User> query(UserQueryForm form) {
        if (null != form) {
            long count = userDAO.count(form);
            List<User> list = userDAO.query(form);
            return new TablePaginationResult<User>(count, list, form);
        } else {
            return null;
        }
    }

    @Transactional
    public Result add(UserInfo userInfo, UserAddForm form) {
        User user = userDAO.findByLoginId(form.getLoginId());
        if (null != user) {
            return Result.error(Result.EXISTS, "登录id已存在.");
        }
        user = new User();
        BeanUtils.copyProperties(form, user);
        user.setId(BaseModel.newId());
        CommonUtils.fillingValue(userInfo, user, Const.ADD);
        String password = DigestUtil.getSha1PassCount2(user.getPassword(), user.getLoginId());
        user.setPassword(password);
        user.setFirstLogin(false);
        userDAO.insert(user);
        return Result.ok();
    }

    @Transactional
    public Result update(UserInfo userInfo, UserUpdateForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);
        CommonUtils.fillingValue(userInfo, user, Const.ADD);
        String password = DigestUtil.getSha1PassCount2(user.getPassword(), userInfo.getLoginId());
        user.setPassword(password);
        userDAO.update(user);
        return Result.ok();
    }

    @Transactional
    public Result delete(UserInfo userInfo, String id) {
        //删除用户角色信息
        userRoleService.deleteByUserIdAndRoleId(id, null);
        //删除用户信息
        userDAO.deleteById(id);
        return Result.ok();
    }

    public void addLoginInfo(UserInfo userInfo) {
        userDAO.insertLoginInfo(userInfo);
    }

    /**
     * 同步用户信息
     *
     * @param loginId 账户登录id
     * @param status  状态0:禁用，1启用
     * @return
     */
    @Transactional
    public Result sync(String loginId, String name, String status) {
        User user = userDAO.findByLoginId(loginId);
        if (null != user) {//同步用户信息已存在,只修改状态
            User updateUser = new User();
            updateUser.setId(user.getId());
            updateUser.setDisabled(status.equals("1"));
            updateUser.setUpdateBy("000000");
            updateUser.setName(name);
            updateUser.setUpdateTime(new Date());
            userDAO.update(updateUser);
        } else {
            user = new User();
            user.setId(BaseModel.newId());
            user.setLoginId(loginId);
            user.setName(name);
            String password = loginId.substring(5, loginId.length());
            password = DigestUtils.sha256Hex(password);
            password = DigestUtil.getSha1PassCount2(password, loginId);
            user.setPassword(password);
            user.setDisabled(status.equals("1"));
            user.setCreator("000000");
            user.setCreateTime(new Date());
            user.setUpdateBy("000000");
            user.setUpdateTime(new Date());
            user.setFirstLogin(true);
            userDAO.insert(user);
            //添加默认角色信息
            List<UserRole> userRoles = new ArrayList<UserRole>();
            UserRole userRole = new UserRole();
            userRole.setRoleId("R00002");
            userRole.setUserId(user.getId());
            userRole.setCreator("000000");
            userRole.setCreateTime(new Date());
            userRoles.add(userRole);
            userRoleDAO.insertAll(userRoles);
        }
        return Result.ok();
    }

    /**
     * 设置或者重置合作商密码
     *
     * @param form
     * @return
     */
    public Result resetPassword(UserUpdateForm form) {
        User user = userDAO.findByLoginId(form.getLoginId());
        String password = DigestUtil.getSha1PassCount2(form.getPassword(), form.getLoginId());
        user.setPassword(password);
        user.setUpdateBy("000000");
        user.setUpdateTime(new Date());
        user.setFirstLogin(false);
        userDAO.resetPassword(user);
        return Result.ok();
    }

    public static void main(String[] args) {
        String pwd = "bluebird82";
        pwd = DigestUtils.sha256Hex(pwd);
        pwd = DigestUtil.getSha1PassCount2(pwd, "15013713890");
        System.out.println(pwd);
    }
}
