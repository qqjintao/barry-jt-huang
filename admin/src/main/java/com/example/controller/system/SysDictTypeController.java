package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.constant.Constants;
import com.example.controller.BaseController;
import com.example.model.SysDictType;
import com.example.service.ISysDictTypeService;
import com.example.util.SecurityUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/29 0029
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private ISysDictTypeService dictTypeService;

    @GetMapping("/list")
    public CommonResult<CommonPage<SysDictType>> list(SysDictType dictType, Date beginTime, Date endTime) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType,beginTime,endTime);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 查询字典类型详细
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(dictTypeService.selectDictTypeById(id));
    }

    /**
     * 新增字典类型
     */
    @JsonDeserialize
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysDictType dict) {
        if (Constants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return CommonResult.failed("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUserId());
        dict.setCreateTime(new Date());
        return CommonResult.toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysDictType dict) {
        if (Constants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return CommonResult.failed("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUserId());
        dict.setUpdateTime(new Date());
        return CommonResult.toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(dictTypeService.deleteDictTypeByIds(ids));
    }

    /**
     * 清空缓存
     */
    @DeleteMapping("/clearCache")
    public CommonResult clearCache() {
        dictTypeService.clearCache();
        return CommonResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public CommonResult optionselect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return CommonResult.success(dictTypes);
    }
}
