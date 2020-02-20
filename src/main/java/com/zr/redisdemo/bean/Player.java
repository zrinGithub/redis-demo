package com.zr.redisdemo.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Player implements Serializable {
    private static final long serialVersionUID = -2285867503722986289L;
    /**
     * 自增ID
     */
    private Integer id;
    /**
     * 中文名称
     */
    private String cnName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 国籍
     */
    private String nation;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 身高
     */
    private BigDecimal height;

    /**
     * 队名
     */
    private String teamName;

    /**
     * 位置
     */
    private String position;

    /**
     * 球衣号码
     */
    private Integer number;
}
