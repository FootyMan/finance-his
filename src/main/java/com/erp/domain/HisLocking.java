package com.erp.domain;

import java.math.BigDecimal;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * HisLocking：实体类
 *
 * @author hcy
 * @version 2020-5-29 11:20:14
 */
public class HisLocking implements java.io.Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 锁定日期
     */
    private String lockingDate;
    /**
     * 0所有锁定 1门诊患者 2医生收入 3科室收入 4门诊汇总
     */
    private Integer lockingType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockingDate() {
        return lockingDate;
    }

    public void setLockingDate(String lockingDate) {
        this.lockingDate = lockingDate;
    }

    public Integer getLockingType() {
        return lockingType;
    }

    public void setLockingType(Integer lockingType) {
        this.lockingType = lockingType;
    }
}
