package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.erp.domain.HisPatientDO;

@Mapper
public interface HisPatientDao {

	/**
	 * 插入数据
	 * @param patient
	 * @return
	 */
	int inserthispatient(List<HisPatientDO> patient);
	/**
	 * 查询数据
	 * @param map
	 * @return
	 */
	List<HisPatientDO> patientList(Map<String, Object> map);
	/**
	 * 查询count
	 * @param map
	 * @return
	 */
	int patientListCount(Map<String, Object> map);
	/**
	 * 统计
	 * @param map
	 * @return
	 */
	
	List<HisPatientDO> sumpatientList(Map<String, Object> map);
	/**
	 * 删除
	 * @param userIds
	 * @return
	 */
	int deletepatient(Long[] id);
}
