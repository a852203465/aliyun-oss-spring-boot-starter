package com.github.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 * 文字类型
 * @author Rong.Jia
 * @date 2021/02/22 22:50
 */
@Getter
public enum  TextTypeEnum {

    // 	文泉驿正黑
    WQY_ZENHEI("wqy-zenhei", "文泉驿正黑", "d3F5LXplbmhlaQ"),

    WQY_MICROHEI("wqy-microhei", "文泉微米黑", "d3F5LW1pY3JvaGVp"),
    FANG_ZHENG_SHU_SONG("fangzhengshusong", "方正书宋", "ZmFuZ3poZW5nc2h1c29uZw"),
    FANG_ZHENG_KAI_TI("fangzhengkaiti", "方正楷体", "ZmFuZ3poZW5na2FpdGk"),
    FANG_ZHENG_HEI_TI("fangzhengkaiti", "方正黑体", "ZmFuZ3poZW5naGVpdGk"),
    FANG_ZHENG_FANG_SONG("fangzhengfangsong", "方正仿宋", "ZmFuZ3poZW5nZmFuZ3Nvbmc"),
    DROIDSANSFALLBACK("droidsansfallback", "DroidSansFallback", "ZHJvaWRzYW5zZmFsbGJhY2s"),


    ;

    /**
     * 参数值
     */
    private String parameter;

    /**
     * 中文含义
     */
    private String meaning;

    /**
     * 编码值
     */
    private String value;

    TextTypeEnum(String parameter, String meaning, String value) {
        this.parameter = parameter;
        this.meaning = meaning;
        this.value = value;
    }
}
