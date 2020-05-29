package com.erp.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * HisLockingQuery：查询类
 *
 * @author hcy
 * @version 2020-5-29 11:20:14
 */
public class HisLockingQuery implements java.io.Serializable {

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
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
