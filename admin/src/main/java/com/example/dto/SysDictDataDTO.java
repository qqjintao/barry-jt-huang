package com.example.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author barry.jt.huang
 */
@Data
public class SysDictDataDTO implements Serializable {
    private static final long serialVersionUID = -1488675258006651610L;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private String cssClass;

    private String listClass;

    private String isDefault;
}
