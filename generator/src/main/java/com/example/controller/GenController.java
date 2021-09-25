package com.example.controller;

import com.example.CommonPage;
import com.example.CommonResult;
import com.example.domain.GenTable;
import com.example.model.GenTableColumn;
import com.example.service.IGenTableColumnService;
import com.example.service.IGenTableService;
import com.example.text.Convert;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author barry.jt.huang
 */
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {
    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @GetMapping("/list")
    public CommonResult genList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 修改代码生成业务
     */
    @PreAuthorize("hasAuthority('tool:gen:query')")
    @GetMapping(value = "/{talbleId}")
    public CommonResult getInfo(@PathVariable Long talbleId) {
        GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return CommonResult.success(map);
    }

    /**
     * 查询数据库列表
     */
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @GetMapping("/db/list")
    public CommonResult dataList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 查询数据表字段列表
     */
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @GetMapping(value = "/column/{talbleId}")
    public CommonResult columnList(Long id) {
        startPage();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(id);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 导入表结构（保存）
     */
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @PostMapping("/importTable")
    public CommonResult importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return CommonResult.success();
    }

    /**
     * 修改保存代码生成业务
     */
    @PreAuthorize("hasAuthority('tool:gen:edit')")
    @PutMapping
    public CommonResult editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return CommonResult.success();
    }

    /**
     * 删除代码生成
     */
    @PreAuthorize("hasAuthority('tool:gen:remove')")
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        genTableService.deleteGenTableByIds(ids);
        return CommonResult.success();
    }

    /**
     * 预览代码
     */
    @PreAuthorize("hasAuthority('tool:gen:preview')")
    @GetMapping("/preview/{id}")
    public CommonResult preview(@PathVariable("id") Long id) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(id);
        return CommonResult.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @PreAuthorize("hasAuthority('tool:gen:code')")
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @PreAuthorize("hasAuthority('tool:gen:code')")
    @GetMapping("/genCode/{tableName}")
    public CommonResult genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return CommonResult.success();
    }

    /**
     * 同步数据库
     */
    @PreAuthorize("hasAuthority('tool:gen:edit')")
    @GetMapping("/synchDb/{tableName}")
    public CommonResult synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return CommonResult.success();
    }

    /**
     * 批量生成代码
     */
    @PreAuthorize("hasAuthority('tool:gen:code')")
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}