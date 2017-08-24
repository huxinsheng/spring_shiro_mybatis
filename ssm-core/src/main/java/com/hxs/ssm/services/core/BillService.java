package com.hxs.ssm.services.core;

import com.hxs.ssm.dao.core.BillDAO;
import com.hxs.ssm.dao.core.BillSequnceDAO;
import com.hxs.ssm.model.core.Bill;
import com.hxs.ssm.model.core.BillSequnce;
import com.hxs.ssm.model.core.BillSequnceKey;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class BillService {

    @Resource
    BillDAO billDAO;

    @Resource
    BillSequnceDAO billSequnceDAO;

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    @Transactional
    public String newBillNo(BillSequnceKey key) {
        lock.writeLock().lock();// 加锁
        try {
            Bill bill = billDAO.getByCode(key.getCode());
            int seqVal = 1;
            BillSequnce sequnce = billSequnceDAO.getByKey(key);
            if (sequnce == null) {
                sequnce = new BillSequnce();
                sequnce.setCode(key.getCode());
                sequnce.setSequnce(seqVal);
                billSequnceDAO.add(sequnce);
            } else {
                seqVal = sequnce.getSequnce() + 1;
                sequnce.setSequnce(seqVal);
                billSequnceDAO.update(sequnce);
            }
            int num = bill.getNum();
            String prefix = bill.getPrefix();
            String dateFormat = bill.getDateFormat();
            if (dateFormat == null) dateFormat = "";
            if (prefix == null) prefix = "";
            StringBuilder buff = new StringBuilder();
            buff.append(prefix);
            buff.append(new SimpleDateFormat(dateFormat).format(new Date()));
            buff.append(StringUtils.leftPad(String.valueOf(seqVal), num, '0'));
            return buff.toString();
        } finally {
            lock.writeLock().unlock();// 解锁
        }
    }

}
