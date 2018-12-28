package com.erp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.erp.domain.DoctorRevenueDo;

@Mapper
public interface DoctorRevenueService {

	/**
	 * 插入数据
	 * 
	 * @param patient
	 * @return
	 */
	int insertdoctor(List<DoctorRevenueDo> patient);

	/**
	 * 查询数据
	 * 
	 * @param map
	 * @return
	 */
	List<DoctorRevenueDo> doctorList(Map<String, Object> map);

	/**
	 * 查询count
	 * 
	 * @param map
	 * @return
	 */
	int doctorListCount(Map<String, Object> map);

	/**
	 * 统计
	 * 
	 * @param map
	 * @return
	 */
	DoctorRevenueDo sumdoctorList(Map<String, Object> map);

	/**
	 * 删除
	 * 
	 * @param userIds
	 * @return
	 */
	int deletedoctor(Long[] id);

}
