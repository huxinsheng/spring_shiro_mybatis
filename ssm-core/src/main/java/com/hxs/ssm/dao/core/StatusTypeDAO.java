package com.hxs.ssm.dao.core;

import com.hxs.ssm.form.core.StatusTypeQueryForm;
import com.hxs.ssm.model.core.StatusType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusTypeDAO {
    long count(StatusTypeQueryForm form);

    List<StatusType> query(StatusTypeQueryForm form);

    int insert(StatusType statusType);

    int update(StatusType statusType);

    void deleteById(@Param("id") String id);

    StatusType findByIdAndName(@Param("id") Integer id, @Param("name") String name);
}
