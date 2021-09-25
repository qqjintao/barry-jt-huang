package com.example.service.impl;

import com.example.constant.Constants;
import com.example.dao.SysDeptDao;
import com.example.dao.SysPostDao;
import com.example.dao.SysRoleDao;
import com.example.dao.SysUserDao;
import com.example.dto.SysUserDTO;
import com.example.mapper.SysUserDeptMapper;
import com.example.mapper.SysUserMapper;
import com.example.mapper.SysUserPostMapper;
import com.example.mapper.SysUserRoleMapper;
import com.example.model.*;
import com.example.service.ISysUserService;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author barry.jt.huang
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysDeptDao deptDao;

    @Autowired
    private SysPostDao postDao;

    @Autowired
    private SysRoleDao roleDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysUserDeptMapper userDeptMapper;

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUser> selectUserList(SysUser user, Date beginTime, Date endTime,Long deptId) {
        return  userDao.selectUserList(user,deptId,beginTime,endTime);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<SysUser> list = userMapper.selectByExample(example);
        if(StringUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        long count = userMapper.countByExample(example);
        if (count > 0)
        {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param phone 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(String phone,Long userId) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        if(StringUtils.isNotEmpty(userId)){
            criteria.andIdNotEqualTo(userId);
        }
        long count = userMapper.countByExample(example);
        if (count > 0)
        {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param email 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(String email,Long userId) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        if(StringUtils.isNotEmpty(userId)){
            criteria.andIdNotEqualTo(userId);
        }
        long count = userMapper.countByExample(example);
        if (count > 0)
        {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }


    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user,Long[] deptIds,Long[] postIds,Long[] roleIds) {
        user.setCreateTime(new Date());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        int row = userMapper.insertSelective(user);
        //建立关系
        buildRelationships(user.getId(),deptIds,postIds,roleIds);
        return row;
    }
    /**
     * 修改保存用户信息
     *
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUserDTO dto,Long[] deptIds,Long[] postIds,Long[] roleIds) {
        SysUser user = new SysUser();
        user.setId(dto.getId());
        user.setNickNameZh(dto.getNickNameZh());
        user.setNickNameEn(dto.getNickNameEn());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setSex(dto.getSex());
        user.setStatus(dto.getStatus());
        user.setRemark(dto.getRemark());
        user.setUpdateBy(SecurityUtils.getUserId());
        user.setUpdateTime(new Date());
        int row = userMapper.updateByPrimaryKeySelective(user);
        //删除关系
        deleteRelationships(user.getId());
        //建立关系
        buildRelationships(user.getId(),deptIds,postIds,roleIds);
        return row;
    }

    //删除关系
    public void deleteRelationships(Long userId){
        SysUserDeptExample example1 = new SysUserDeptExample();
        SysUserDeptExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        userDeptMapper.deleteByExample(example1);

        SysUserPostExample example2= new SysUserPostExample();
        SysUserPostExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andUserIdEqualTo(userId);
        userPostMapper.deleteByExample(example2);

        SysUserRoleExample example3 = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria3 = example3.createCriteria();
        criteria3.andUserIdEqualTo(userId);
        userRoleMapper.deleteByExample(example3);
    }

    //user表建立对应关系
    public void buildRelationships(Long userId,Long[] deptIds,Long[] postIds,Long[] roleIds){
        if(StringUtils.isNotEmpty(deptIds)){
            for(Long deptId : deptIds){
                SysUserDept userDept = new SysUserDept();
                userDept.setUserId(userId);
                userDept.setDeptId(deptId);
                userDeptMapper.insert(userDept);
            }
        }
        if(StringUtils.isNotEmpty(postIds)){
            for(Long postId : postIds){
                SysUserPost userPost = new SysUserPost();
                userPost.setUserId(userId);
                userPost.setPostId(postId);
                userPostMapper.insert(userPost);
            }
        }
        if(StringUtils.isNotEmpty(roleIds)){
            for(Long roleId : roleIds){
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            }
        }
    }
    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user) {
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtils.getUserId());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 修改用户头像
     *
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String avatar) {
        SysUser user = new SysUser();
        user.setId(SecurityUtils.getUserId());
        if(StringUtils.isNotEmpty(avatar)){
            user.setAvatar(avatar);
        }
        user.setUpdateBy(SecurityUtils.getUserId());
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user)>0;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user) {
        if(StringUtils.isNotEmpty(user.getPassword())){
            user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        }
        user.setUpdateTime(new Date());
        user.setUpdateBy(SecurityUtils.getUserId());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id) {
        deleteRelationships(id);
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] ids) {
        for(Long id:ids){
            deleteRelationships(id);
        }
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return userMapper.deleteByExample(example);
    }

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        SysUserRoleExample example =  new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SysUserRole> userRoleList = userRoleMapper.selectByExample(example);
        List<Long> ids = userRoleList.stream().map(list->list.getRoleId()).collect(Collectors.toList());
        return ids;
    }
    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId){
        SysUserPostExample example =  new SysUserPostExample();
        SysUserPostExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SysUserPost> userPostList = userPostMapper.selectByExample(example);
        List<Long> ids = userPostList.stream().map(list->list.getPostId()).collect(Collectors.toList());
        return ids;
    }

    /**
     * 根据用户ID获取部门选择框列表
     *
     * @param userId 用户ID
     * @return 选中部门ID列表
     */
    @Override
    public List<Long> selectDeptListByUserId(Long userId){
        SysUserDeptExample example =  new SysUserDeptExample();
        SysUserDeptExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SysUserDept> userRoleList = userDeptMapper.selectByExample(example);
        List<Long> ids = userRoleList.stream().map(list->list.getDeptId()).collect(Collectors.toList());
        return ids;
    }


    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 选中角色Name列表
     */
    @Override
    public List<String> selectRoleNameListByUserId(Long userId){
        return  roleDao.selectRoleNameListByUserId(userId);
    }

    /**
     * 根据用户ID获取岗位列表
     *
     * @param userId 用户ID
     * @return 选中岗位Name列表
     */
    @Override
    public List<String> selectPostNameListByUserId(Long userId){
        return  postDao.selectPostNameListByUserId(userId);
    }

    /**
     * 根据用户ID获取部门列表
     *
     * @param userId 用户ID
     * @return 选中部门Name列表
     */
    @Override
    public List<String> selectDeptNameListByUserId(Long userId){
        return  deptDao.selectDeptNameListByUserId(userId);
    }
}
