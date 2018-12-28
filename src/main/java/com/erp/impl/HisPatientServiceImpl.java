package com.erp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.HisPatientDao;
import com.erp.domain.HisPatientDO;
import com.erp.service.HisPatientService;

@Service
public class HisPatientServiceImpl implements HisPatientService {

	@Autowired
	private HisPatientDao hisPatientDao;
	
	@Override
	public int inserthispatient(List<HisPatientDO> patient) {
		// TODO Auto-generated method stub
		return hisPatientDao.inserthispatient(patient);
	}

	@Override
	public List<HisPatientDO> patientList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hisPatientDao.patientList(map);
	}

	@Override
	public int patientListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hisPatientDao.patientListCount(map);
	}

	@Override
	public HisPatientDO sumpatientList(Map<String, Object> map) {
		List<HisPatientDO> list_his=hisPatientDao.sumpatientList(map);
		return list_his.get(0);
	}

	@Override
	public int deletepatient(Long[] id) {
		// TODO Auto-generated method stub
		return hisPatientDao.deletepatient(id);
	}

}
