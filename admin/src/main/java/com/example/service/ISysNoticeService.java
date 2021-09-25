package com.example.service;

import com.example.dto.SysNoticeDTO;
import com.example.model.SysNotice;

import java.util.List;

/**
 * @author barry.jt.huang
 */
public interface ISysNoticeService
{
    /**
     * 查询公告信息
     *
     * @param id 公告ID
     * @return 公告信息
     */
    SysNoticeDTO selectNoticeById(Integer id);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int updateNotice(SysNotice notice);

    /**
     * 删除公告信息
     *
     * @param id 公告ID
     * @return 结果
     */
    int deleteNoticeById(Integer id);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    int deleteNoticeByIds(Integer[] noticeIds);
}
