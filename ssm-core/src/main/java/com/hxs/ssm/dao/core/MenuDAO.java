package com.hxs.ssm.dao.core;

import com.hxs.ssm.form.core.MenuQueryForm;
import com.hxs.ssm.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @项目名称： hxs-mp
 * @类名称： MenuDAO
 * @类描述： 菜单数据持久接口
 * @创建人： huxinsheng
 * @创建时间： 2017-04-06 09:39
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 */
@Repository("menuDAO")
public interface MenuDAO {
    List<Menu> findByEntity(MenuQueryForm form);

    List<Menu> findByParent(@Param("parent") String parent);

    Menu findByMenuName(@Param("id") String id, @Param("name") String name);

    Menu findByUrl(@Param("url") String url);

    int insert(Menu menu);

    int update(Menu form);

    int deleteById(@Param("id") String id);

    void deleteByParent(@Param("id") String id);

    List<Menu> findByUserIdAndParent(@Param("userId") String userId, @Param("parent") String parent);
}
