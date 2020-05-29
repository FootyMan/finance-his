package com.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.erp.domain.HisLocking;
import com.erp.impl.LockingServerUtil;
import com.erp.service.HisLockingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.erp.domain.DepartmentDO;
import com.erp.domain.OutpatientDO;
import com.erp.impl.OutpatientServiceImpl;
import com.erp.utils.POIUtils;
import com.erp.utils.PageUtils;
import com.erp.utils.R;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/his/outpatient")
public class OutpatientController extends BaseController {

	@Autowired
	private OutpatientServiceImpl outpatientServiceImpl;
	@Autowired
	private LockingServerUtil lockingServerUtil;

	@GetMapping()
	@RequiresPermissions("his:outpatient:list")
	String patient(Model model) {
		return "his/finance/outpatient";
	}
	

	/**
	 * 门诊
	 * @param file
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@PostMapping("/outpatient_upload")
	R DoctorUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws ParseException {
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM");
			String lockingDate="";
			MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
			MultipartFile multipartFile = params.getFile("file");
			fos = new FileOutputStream(new File(multipartFile.getOriginalFilename()));
			in = multipartFile.getInputStream();
			
			List<List<Object>> result=new ArrayList<List<Object>>();
			String fileName = file.getOriginalFilename();
			//2003
			if (fileName.endsWith("xls")) {
				result=POIUtils.readXlsx2003(in, 1, 21);
			}
			else
			{
				//2007
				result = POIUtils.readXlsx(in, 1, 21);
			}
			List<OutpatientDO> list_dao = new ArrayList<>();

			System.out.println(result.get(1));
			List<Object> title_list=result.get(0);
			List<Object> hospital_list=result.get(1);
			List<Object> summary_list=result.get(3);
			String title=title_list.get(0).toString();
			String hospital=hospital_list.get(0).toString();
			String summary=summary_list.get(0).toString();
			
			//最后一行数据
			System.out.println(result.size());
			List<Object> tail_list=result.get(result.size()-2);
			String reviewer=tail_list.get(1).toString();//复核人
			String table_name=tail_list.get(5).toString();//制表人
			String table_date=tail_list.get(13).toString();//制表日期
			String summary_date_begin="";
			for (List<Object> list : result) {
				//如果第一列有数据 则认为可读取 其他不读取
				String onecell=list.get(0).toString();
				if (!onecell.equals("") && !onecell.equals("科    目") && !onecell.equals(title) 
						&& !onecell.equals(hospital) && !onecell.equals(summary) &&!onecell.equals("复核人：")) {
					OutpatientDO his_do = new OutpatientDO();
					his_do.setTitle(title);
					his_do.setHospital_name(hospital);
					String str_data=summary.replaceAll("汇总日期：", "").trim();
					
					summary_date_begin=str_data.replace("年", "-").replaceAll("月", "-").replaceAll("日", "");
					his_do.setSummary_date_begin(sdf.parse(summary_date_begin));
//					his_do.setSummary_date_end(sdf.parse(str_data[1].replace("年", "-").replaceAll("月", "-").replaceAll("日", "")));
					his_do.setSubject(list.get(0).toString());
					his_do.setAmount_received(TransformationAmount(list.get(2)));
					his_do.setSubject1(list.get(3).toString());
					System.out.println(his_do.getSubject());
					his_do.setReceived_no(list.get(6).toString());
					his_do.setReceived_money(TransformationAmount(list.get(7)));
					his_do.setDiscard_no(list.get(10).toString());
					his_do.setDiscard_money(TransformationAmount(list.get(11)));
					his_do.setRush_accounts_no(list.get(15).toString());
					his_do.setRush_accounts_money(TransformationAmount(list.get(17)));
					his_do.setReviewer(reviewer);
					his_do.setTable_name(getUsername());
					his_do.setTable_date(table_date);

					lockingDate=sdfTwo.format(his_do.getSummary_date_begin());
					list_dao.add(his_do);
					
				}
				 
				
				
//				his_do.setDepartment_name(list.get(1).toString());
//				his_do.setTreatment_fee(TransformationAmount(list.get(2)));
//				his_do.setRadiological_fee(TransformationAmount(list.get(3)));
//				his_do.setOperation_fee(TransformationAmount(list.get(4)));
//				his_do.setInspection_fee(TransformationAmount(list.get(5)));
//				his_do.setCheck_fee(TransformationAmount(list.get(6)));
//				his_do.setMedical_materials(TransformationAmount(list.get(7)));
//				his_do.setDental_implant_fee(TransformationAmount(list.get(8)));
//				his_do.setOrthodontic_fee(TransformationAmount(list.get(9)));
//				his_do.setPlanting_fee(TransformationAmount(list.get(10)));
//				his_do.setPediatric_treatment_fee(TransformationAmount(list.get(11)));
//				his_do.setWestern_medicine_fee(TransformationAmount(list.get(12)));
//				his_do.setChinese_patent_medicine(TransformationAmount(list.get(13)));
//				his_do.setPediatric_treatment_fee2(TransformationAmount(list.get(14)));
//				his_do.setTotal_fee(TransformationAmount(list.get(15)));
				
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("summary_date_begin", summary_date_begin);

			boolean isExist=lockingServerUtil.isExist(lockingDate,4);
			if(isExist)
			{
				return R.error("当前月份已锁定");
			}

			int count = outpatientServiceImpl.count(map);
			if (count <= 0) {
				outpatientServiceImpl.insertOutpatient(list_dao);
			}
			else {
				return R.error("当前日期已存在");
			}
			
		} catch (Exception e) {
			return R.error();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return R.ok();
	}
	
	/**
	 * 门诊列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	PageUtils doctor(@RequestParam Map<String, Object> params) {
		List<OutpatientDO> empReslist = outpatientServiceImpl.outpatientList(params);
	  if (empReslist!=null && empReslist.size()>0) {
		  OutpatientDO outpatientDO=empReslist.get(0);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  outpatientDO.setTable_date(sdf.format(new Date()));
		  empReslist.set(0, outpatientDO);
	}
		int total = outpatientServiceImpl.outpatientListCount(params);
		PageUtils pageUtil = new PageUtils(empReslist, total);
		return pageUtil;
	}
	
	/**
	 * 金额转换
	 * @param amount
	 * @return
	 */
	public double TransformationAmount(Object amount)
	{
		if(amount.toString()=="")
		{
			return 0;
		}
		return Double.parseDouble(amount.toString().replaceAll(",", ""));
		
	}
}
