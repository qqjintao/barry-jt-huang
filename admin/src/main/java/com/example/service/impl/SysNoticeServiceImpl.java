package com.example.service.impl;

import com.example.dao.SysNoticeDao;
import com.example.dto.SysNoticeDTO;
import com.example.mapper.SysNoticeMapper;
import com.example.model.SysNotice;
import com.example.model.SysNoticeExample;
import com.example.service.ISysNoticeService;
import com.example.untils.StringUtils;
import com.example.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author barry.jt.huang
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {

    @Autowired
    private SysNoticeDao sysNoticeDao;
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     *
     * @param id 公告ID
     * @return 公告信息
     */
    @Override
    public SysNoticeDTO selectNoticeById(Integer id) {
        return sysNoticeDao.selectByPrimaryKey(id);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        SysNoticeExample example = new SysNoticeExample();
        SysNoticeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(notice.getNoticeTitle())) {
            criteria.andNoticeTitleLike(StringUtils.getSqlLike(notice.getNoticeTitle()));
        }
        if (StringUtils.isNotEmpty(notice.getNoticeType())) {
            criteria.andNoticeTypeEqualTo(notice.getNoticeType());
        }
        if (StringUtils.isNotEmpty(notice.getStatus())) {
            criteria.andStatusEqualTo(notice.getStatus());
        }
        return noticeMapper.selectByExample(example);
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice) {
        notice.setCreateBy(SecurityUtils.getUserId());
        notice.setCreateTime(new Date());
        return noticeMapper.insertSelective(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice) {
        notice.setUpdateBy(SecurityUtils.getUserId());
        notice.setUpdateTime(new Date());
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    /**
     * 删除公告对象
     *
     * @param id 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Integer id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Integer[] noticeIds) {
        SysNoticeExample example = new SysNoticeExample();
        SysNoticeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(noticeIds));
        return noticeMapper.deleteByExample(example);
    }
}