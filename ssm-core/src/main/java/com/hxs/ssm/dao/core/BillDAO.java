package com.hxs.ssm.dao.core;

import com.hxs.ssm.model.core.Bill;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDAO {

    Bill getByCode(String code);
}
