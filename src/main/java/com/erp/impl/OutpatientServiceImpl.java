package com.erp.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.runtime.DotClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.OutpatientDao;
import com.erp.domain.OutpatientDO;
import com.erp.service.OutpatientService;

@Service
public class OutpatientServiceImpl implements  OutpatientService{

	@Autowired
	private OutpatientDao outpatientDao;
	@Override
	public int insertOutpatient(List<OutpatientDO> patient) {
		// TODO Auto-generated method stub
		return outpatientDao.insertOutpatient(patient);
	}

	@Override
	public List<OutpatientDO> outpatientList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		List<OutpatientDO> outpatientDOs=new ArrayList<>();
		List<Map<String, Object>> map_obj= outpatientDao.outpatientList(map);
		for (Map<String, Object> item : map_obj) {
			OutpatientDO dto=new OutpatientDO();
			dto.setTitle(item.get("title").toString());
			dto.setHospital_name(item.get("hospital_name").toString());
			dto.setSubject(item.get("subject").toString());
			dto.setAmount_received(Double.parseDouble(item.get("amount_received").toString()));
			dto.setSubject1(item.get("subject1").toString());
			dto.setReceived_no(item.get("received_no").toString());
			dto.setReceived_money(Double.parseDouble(item.get("received_money").toString()));
			dto.setDiscard_no(item.get("discard_no").toString());
			dto.setDiscard_money(Double.parseDouble(item.get("discard_money").toString()));
			dto.setRush_accounts_no(item.get("rush_accounts_no").toString());
			dto.setRush_accounts_money(Double.parseDouble(item.get("rush_accounts_money").toString()));
			try {
				dto.setSummary_date_begin(simpleDateFormat.parse(map.get("summary_date_begin").toString()));
				dto.setSummary_date_end(simpleDateFormat.parse(map.get("summary_date_end").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outpatientDOs.add(dto);
		}
		return outpatientDOs;
	}

	@Override
	public int outpatientListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outpatientDao.outpatientListCount(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return outpatientDao.count(map);
	}

}
