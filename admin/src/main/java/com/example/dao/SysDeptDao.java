package com.example.dao;

import com.example.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface SysDeptDao {

    /**
     * 根据ID查询所有子部门
     *
     * @param id 部门ID
     * @return 部门列表
     */
    List<SysDept> selectChildrenDeptById(Long id);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 根据部门ids 获取 子节点的 id 列表 不包涵 该节点id
     *
     * @param ids 部门IDs
     * @return 部门列表
     */
    List<Long> selectChildrenDeptIdById(@Param("ids") Long[] ids);

    /**
     * 根据用户ID获取部门名称列表
     *
     * @param userId 用户ID
     * @return 选中部门Name列表
     */
    List<String> selectDeptNameListByUserId(@Param("userId") Long userId);
}
