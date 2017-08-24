package com.hxs.ssm.dao.core;

import com.hxs.ssm.model.core.BillSequnce;
import com.hxs.ssm.model.core.BillSequnceKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BillSequnceDAO {

    BillSequnce getByKey(BillSequnceKey key);

    void add(BillSequnce sequnce);

    void update(BillSequnce sequnce);
}
