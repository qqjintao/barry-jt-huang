package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.annotation.RepeatSubmit;
import com.example.constant.Constants;
import com.example.controller.BaseController;
import com.example.model.SysConfig;
import com.example.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {
    @Autowired
    private ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<SysConfig>> list(SysConfig config, Date beginTime, Date endTime) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config, beginTime, endTime);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据参数编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Long id) {
        return CommonResult.success(configService.selectConfigById(id));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public CommonResult getConfigKey(@PathVariable String configKey) {
        return CommonResult.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @PostMapping
    @RepeatSubmit
    public CommonResult add(@Validated @RequestBody SysConfig config) {
        if (Constants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return CommonResult.failed("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return CommonResult.toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysConfig config) {
        if (Constants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return CommonResult.failed("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return CommonResult.toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return CommonResult.toAjax(configService.deleteConfigByIds(ids));
    }

    /**
     * 清空缓存
     */
    @DeleteMapping("/clearCache")
    public CommonResult clearCache() {
        configService.clearCache();
        return CommonResult.success();
    }
}
