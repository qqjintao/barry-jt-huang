package com.example.service.impl;

import com.example.mapper.SysRoleMapper;
import com.example.mapper.SysRoleMenuMapper;
import com.example.mapper.SysUserRoleMapper;
import com.example.model.*;
import com.example.service.ISysRoleService;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/12/7 0007
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;


    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role, Date beginTime, Date endTime) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(role.getRoleNameZh())) {
            criteria.andRoleNameZhLike(StringUtils.getSqlLike(role.getRoleNameZh()));
        }
        if (StringUtils.isNotEmpty(role.getRoleNameEn())) {
            criteria.andRoleNameEnLike(StringUtils.getSqlLike(role.getRoleNameEn()));
        }
        if (StringUtils.isNotEmpty(role.getStatus())) {
            criteria.andStatusEqualTo(role.getStatus());
        }
        if (StringUtils.isNotEmpty(role.getDelFlag())) {
            criteria.andDelFlagEqualTo(role.getDelFlag());
        }
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            criteria.andCreateByEqualTo(SecurityUtils.getUserId());
        }
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            criteria.andCreateTimeBetween(beginTime, endTime);
        }
        return roleMapper.selectByExample(example);
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }


    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public long countUserRoleByRoleId(Long roleId) {
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return userRoleMapper.countByExample(example);
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(SysRole role,Long[] menuIds) {
        // 新增角色信息
        role.setCreateBy(SecurityUtils.getUserId());
        role.setCreateTime(new Date());
        int row = roleMapper.insertSelective(role);
        if(StringUtils.isNotEmpty(menuIds)){
            insertRoleMenu(role.getId(),menuIds);
        }
        return row;
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(SysRole role,Long[] menuIds) {
        role.setUpdateBy(SecurityUtils.getUserId());
        role.setUpdateTime(new Date());
        int row = roleMapper.updateByPrimaryKeySelective(role);
        deleteRoleMenuByRoleId(role.getId());
        if(StringUtils.isNotEmpty(menuIds)){
            insertRoleMenu(role.getId(),menuIds);
        }
        return row;
    }

    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(SysRole role) {
        role.setUpdateBy(SecurityUtils.getUserId());
        role.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(role);
    }


    /**
     * 新增角色菜单信息
     */
    public void insertRoleMenu(Long roldId,Long[] menuIds) {
        for (Long menuId : menuIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roldId);
            roleMenuMapper.insert(sysRoleMenu);
        }
    }


    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleById(Long roleId) {
        deleteRoleMenuByRoleId(roleId);
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(roleIds));
        int row = roleMapper.deleteByExample(example);
        for (Long roleId : roleIds) {
            deleteRoleMenuByRoleId(roleId);
        }
        return row;
    }

    public void deleteRoleMenuByRoleId(Long roleId){
        SysRoleMenuExample example = new SysRoleMenuExample();
        SysRoleMenuExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        roleMenuMapper.deleteByExample(example);
    }
}
