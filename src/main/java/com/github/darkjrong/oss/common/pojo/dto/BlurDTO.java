package com.github.darkjrong.oss.common.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 模糊效果参数
 * @author Rong.Jia
 * @date 2021/02/24 23:22
 */
@Data
public class BlurDTO implements Serializable {

    private static final long serialVersionUID = -8748125932571517876L;

    /**
     *
     * 模糊效果-模糊半径 [1,50] 该值越大，图片越模糊
     */
    private Integer radius;

    /**
     * 模糊效果-正态分布的标准差 [1,50] 该值越大，图片越模糊
     */
    private Integer deviation;


}
