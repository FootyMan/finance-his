package com.erp.impl;

import com.erp.domain.HisLocking;
import com.erp.service.HisLockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockingServerUtil {

    @Autowired
    private HisLockingService hisLockingService;


    public boolean isExist(String lockingDate,Integer lockingType)
    {
        HisLocking hisLocking=new HisLocking();
        hisLocking.setLockingDate(lockingDate);
        hisLocking.setLockingType(lockingType);
        boolean isExist=hisLockingService.exist(hisLocking);
        return isExist;
    }
}
