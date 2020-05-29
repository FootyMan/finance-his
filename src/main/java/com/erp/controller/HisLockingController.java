package com.erp.controller;

import com.erp.aspect.Log;
import com.erp.domain.DepartmentDO;
import com.erp.domain.HisLocking;
import com.erp.domain.HisLockingQuery;
import com.erp.service.HisLockingService;
import com.erp.utils.PageUtils;
import com.erp.utils.Query;
import com.erp.utils.R;
import com.erp.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/his/locking")
public class HisLockingController extends BaseController {

    String prefix = "locking";

    @Autowired
    private HisLockingService hisLockingService;

    @GetMapping()
    @RequiresPermissions("his:locking:list")
    String patient(Model model) {
        return prefix + "/list";
    }


    /**
     * 锁定列表
     *
     * @param params
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<HisLocking> hisLockingList = hisLockingService.queryList(query);
        int total = hisLockingService.count(query);
        PageUtils pageUtil = new PageUtils(hisLockingList, total);
        return pageUtil;
    }

    @Log("添加锁定")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @Log("保存锁定")

    @PostMapping("/save")
    @ResponseBody()
    R save(String lockingDate, Integer lockingType) {
        if (Objects.isNull(lockingDate) || Objects.isNull(lockingType)) {
            return R.error(500, "参数不能为空");
        }
        HisLocking hisLocking = new HisLocking();
        hisLocking.setLockingDate(lockingDate);
        hisLocking.setLockingType(lockingType);
        if (hisLockingService.insert(hisLocking)) {
            return R.ok();
        } else {
            return R.error(1, "保存失败！请查看是否存在");
        }
    }

    @Log("删除锁定")
    @PostMapping("/remove")
    @ResponseBody()
    R save(int id) {
        if (hisLockingService.delete(id)) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }
}
