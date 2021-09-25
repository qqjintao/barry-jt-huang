package com.example.service.impl;

import com.example.mapper.SysLoginInfoMapper;
import com.example.model.SysLoginInfo;
import com.example.model.SysLoginInfoExample;
import com.example.untils.StringUtils;
import com.example.dao.LoginInfoDao;
import com.example.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/14 0014
 */
@Service
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {
    @Autowired
    private LoginInfoDao loginInfoDao;
    @Autowired
    private SysLoginInfoMapper loginInfoMapper;
    /**
     * 新增系统登录日志
     *
     * @param loginInfo 访问日志对象
     */
    @Override
    public void insertLoginInfo(SysLoginInfo loginInfo)
    {
        loginInfoMapper.insertSelective(loginInfo);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo, Date beginTime, Date endTime)
    {
        SysLoginInfoExample example = new SysLoginInfoExample();
        SysLoginInfoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(loginInfo.getIpaddr())) {
            criteria.andIpaddrLike(StringUtils.getSqlLike(loginInfo.getIpaddr()));
        }
        if (StringUtils.isNotEmpty(loginInfo.getStatus())) {
            criteria.andStatusEqualTo(loginInfo.getStatus());
        }
        if (StringUtils.isNotEmpty(loginInfo.getUserName())) {
            criteria.andUserNameLike(StringUtils.getSqlLike(loginInfo.getUserName()));
        }
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            criteria.andLoginTimeBetween(beginTime, endTime);
        }
        return loginInfoMapper.selectByExample(example);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return
     */
    @Override
    public int deleteLoginInfoByIds(Long[] infoIds)
    {
        SysLoginInfoExample example = new SysLoginInfoExample();
        SysLoginInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(infoIds));
        return loginInfoMapper.deleteByExample(example);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginInfo()
    {
        loginInfoDao.cleanLoginInfo();
    }
}
