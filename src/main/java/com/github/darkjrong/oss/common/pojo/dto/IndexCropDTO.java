package com.github.darkjrong.oss.common.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 索引切割参数
 *
 * @author Rong.Jia
 * @date 2021/02/24 18:19
 */
@Data
public class IndexCropDTO implements Serializable {

    private static final long serialVersionUID = 223201421976888643L;

    /**
     * 指定在x轴切割出的每块区域的长度。x参数与y参数只能任选其一  [1,图片宽度]
     */
    private Integer x;

    /**
     * 指定在y轴切割出的每块区域的长度。x参数与y参数只能任选其一 [1,图片高度]
     */
    private Integer y;

    /**
     * 选择切割后返回的图片区域 	[0,区域数) 默认为0，表示第一块
     */
    private Integer i;


}
