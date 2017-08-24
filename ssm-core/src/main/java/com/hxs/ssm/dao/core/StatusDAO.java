package com.hxs.ssm.dao.core;

import com.hxs.ssm.form.core.StatusQueryForm;
import com.hxs.ssm.model.core.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusDAO {
    int insert(Status status);

    int update(Status status);

    long count(StatusQueryForm form);

    List<Status> query(StatusQueryForm form);

    List<Status> queryByType(@Param("type") String type);

    Status findExistsByCondition(@Param("id") Integer id, @Param("type") Integer type, @Param("value") String value, @Param("name") String name);

    int updateStatus(Status status);
}
