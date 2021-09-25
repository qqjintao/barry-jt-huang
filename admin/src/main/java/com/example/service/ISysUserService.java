package com.example.service;

import com.example.dto.SysUserDTO;
import com.example.model.SysUser;

import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface ISysUserService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser user, Date beginTime, Date endTime,Long deptId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    SysUser selectUserById(Long id);


    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    String checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 用户信息
     * @return 结果
     */
    String checkPhoneUnique(String phone,Long userId);

    /**
     * 校验email是否唯一
     *
     * @param email 用户信息
     * @return 结果
     */
    String checkEmailUnique(String email,Long userId);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUser user,Long[] deptIds,Long[] postIds,Long[] roleIds);

    /**
     * 修改用户信息
     *
     * @param
     * @return 结果
     */
    int updateUser(SysUserDTO dto,Long[] deptIds,Long[] postIds,Long[] roleIds);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUserStatus(SysUser user);


    /**
     * 修改用户头像
     *
     * @param avatar   头像地址
     * @return 结果
     */
    boolean updateUserAvatar(String avatar);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    int resetPwd(SysUser user);

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    int deleteUserById(Long id);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    int deleteUserByIds(Long[] ids);

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<Long> selectRoleListByUserId(Long userId);

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    List<Long> selectPostListByUserId(Long userId);

    /**
     * 根据用户ID获取部门选择框列表
     *
     * @param userId 用户ID
     * @return 选中部门ID列表
     */
    List<Long> selectDeptListByUserId(Long userId);

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 选中角色Name列表
     */
    List<String> selectRoleNameListByUserId(Long userId);

    /**
     * 根据用户ID获取岗位列表
     *
     * @param userId 用户ID
     * @return 选中岗位Name列表
     */
    List<String> selectPostNameListByUserId(Long userId);

    /**
     * 根据用户ID获取部门列表
     *
     * @param userId 用户ID
     * @return 选中部门Name列表
     */
    List<String> selectDeptNameListByUserId(Long userId);
}
