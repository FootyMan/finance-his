package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.erp.domain.DepartmentDO;
import com.erp.domain.OutpatientDO;

@Mapper
public interface OutpatientDao {

	/**
	 * 插入数据
	 * @param patient
	 * @return
	 */
	int insertOutpatient(List<OutpatientDO> patient);
	/**
	 * 查询数据
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> outpatientList(Map<String, Object> map);
	/**
	 * 查询count
	 * @param map
	 * @return
	 */
	int outpatientListCount(Map<String, Object> map);
	/**
	 * 查询count
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);
	
}
