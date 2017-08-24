package com.hxs.ssm.dao.core;

import com.hxs.ssm.form.core.PermissionQueryForm;
import com.hxs.ssm.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDAO {

    int insert(Permission pojo);

    int update(Permission pojo);

    long count(PermissionQueryForm form);

    List<Permission> query(PermissionQueryForm form);

    String getNewCode();

    Permission findByIdAndName(@Param("id") String id, @Param("name") String name);

    void deleteById(@Param("id") String id);
}
