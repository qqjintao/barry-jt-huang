package com.example.dto;

import com.example.model.SysRole;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/12/16 0016
 */
@Data
public class SysRoleDTO implements Serializable {
    private static final long serialVersionUID = -6680677999453609691L;

    private Long id;

    private String roleCode;

    private String roleNameZh;

    private String roleNameEn;

    private Integer orderNum;

    private String status;

    private String remark;

    private Long[] menuIds;

    public SysRole changeSysRole(){
        SysRole role = new SysRole();
        role.setId(id);
        role.setRoleCode(roleCode);
        role.setRoleNameZh(roleNameZh);
        role.setRoleNameEn(roleNameEn);
        role.setOrderNum(orderNum);
        role.setStatus(status);
        role.setRemark(remark);
        return role;
    }
}
