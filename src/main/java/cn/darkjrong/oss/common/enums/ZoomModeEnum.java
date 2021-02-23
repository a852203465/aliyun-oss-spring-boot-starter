package cn.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 *  缩放模式
 * @author Rong.Jia
 * @date 2021/02/22 19:39
 */
@Getter
public enum  ZoomModeEnum {

    /*
     * lfit（默认值）：等比缩放，缩放图限制为指定w与h的矩形内的最大图片。
     * mfit：等比缩放，缩放图为延伸出指定w与h的矩形框外的最小图片。
     * fill：将原图等比缩放为延伸出指定w与h的矩形框外的最小图片，之后将超出的部分进行居中裁剪。
     * pad：将原图缩放为指定w与h的矩形内的最大图片，之后使用指定颜色居中填充空白部分。
     * fixed：固定宽高，强制缩放。
     */

    LFIT("lfit"),
    MFIT("mfit"),
    FILL("fill"),
    PAD("pad"),
    FIXED("fixed"),




    ;


    private String value;

    ZoomModeEnum(String value){
        this.value = value;
    }



}
