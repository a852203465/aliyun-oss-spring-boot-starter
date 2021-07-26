package cn.darkjrong.oss.common.enums;

/**
 *  图片格式枚举
 * @author Rong.Jia
 * @date 2021/02/23 18:48
 */
public enum  ImageFormatEnum {

    // 将原图保存成JPG格式，如果原图是PNG、WebP、BMP等存在透明通道的格式，默认会把透明填充成白色
    JPG,

    PNG,
    WEBP,
    BMP,

    // 原图为GIF图片则继续保存为GIF格式；原图不是GIF图片，则按原图格式保存。
    GIF,

    TIFF




    ;





}
