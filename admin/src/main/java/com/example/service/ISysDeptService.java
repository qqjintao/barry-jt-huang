package com.example.service;

import com.example.model.SysDept;
import com.example.vo.TreeSelect;

import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface ISysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 是否存在部门子节点
     *
     * @param id 部门ID
     * @return 结果
     */
    boolean hasChildByDeptId(Long id);


    /**
     * 根据部门ID查询信息
     *
     * @param id 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Long id);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int insertDept(SysDept dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDept dept);

    /**
     * 删除部门管理信息
     *
     * @param id 部门ID
     * @return 结果
     */
    int deleteDeptById(Long id);
}
