package com.github.darkjrong.oss.common.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *  质量变换
 * @author Rong.Jia
 * @date 2021/02/23 18:38
 */
@Data
public class QualityDTO implements Serializable {

    private static final long serialVersionUID = 162063264852944686L;

    /**
     * 相对
     *
     * 图片的相对质量，对原图按百分比进行质量压缩。
     * 例如原图质量为100%，添加quality,q_90参数会得到质量为90％的图片。
     * 原图质量为80%，添加quality,q_90参数会得到质量72%的图片。
     *
     *  只有JPG格式的原图添加该参数，才可以决定图片的相对质量。
     *  如果原图为WebP格式，添加该参数相当于指定了原图绝对质量，即与参数absolute的作用相同
     *
     */
    private Integer relative;

    /**
     * 绝对
     *
     * 图片的绝对质量，将原图质量压缩至Q%，如果原图质量小于指定参数值，则按照原图质量重新进行压缩。
     * 例如原图质量是95%，添加quality,Q_90参数会得到质量90％的图片。
     * 原图质量是80%，添加quality,Q_90只能得到质量80%的图片
     *
     * 该参数只能对保存格式为JPG、WebP的图片使用，对其他格式的图片无效果。
     * 如果同时指定了relative和absolute，会按照absolute的值进行处理
     *
     */
    private Integer absolute;


}
