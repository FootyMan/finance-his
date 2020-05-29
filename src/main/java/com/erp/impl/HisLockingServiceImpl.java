package com.erp.impl;

import java.util.List;
import java.util.Map;

import com.erp.dao.HisLockingDao;
import com.erp.domain.HisLocking;
import com.erp.domain.HisLockingQuery;
import com.erp.service.HisLockingService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * HisLockingService接口的实现类
 *
 * @author hcy
 * @version 2020-5-29 11:20:14
 */
@Service
public class HisLockingServiceImpl implements HisLockingService {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HisLockingServiceImpl.class);

    @Autowired
    private HisLockingDao hisLockingDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(HisLocking hisLocking) {
        boolean resultFlag = false;
        try {
            if (null != hisLocking) {
                if (hisLockingDao.exist(hisLocking) > 0) {
                    throw new Exception("已经存在");
                }
                resultFlag = hisLockingDao.insert(hisLocking)>0;
            } else {
                LOGGER.warn("HisLockingServiceImpl#insert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOGGER.warn("HisLockingServiceImpl#insert failed, hisLocking has existed.");
        }
        return resultFlag;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Integer id) {
        boolean resultFlag = false;
        try {

            resultFlag = hisLockingDao.delete(id)>0;

        } catch (Exception e) {
            LOGGER.error("HisLockingServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HisLocking> queryList(Map<String, Object> map) {
        List<HisLocking> hisLockingList = null;
        try {
            hisLockingList = hisLockingDao.queryList(map);
        } catch (Exception e) {
            LOGGER.error("HisLockingServiceImpl#queryList has error.", e);
        }
        return hisLockingList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return hisLockingDao.count(map);
    }

    @Override
    public boolean exist(HisLocking hisLocking) {
        return hisLockingDao.exist(hisLocking) > 0;
    }
}

