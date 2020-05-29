package com.erp.dao;

import com.erp.domain.HisLocking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * HisLockingDao接口<br/>
 * 对''表进行基本的操作
 *
 * @author hcy
 * @version 2020-5-29 11:20:14
 */
@Mapper
public interface HisLockingDao {


    int insert(HisLocking hisLocking);


    int delete(Integer id);


    List<HisLocking> queryList(Map<String, Object> map);


    Integer exist(HisLocking hisLocking);

    int count(Map<String,Object> map);
}
