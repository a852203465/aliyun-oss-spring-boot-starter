package com.github.darkjrong.oss.common.pojo.dto;

import com.github.darkjrong.oss.common.enums.CompressedFormatEnum;
import com.github.darkjrong.oss.common.enums.ImageFormatEnum;
import lombok.Data;

import java.io.Serializable;

/**
 *  图片处理参数对象
 * @author Rong.Jia
 * @date 2021/02/24 17:53
 */
@Data
public class ImageDTO implements Serializable {

    private static final long serialVersionUID = 446042266245274514L;

    /**
     * 图片缩放
     */
    private ResizeDTO resize;

    /**
     * 格式转换
     */
    private ImageFormatEnum format;

    /**
     * 图片水印
     */
    private WatermarkDTO watermark;

    /**
     * 压缩文件
     */
    private CompressedFormatEnum compression;

    /**
     * 裁剪
     */
    private CropDTO crop;

    /**
     * 质量变换
     */
    private QualityDTO quality;

    /**
     * 自适应方向
     */
    private Integer autoOrient;

    /**
     * 内切圆
     */
    private Integer circle;

    /**
     * 索引切割
     */
    private IndexCropDTO indexCrop;

    /**
     * 旋转角度
     */
    private Integer rotate;

    /**
     * 渐进显示
     */
    private Integer interlace;

    /**
     * 亮度
     */
    private Integer bright;

    /**
     * 锐化
     */
    private Integer sharpen;

    /**
     * 对比度
     */
    private Integer contrast;

    /**
     * 圆角矩形
     */
    private Integer roundedCorners;

    /**
     * 模糊效果
     */
    private BlurDTO blur;




}
