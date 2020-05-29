package com.erp.service;

import com.erp.domain.HisLocking;
import com.erp.domain.HisLockingQuery;

import java.util.List;
import java.util.Map;


/**
 * HisLockingService接口
 *
 * @author hcy
 * @version 2020-5-29 11:20:14
 */
public interface HisLockingService {

    boolean insert(HisLocking hisLocking);

    boolean delete(Integer id);


    List<HisLocking> queryList(Map<String, Object> map);

    int count(Map<String, Object> map);

    boolean exist(HisLocking hisLocking);

}
