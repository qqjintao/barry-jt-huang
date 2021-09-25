package com.example.dto;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.model.SysUser;
import com.example.untils.bean.BeanUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/12/19 0019
 */
@Data
public class SysUserDTO implements Serializable {

    private static final long serialVersionUID = 1697425516191304949L;

    private Long id;

    private Long parentId;

    private String userName;

    private String password;

    private String nickNameZh;

    private String nickNameEn;

    private String userType;

    private String email;

    private String phone;

    private String sex;

    private String avatar;

    private String status;

    private String theme;

    private Byte isTab;

    private Byte isSet;

    private Byte isHeader;

    private Byte isLogo;

    private String remark;

    private Long[] deptIds;

    private Long[] postIds;

    private Long[] roleIds;
}
