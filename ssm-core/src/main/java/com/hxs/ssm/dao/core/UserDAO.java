package com.hxs.ssm.dao.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.form.core.LoginForm;
import com.hxs.ssm.form.core.UserQueryForm;
import com.hxs.ssm.model.core.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： UserDAO
 * @类描述： 用户数据持久接口
 * @创建人： huxinsheng
 * @创建时间： 2017-04-05 11:08
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Repository
public interface UserDAO {
    User findByLoginId(@Param("loginId") String loginId);

    long count(UserQueryForm form);

    List<User> query(UserQueryForm form);

    void insert(User user);

    void update(User user);

    void deleteById(String id);

    User authentication(LoginForm loginForm);

    void insertLoginInfo(UserInfo userInfo);

    void resetPassword(User user);
}
