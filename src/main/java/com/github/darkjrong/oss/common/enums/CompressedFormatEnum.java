package com.github.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 * 压缩格式
 * @author Rong.Jia
 * @date 2021/02/22 22:13
 */
@Getter
public enum  CompressedFormatEnum {

    // HEIF格式
    HEIF("heic"),

    // WebP M6格式
    WEBP_M6("WebP"),


    ;

    private String value;

    CompressedFormatEnum(String value) {
        this.value = value;
    }


}
