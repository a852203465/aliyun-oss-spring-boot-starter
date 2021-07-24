package com.github.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 * 原点枚举类
 * @author Rong.Jia
 * @date 2021/02/23 18:21
 */
@Getter
public enum  OriginEnum {

    //左上
    NW("左上"),

    NORTH("中上"),
    NE("右上"),
    WEST("左中"),
    CENTER("中部"),
    EAST("右中"),
    SW("左下"),
    SOUTH("中下"),
    SE("右下"),



    ;

    public String value;

    OriginEnum(String value) {
        this.value = value;
    }




}
