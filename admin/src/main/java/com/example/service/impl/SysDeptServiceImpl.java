package com.example.service.impl;

import com.example.constant.SysConstants;
import com.example.dao.SysDeptDao;
import com.example.mapper.SysDeptMapper;
import com.example.model.SysDept;
import com.example.model.SysDeptExample;
import com.example.service.ISysDeptService;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import com.example.vo.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {

    @Autowired
    private SysDeptDao deptDao;

    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        SysDeptExample example = new SysDeptExample();
        SysDeptExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(dept.getParentId())){
            criteria.andParentIdEqualTo(dept.getParentId());
        }
        if(StringUtils.isNotEmpty(dept.getDeptNameZh())){
            criteria.andDeptNameZhLike(StringUtils.getSqlLike(dept.getDeptNameZh()));
        }
        if(StringUtils.isNotEmpty(dept.getDeptNameEn())){
            criteria.andDeptNameEnLike(StringUtils.getSqlLike(dept.getDeptNameEn()));
        }
        if(StringUtils.isNotEmpty(dept.getStatus())){
            criteria.andStatusEqualTo(dept.getStatus());
        }
        example.setOrderByClause("parent_id, order_num asc");
        return deptMapper.selectByExample(example);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<TreeSelect> treeSelectList = getChildren(depts,0L);
        return  treeSelectList;
    }

    public List<TreeSelect> getChildren(List<SysDept> depts,Long parentId){
        List<TreeSelect> treeSelectList = new ArrayList<>();
        for (Iterator<SysDept> iterator = depts.iterator(); iterator.hasNext(); ) {
            SysDept dept =  iterator.next();
            if (dept.getParentId().equals(parentId)) {
                TreeSelect treeSelect = new TreeSelect();
                treeSelect.setId(dept.getId());
                treeSelect.setLabel(dept.getDeptNameZh());
                treeSelect.setChildren(getChildren(depts,dept.getId()));
                treeSelectList.add(treeSelect);
            }
        }
        return treeSelectList;
    }

    /**
     * 是否存在子节点
     *
     * @param id 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long id) {
        SysDeptExample example = new SysDeptExample();
        SysDeptExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<SysDept> list = deptMapper.selectByExample(example);
        return list.size() > 0 ? true : false;
    }

    /**
     * 根据部门ID查询信息
     *
     * @param id 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(Long id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDept dept) {

        if(0L!=dept.getParentId()){
            //如果不是主节点
            SysDept info = deptMapper.selectByPrimaryKey(dept.getParentId());
            if (!SysConstants.STATUS_OFF.equals(info.getStatus())) {
                throw new RuntimeException("部门停用，不允许新增");
            }
            dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        }else {
            //主节点
            dept.setAncestors("0");
        }
        dept.setCreateBy(SecurityUtils.getUserId());
        dept.setCreateTime(new Date());
        return deptMapper.insertSelective(dept);
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(SysDept dept) {
        SysDept newParentDept = deptMapper.selectByPrimaryKey(dept.getParentId());
        SysDept oldDept = deptMapper.selectByPrimaryKey(dept.getId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getId(), newAncestors, oldAncestors);
        }
        dept.setCreateBy(SecurityUtils.getUserId());
        dept.setCreateTime(new Date());
        return deptMapper.updateByPrimaryKeySelective(dept);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<SysDept> children = deptDao.selectChildrenDeptById(deptId);
        for (SysDept child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            deptDao.updateDeptChildren(children);
        }
    }

    /**
     * 删除部门管理信息
     *
     * @param id 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long id) {
        return deptMapper.deleteByPrimaryKey(id);
    }
}
