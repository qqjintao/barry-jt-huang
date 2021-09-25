package com.example.controller.system;

import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.model.SysDept;
import com.example.service.ISysDeptService;
import com.example.untils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @GetMapping("/list")
    public CommonResult list(SysDept dept) {
        List<SysDept> list = deptService.selectDeptList(dept);
        return CommonResult.success(list);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(deptService.selectDeptById(id));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public CommonResult treeselect(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return CommonResult.success(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 查询部门列表（排除节点）
     */
    @GetMapping("/list/exclude/{id}")
    public CommonResult excludeChild(@PathVariable(value = "id", required = false) Long id) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Iterator<SysDept> it = depts.iterator();
        while (it.hasNext()) {
            SysDept d = it.next();
            if (d.getId().intValue() == id
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), id + "")) {
                it.remove();
            }
        }
        return CommonResult.success(depts);
    }


    /**
     * 新增部门
     */
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysDept dept) {
        return CommonResult.toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysDept dept) {
        if (dept.getParentId().equals(dept.getId())) {
            return CommonResult.failed("修改部门'" + dept.getDeptNameZh() + "'失败，上级部门不能是自己");
        }
        return CommonResult.toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public CommonResult remove(@PathVariable Long id) {
        if (deptService.hasChildByDeptId(id)) {
            return CommonResult.failed("存在下级部门,不允许删除");
        }
//        if (deptService.checkDeptExistUser(id)) {
//            return CommonResult.failed("部门存在用户,不允许删除");
//        }
        return CommonResult.toAjax(deptService.deleteDeptById(id));
    }
}
