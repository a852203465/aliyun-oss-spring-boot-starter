package cn.darkjrong.oss.common.pojo.dto;

import cn.darkjrong.oss.common.enums.CompressedFormatEnum;
import cn.darkjrong.oss.common.enums.ImageFormatEnum;
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
     * 压缩文件
     */
    private CompressedFormatEnum compression;

    /**
     * 图片水印
     */
    private WatermarkDTO watermark;

    /**
     * 裁剪
     */
    private CropDTO crop;

    /**
     * 质量变换
     */
    private QualityDTO quality;

    /**
     * 格式转换
     */
    private ImageFormatEnum format;

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
     * 圆角矩形
     * 模糊效果-模糊半径 [1,50] 该值越大，图片越模糊
     */
    private Integer radius;

    /**
     * 模糊效果-正态分布的标准差 [1,50] 该值越大，图片越模糊
     */
    private Integer deviation;

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








}
