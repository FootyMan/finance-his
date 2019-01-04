package com.erp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.DoctorRevenueDao;
import com.erp.domain.DoctorRevenueDo;
import com.erp.service.DoctorRevenueService;

@Service
public class DoctorRevenueServiceImpl implements DoctorRevenueService {

	@Autowired
	private DoctorRevenueDao doctorRevenueDao;
	
	@Override
	public int insertdoctor(List<DoctorRevenueDo> patient) {
		// TODO Auto-generated method stub
		return doctorRevenueDao.insertdoctor(patient);
	}

	@Override
	public List<DoctorRevenueDo> doctorList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return doctorRevenueDao.doctorList(map);
	}

	@Override
	public int doctorListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return doctorRevenueDao.doctorListCount(map);
	}

	@Override
	public DoctorRevenueDo sumdoctorList(Map<String, Object> map) {
		List<DoctorRevenueDo> doctorRevenueDos=doctorRevenueDao.sumdoctorList(map);
		return doctorRevenueDos.get(0);
	}

	@Override
	public int deletedoctor(Long[] id) {
		// TODO Auto-generated method stub
		return doctorRevenueDao.deletedoctor(id);
	}

	/**
	 * 按医生和科室统计
	 */
	@Override
	public List<DoctorRevenueDo> sumdoctorListbydectorname(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return doctorRevenueDao.sumdoctorListbydectorname(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return doctorRevenueDao.count(map);
	}

}
