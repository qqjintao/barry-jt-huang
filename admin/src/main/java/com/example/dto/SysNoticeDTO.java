package com.example.dto;

import com.example.model.SysNotice;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author barry.jt.huang
 */
@Data
public class SysNoticeDTO implements Serializable {
    
    private static final long serialVersionUID = 4267189473400448193L;

    //"公告ID
    private Integer id;

    //"公告标题
    private String noticeTitle;

    //"公告类型（1通知 2公告）
    private String noticeType;

    //"帐号状态（0正常 1停用）
    private String status;

    //"删除标志（0代表存在1代表删除）
    private String delFlag;

    //"创建者
    private Long createBy;

    //"创建时间
    private Date createTime;

    //"更新者
    private Long updateBy;

    //"更新时间
    private Date updateTime;

    //"备注
    private String remark;

    //"公告内容"
    private String noticeContent;

    public SysNotice changeSysNotice(){
        SysNotice notice = new SysNotice();
        notice.setNoticeContent(noticeContent.getBytes());
        notice.setUpdateTime(updateTime);
        notice.setUpdateBy(updateBy);
        notice.setRemark(remark);
        notice.setCreateTime(createTime);
        notice.setCreateBy(createBy);
        notice.setId(id);
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeType(noticeType);
        notice.setStatus(status);
        notice.setDelFlag(delFlag);
        return notice;
    }
}
