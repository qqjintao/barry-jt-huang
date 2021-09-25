package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.model.SysDictData;
import com.example.model.SysUser;
import com.example.service.ISysDictDataService;
import com.example.service.ISysDictTypeService;
import com.example.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    //    @PreAuthorize("hasAuthority('system:dict:list')")
    @GetMapping("/list")
    public CommonResult<CommonPage<SysDictData>> list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 查询字典数据详细
     */
//    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(dictDataService.selectDictDataById(id));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public CommonResult dictType(@PathVariable String dictType) {
        return CommonResult.success(dictTypeService.selectDictDataByType(dictType));
    }

    /**
     * 新增字典类型
     */
//    @PreAuthorize("hasAuthority('system:dict:add')")
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysDictData dict) {
        return CommonResult.toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
//    @PreAuthorize("hasAuthority('system:dict:edit')")
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(SecurityUtils.getUserId());
        return CommonResult.toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
//    @PreAuthorize("hasAuthority('system:dict:remove')")
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(dictDataService.deleteDictDataByIds(ids));
    }
}
