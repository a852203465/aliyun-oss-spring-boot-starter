package com.github.darkjrong.oss.common.pojo.dto;

import com.github.darkjrong.oss.common.enums.OriginEnum;
import lombok.Data;

import java.io.Serializable;

/**
 *  裁剪 参数
 * @author Rong.Jia
 * @date 2021/02/23 18:16
 */
@Data
public class CropDTO implements Serializable {

    private static final long serialVersionUID = 8027161814152905116L;

    /**
     * 裁剪宽度  [0,图片宽度]
     * 默认为最大值
     */
    private Integer width;

    /**
     * 裁剪高度 [0,图片高度]
     * 默认为最大值
     */
    private Integer height;

    /**
     * 横坐标, [0,图片边界]
     * 默认左上角为原点
     */
    private Integer x;

    /**
     * 纵坐标, [0,图片边界]
     * 左上角为原点
     */
    private Integer y;

    /**
     * 原点位置
     */
    private OriginEnum origin;

















}
