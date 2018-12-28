package com.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.ast.Var;
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

import com.erp.aspect.Log;
import com.erp.config.Constant;
import com.erp.domain.DepartmentDO;
import com.erp.domain.DoctorRevenueDo;
import com.erp.impl.DepartmentServiceImpl;
import com.erp.utils.POIUtils;
import com.erp.utils.PageUtils;
import com.erp.utils.R;
import com.erp.utils.StringUtils;

@Controller
@RequestMapping("/his/dep")
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentServiceImpl departmentServiceImpl;

	@GetMapping()
	@RequiresPermissions("his:dep:list")
	String patient(Model model) {
		return "his/finance/department";
	}

	/**
	 * 科室收入统计
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@PostMapping("/dep_upload")
	R DoctorUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws ParseException {
		InputStream in = null;
		FileOutputStream fos = null;
		try {

			MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
			MultipartFile multipartFile = params.getFile("file");
			fos = new FileOutputStream(new File(multipartFile.getOriginalFilename()));
			in = multipartFile.getInputStream();

			List<List<Object>> result=new ArrayList<List<Object>>();
			String fileName = file.getOriginalFilename();
			//2003
			if (fileName.endsWith("xls")) {
				result=POIUtils.readXlsx2003(in, 1, 18);
			}
			else
			{
				//2007
				result = POIUtils.readXlsx(in, 1, 18);
			}
			  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<DepartmentDO> list_dao = new ArrayList<>();

			for (List<Object> list : result) {
				DepartmentDO his_do = new DepartmentDO();
				if (!list.get(0).toString().equals("合计") && !list.get(0).toString().equals("")) {

					his_do.setOrganization_name(list.get(1).toString());
					his_do.setDepartment_name(list.get(2).toString());
					his_do.setCharge_date(sdf.parse(list.get(3).toString()));
					his_do.setTreatment_fee(TransformationAmount(list.get(4)));
					his_do.setRadiological_fee(TransformationAmount(list.get(5)));
					his_do.setOperation_fee(TransformationAmount(list.get(6)));
					his_do.setInspection_fee(TransformationAmount(list.get(7)));
					his_do.setCheck_fee(TransformationAmount(list.get(8)));
					his_do.setMedical_materials(TransformationAmount(list.get(9)));
					his_do.setDental_implant_fee(TransformationAmount(list.get(10)));
					his_do.setOrthodontic_fee(TransformationAmount(list.get(11)));
					his_do.setPlanting_fee(TransformationAmount(list.get(12)));
					his_do.setPediatric_treatment_fee(TransformationAmount(list.get(13)));
					his_do.setWestern_medicine_fee(TransformationAmount(list.get(14)));
					his_do.setChinese_patent_medicine(TransformationAmount(list.get(15)));
					his_do.setPediatric_treatment_fee2(TransformationAmount(list.get(16)));
					his_do.setTotal_fee(TransformationAmount(list.get(17)));
					list_dao.add(his_do);
				}
			}
			departmentServiceImpl.insertDepartment(list_dao);
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
	 * 科室列表
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	List<DepartmentDO> doctor(@RequestParam Map<String, Object> params) {

		if (params.containsKey("organization_name")) {
			List<String> strnig = new ArrayList<String>();
			String org = params.get("organization_name").toString();
			if (StringUtils.isNotEmpty(org)) {
				if (org.indexOf(",") > 0) {
					String[] str_split = org.split(",");
					for (int i = 0; i < str_split.length; i++) {
						strnig.add(str_split[i]);
					}
				} else {
					strnig.add(org);
				}
			}
			params.put("organization_name", strnig);
		}
		List<DepartmentDO> empReslist = departmentServiceImpl.departmentList(params);
		if (empReslist != null && empReslist.size() > 0) {
			DepartmentDO sum_depart = departmentServiceImpl.sumdepartmentList(params);
			empReslist.add(sum_depart);
		}
		return empReslist;
	}

	/**
	 * 金额转换
	 * 
	 * @param amount
	 * @return
	 */
	public double TransformationAmount(Object amount) {
		if (amount.toString() == "") {
			return 0;
		}
		return Double.parseDouble(amount.toString());

	}

	// @RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除科室数据")
	@PostMapping("/batchdept")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] id) {
		int r = departmentServiceImpl.deletedepartment(id);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
