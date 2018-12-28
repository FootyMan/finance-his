package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.erp.domain.DepartmentDO;

@Mapper
public interface DepartmentDao {

	/**
	 * 插入数据
	 * @param patient
	 * @return
	 */
	int insertDepartment(List<DepartmentDO> patient);
	/**
	 * 查询数据
	 * @param map
	 * @return
	 */
	List<DepartmentDO> departmentList(Map<String, Object> map);
	/**
	 * 查询count
	 * @param map
	 * @return
	 */
	int departmentListCount(Map<String, Object> map);
	/**
	 * 统计
	 * @param map
	 * @return
	 */
	List<DepartmentDO> sumdepartmentList(Map<String, Object> map);
	
	/**
	 * 删除
	 * @param userIds
	 * @return
	 */
	int deletedepartment(Long[] id);
}
