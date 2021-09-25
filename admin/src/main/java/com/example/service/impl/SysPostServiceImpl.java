package com.example.service.impl;

import com.example.dao.SysDeptDao;
import com.example.dao.SysPostDao;
import com.example.mapper.SysDeptMapper;
import com.example.mapper.SysPostMapper;
import com.example.model.SysDept;
import com.example.model.SysDeptExample;
import com.example.model.SysPost;
import com.example.model.SysPostExample;
import com.example.service.ISysPostService;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/29 0029
 */
@Service
public class SysPostServiceImpl implements ISysPostService {

    @Autowired
    private SysDeptDao deptDao;

    @Autowired
    private SysPostDao postDao;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysDeptMapper deptMapper;


    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return postDao.selectPostList(post);
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll() {
        SysPostExample example = new SysPostExample();
        return postMapper.selectByExample(example);
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param id 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPost selectPostById(Long id) {
        return postMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据deptIds获取对应岗位
     * @param deptIds
     * @return
     */
    @Override
    public List<SysPost> selectPostByDeptIds(Long[] deptIds){
        if(deptIds.length>0){
            List<Long>  ids = deptDao.selectChildrenDeptIdById(deptIds);
            ids.addAll(Arrays.asList(deptIds));
            SysPostExample example = new SysPostExample();
            SysPostExample.Criteria criteria = example.createCriteria();
            criteria.andDeptIdIn(ids);
            return postMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的岗位ID
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public int deletePostByIds(Long[] ids) {
        SysPostExample example = new SysPostExample();
        SysPostExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return postMapper.deleteByExample(example);
    }

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPost post) {
        SysDept sysDept=deptMapper.selectByPrimaryKey(post.getDeptId());
        post.setDeptNameZh(sysDept.getDeptNameZh());
        post.setDeptNameEn(sysDept.getDeptNameEn());
        post.setCreateBy(SecurityUtils.getUserId());
        post.setCreateTime(new Date());
        return postMapper.insertSelective(post);
    }

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPost post) {
        SysDept sysDept=deptMapper.selectByPrimaryKey(post.getDeptId());
        post.setDeptNameZh(sysDept.getDeptNameZh());
        post.setDeptNameEn(sysDept.getDeptNameEn());
        post.setUpdateBy(SecurityUtils.getUserId());
        post.setUpdateTime(new Date());
        return postMapper.updateByPrimaryKeySelective(post);
    }
}

