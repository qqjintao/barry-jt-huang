package com.example.controller.system;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.controller.BaseController;
import com.example.dto.SysNoticeDTO;
import com.example.model.SysNotice;
import com.example.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {
    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @GetMapping("/list")
    public CommonResult list(SysNotice notice) {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable Integer id) {
        return CommonResult.success(noticeService.selectNoticeById(id));
    }

    /**
     * 新增通知公告
     */
    @PostMapping
    public CommonResult add(@Validated @RequestBody SysNoticeDTO noticeDTO) {
        return CommonResult.toAjax(noticeService.insertNotice(noticeDTO.changeSysNotice()));
    }

    /**
     * 修改通知公告
     */
    @PutMapping
    public CommonResult edit(@Validated @RequestBody SysNoticeDTO noticeDTO) {
        return CommonResult.toAjax(noticeService.updateNotice(noticeDTO.changeSysNotice()));
    }

    /**
     * 删除通知公告
     */
    @DeleteMapping("/{noticeIds}")
    public CommonResult remove(@PathVariable Integer[] noticeIds) {
        return CommonResult.toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
