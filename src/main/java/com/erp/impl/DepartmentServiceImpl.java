package com.erp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.DepartmentDao;
import com.erp.domain.DepartmentDO;
import com.erp.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public int insertDepartment(List<DepartmentDO> patient) {
		// TODO Auto-generated method stub
		return departmentDao.insertDepartment(patient);
	}

	@Override
	public List<DepartmentDO> departmentList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return departmentDao.departmentList(map);
	}

	@Override
	public int departmentListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return departmentDao.departmentListCount(map);
	}

	@Override
	public DepartmentDO sumdepartmentList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<DepartmentDO> departments = departmentDao.sumdepartmentList(map);
		return departments.get(0);
	}

	@Override
	public int deletedepartment(Long[] id) {
		// TODO Auto-generated method stub
		return departmentDao.deletedepartment(id);
	}

}
