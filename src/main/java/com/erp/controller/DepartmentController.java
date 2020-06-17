package com.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.erp.domain.HisLocking;
import com.erp.impl.LockingServerUtil;
import com.erp.service.HisLockingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private LockingServerUtil lockingServerUtil;

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

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

            List<List<Object>> result = new ArrayList<List<Object>>();
            String fileName = file.getOriginalFilename();
            // 2003
            if (fileName.endsWith("xls")) {
                result = POIUtils.readXlsx2003(in, 1, 26);
            } else {
                // 2007
                result = POIUtils.readXlsx(in, 1, 26);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM");
            List<DepartmentDO> list_dao = new ArrayList<>();
            String lockingDate="";

            String charge_date = "";
            for (List<Object> list : result) {
                DepartmentDO his_do = new DepartmentDO();
                if (!list.get(0).toString().equals("合计") && !list.get(0).toString().equals("")) {
                    //机构名称
                    his_do.setOrganization_name(list.get(1).toString());
                    //科室名称
                    his_do.setDepartment_name(list.get(2).toString());
                    //收费日期
                    his_do.setCharge_date(sdf.parse(list.get(3).toString()));
                    //治疗费
                    his_do.setTreatment_fee(TransformationAmount(list.get(4)));
                    //放射费
                    his_do.setRadiological_fee(TransformationAmount(list.get(5)));
                    //手术费
                    his_do.setOperation_fee(TransformationAmount(list.get(6)));
                    //材料费
                    his_do.setMaterial_cost(TransformationAmount(list.get(7)));
                    //麻醉费
                    his_do.setAnesthesia_cost(TransformationAmount(list.get(8)));
                    //检验费
                    his_do.setInspection_fee(TransformationAmount(list.get(9)));
                    //正畸材料费
                    his_do.setZj_material_cost(TransformationAmount(list.get(10)));
                    //治疗一次性医药用材
                    his_do.setTreatment_medical_materials(TransformationAmount(list.get(11)));
                    //检查一次性医药用材
                    his_do.setCheck_medical_materials(TransformationAmount(list.get(12)));
                    //镶牙费
                    his_do.setDental_implant_fee(TransformationAmount(list.get(13)));
                    //正畸费
                    his_do.setOrthodontic_fee(TransformationAmount(list.get(14)));
                    //种植费
                    his_do.setPlanting_fee(TransformationAmount(list.get(15)));
                    //儿童治疗费
                    his_do.setPediatric_treatment_fee(TransformationAmount(list.get(16)));
                    //种植材料费
                    his_do.setPlanting_material_cost(TransformationAmount(list.get(17)));
                    //医疗小计
                    his_do.setSubtotal_medical_treatment(TransformationAmount(list.get(18)));
                    //西药费
                    his_do.setWestern_medicine_fee(TransformationAmount(list.get(19)));
                    //中成药
                    his_do.setChinese_patent_medicine(TransformationAmount(list.get(20)));
                    //儿童治疗费
                    his_do.setPediatric_treatment_fee2(TransformationAmount(list.get(21)));
                    //药品小计
                    his_do.setSubtotal_drugs(TransformationAmount(list.get(22)));
                    //合计
                    his_do.setTotal_fee(TransformationAmount(list.get(23)));
                    //自付合计
                    his_do.setTotal_pocket(TransformationAmount(list.get(24)));
                    //应收合计
                    his_do.setTotal_pocket(TransformationAmount(list.get(25)));

                    //his_do.setCheck_fee(TransformationAmount(list.get(8)));
                    //his_do.setMedical_materials(TransformationAmount(list.get(9)));
                    //his_do.setDental_implant_fee(TransformationAmount(list.get(10)));
                    //his_do.setOrthodontic_fee(TransformationAmount(list.get(11)));


                    his_do.setTable_name(getUsername());
                    charge_date = list.get(3).toString();
                    lockingDate=sdfTwo.format(his_do.getCharge_date());
                    list_dao.add(his_do);
                }
            }
            // 插入之前查询是否已存再当前收费日期记录
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("charge_date", charge_date);

            boolean isExist=lockingServerUtil.isExist(lockingDate,3);
            if(isExist)
            {
                return R.error("当前月份已锁定");
            }

            int count = departmentServiceImpl.count(map);
            if (count <= 0) {
                departmentServiceImpl.insertDepartment(list_dao);
            } else {
                return R.error("当前收费日期已存在");
            }

        } catch (Exception e) {
            log.error("dep_upload.error", e);
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

        List<DepartmentDO> empReslist = new ArrayList<DepartmentDO>();
        // countType 1科室明细统计 2科室合并统计
        if (params.get("countType").toString().equals("1")) {
            empReslist = departmentServiceImpl.departmentList(params);
        } else if (params.get("countType").toString().equals("2")) {
            empReslist = departmentServiceImpl.sumdepartmentListbydepartment(params);
        }
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
