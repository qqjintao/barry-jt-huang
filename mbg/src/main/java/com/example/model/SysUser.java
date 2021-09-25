package com.example.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "上一级负责人ID")
    private Long parentId;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "中文名")
    private String nickNameZh;

    @ApiModelProperty(value = "英文名")
    private String nickNameEn;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "帐号状态（0存1删）")
    private String status;

    @ApiModelProperty(value = "删除标志（0存1删）")
    private String delFlag;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "是否开启tab，0关闭 1开启")
    private Byte isTab;

    @ApiModelProperty(value = "是否系统布局配置，0关闭 1开启")
    private Byte isSet;

    @ApiModelProperty(value = "是否固定头部，0关闭 1开启")
    private Byte isHeader;

    @ApiModelProperty(value = "是否显示logo，0关闭 1开启")
    private Byte isLogo;

    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;

    @ApiModelProperty(value = "创建者")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickNameZh() {
        return nickNameZh;
    }

    public void setNickNameZh(String nickNameZh) {
        this.nickNameZh = nickNameZh;
    }

    public String getNickNameEn() {
        return nickNameEn;
    }

    public void setNickNameEn(String nickNameEn) {
        this.nickNameEn = nickNameEn;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Byte getIsTab() {
        return isTab;
    }

    public void setIsTab(Byte isTab) {
        this.isTab = isTab;
    }

    public Byte getIsSet() {
        return isSet;
    }

    public void setIsSet(Byte isSet) {
        this.isSet = isSet;
    }

    public Byte getIsHeader() {
        return isHeader;
    }

    public void setIsHeader(Byte isHeader) {
        this.isHeader = isHeader;
    }

    public Byte getIsLogo() {
        return isLogo;
    }

    public void setIsLogo(Byte isLogo) {
        this.isLogo = isLogo;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", nickNameZh=").append(nickNameZh);
        sb.append(", nickNameEn=").append(nickNameEn);
        sb.append(", userType=").append(userType);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", avatar=").append(avatar);
        sb.append(", status=").append(status);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", theme=").append(theme);
        sb.append(", isTab=").append(isTab);
        sb.append(", isSet=").append(isSet);
        sb.append(", isHeader=").append(isHeader);
        sb.append(", isLogo=").append(isLogo);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginDate=").append(loginDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}