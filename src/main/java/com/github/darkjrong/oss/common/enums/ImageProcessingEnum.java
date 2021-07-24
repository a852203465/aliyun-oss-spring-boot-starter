package com.github.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 * 图片处理枚举类
 *
 * @author Rong.Jia
 * @date 2021/02/22 19:29
 */
@Getter
public enum ImageProcessingEnum {

    // 图片高级压缩, 格式转换
    FORMAT("/format"),

    // 图片缩放
    RESIZE("/resize"),

    // 内切圆
    CIRCLE("/circle"),

    // 自定义裁剪
    CROP("/crop"),

    //  索引切割
    INDEX_CROP("/indexcrop"),

    //  圆角矩形
    ROUNDED_CORNERS("/rounded-corners"),

    //  自适应方向
    AUTO_ORIENT("/auto-orient"),

    //  旋转
    ROTATE("/rotate"),

    //  模糊效果
    BLUR("/blur"),

    //  亮度
    BRIGHT("/bright"),

    //  锐化
    SHARPEN("/sharpen"),

    //  对比度
    CONTRAST("/contrast"),

    // 渐进显示
    INTERLACE("/interlace"),

    // 质量变换
    QUALITY("/quality"),

    // 图片水印
    WATERMARK("/watermark"),

    // 获取图片主色调
    AVERAGE_HUE("/average-hue"),

    // 获取信息
    INFO("/info"),




    ;

    private String value;

    ImageProcessingEnum(String value) {
        this.value = value;
    }


}
