package com.erp.controller;

import java.io.File;
import java.io.FileInputStream;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

import com.erp.aspect.Log;
import com.erp.domain.DepartmentDO;
import com.erp.domain.DoctorRevenueDo;
import com.erp.domain.FileDO;
import com.erp.domain.HisPatientDO;
import com.erp.impl.DoctorRevenueServiceImpl;
import com.erp.impl.HisPatientServiceImpl;
import com.erp.utils.FileType;
import com.erp.utils.FileUtil;
import com.erp.utils.POIUtils;
import com.erp.utils.PageUtils;
import com.erp.utils.R;
import com.mysql.jdbc.StringUtils;

import net.sf.ehcache.hibernate.management.impl.EhcacheHibernate;

@Controller
@RequestMapping("/his/finance")
public class HisController extends BaseController {

	@Autowired
	private HisPatientServiceImpl hisPatientServiceImpl;

	@GetMapping()
	@RequiresPermissions("his:finance:patient")
	String patient(Model model) {
		return "his/finance/patient";
	}

	@GetMapping("/list")
	@ResponseBody
	List<HisPatientDO> list(@RequestParam Map<String, Object> params) {
		List<HisPatientDO> empReslist = hisPatientServiceImpl.patientList(params);
		if (empReslist != null && empReslist.size() > 0) {
			HisPatientDO sum_depart = hisPatientServiceImpl.sumpatientList(params);
			empReslist.add(sum_depart);
		}
		return empReslist;
	}

	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws ParseException {
		InputStream in = null;
		FileOutputStream fos = null;
		try {

			MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
			MultipartFile multipartFile = params.getFile("file");
			fos = new FileOutputStream(new File(multipartFile.getOriginalFilename()));
			in = multipartFile.getInputStream();
			List<List<Object>> result = new ArrayList<List<Object>>();
			String fileName = file.getOriginalFilename();
			// 2003
			if (fileName.endsWith("xls")) {
				result = POIUtils.readXlsx2003(in, 1, 13);
			} else {
				// 2007
				result = POIUtils.readXlsx(in, 1, 13);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			List<HisPatientDO> list_dao = new ArrayList<>();

			for (List<Object> list : result) {
				if (!list.get(0).toString().equals("合计") && !list.get(0).toString().equals("")) {

					HisPatientDO his_do = new HisPatientDO();
					his_do.setCase_number(list.get(0).toString());
					his_do.setName(list.get(1).toString());
					his_do.setSex(list.get(2).toString());
					his_do.setAge(Integer.parseInt(list.get(3).toString()));
					his_do.setNature(list.get(4).toString());
					his_do.setPatient_nature(list.get(5).toString());
					his_do.setInvoice_number(list.get(6).toString());
					his_do.setYb_number(list.get(7).toString());
					his_do.setPay_type(list.get(8).toString());
					his_do.setVisiting_time(sdf.parse(list.get(9).toString()));
					his_do.setCharge_time(sdf.parse(list.get(10).toString()));
					his_do.setAmount(Double.parseDouble(list.get(11).toString()));
					his_do.setPhone(list.get(12).toString());
//					his_do.setDepartment(list.get(13).toString());
//					his_do.setDoctor(list.get(14).toString());
//					his_do.setDiagnosis_msg(list.get(15).toString());
					list_dao.add(his_do);
				}
			}
			hisPatientServiceImpl.inserthispatient(list_dao);
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

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String time = "2018/12/11 11:26";
		// Date date = sdf.parse(time);
		// System.out.println(date.getTime());
		// String amout="5,202,700.02".replaceAll(",", "");
		// System.out.println(Double.parseDouble(amout));

		String dd = "2018年10月01日";
		System.out.println(dd.replace("年", "-").replaceAll("月", "-").replaceAll("日", "-"));
		System.out.println(sdf.parse(dd.replace("年", "-").replaceAll("月", "-").replaceAll("日", "")));

	}
	
	@Log("批量删除患者数据")
	@PostMapping("/batchPatient")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] id) {
		int r = hisPatientServiceImpl.deletepatient(id);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

}
