package com.example.dao;

import com.example.dto.SysNoticeDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author barry.jt.huang
 */
public interface SysNoticeDao {
    SysNoticeDTO selectByPrimaryKey(@Param("id")Integer id);
}
