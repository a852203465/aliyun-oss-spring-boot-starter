package cn.darkjrong.oss.common.utils;

import cn.darkjrong.oss.common.builder.StyleBuilder;
import cn.darkjrong.oss.common.enums.CompressedFormatEnum;
import cn.darkjrong.oss.common.enums.ImageFormatEnum;
import cn.darkjrong.oss.common.enums.ImageProcessingEnum;
import cn.darkjrong.oss.common.enums.ZoomModeEnum;
import cn.darkjrong.oss.common.pojo.dto.*;

/**
 *  样式工具类
 * @author Rong.Jia
 * @date 2021/02/24 18:34
 */
public class StyleUtils {

    /**
     * 图片缩放 样式
     * @param resizeDTO 图片缩放参数
     * @return 样式
     */
    public static String resize(ResizeDTO resizeDTO) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.RESIZE)
                .zoomMode(resizeDTO.getZoomModeEnum())
                .width(resizeDTO.getWidth())
                .height(resizeDTO.getHeight())
                .longest(resizeDTO.getLongest())
                .shortest(resizeDTO.getShortest())
                .limit(resizeDTO.getLimit())
                .color(resizeDTO.getColor())
                .build();

        if (ZoomModeEnum.PAD == resizeDTO.getZoomModeEnum()) {
            style = StyleBuilder.custom().other(style, StyleBuilder.custom().color(resizeDTO.getColor()).build()).build();
        }

        return style;
    }

    /**
     * 压缩文件样式
     * @param compressedFormatEnum 格式
     * @return 样式
     */
    public static String compression(CompressedFormatEnum compressedFormatEnum) {
        return StyleBuilder.custom().image().processMode(ImageProcessingEnum.FORMAT)
                .compression(compressedFormatEnum).build();
    }

    /**
     * 图片水印
     * @param watermarkDTO 参数
     * @return 样式
     */
    public static String watermark(WatermarkDTO watermarkDTO) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.WATERMARK)
                .transparency(watermarkDTO.getTransparency())
                .location(watermarkDTO.getLocation())
                .horizontalMargin(watermarkDTO.getHorizontalMargin())
                .verticalMargin(watermarkDTO.getVerticalMargin())
                .voffset(watermarkDTO.getVoffset())
                .image(watermarkDTO.getImage())
                .proportion(watermarkDTO.getProportion())
                .text(watermarkDTO.getText())
                .type(watermarkDTO.getType())
                .size(watermarkDTO.getSize())
                .shadow(watermarkDTO.getShadow())
                .rotate(watermarkDTO.getRotate())
                .fill(watermarkDTO.getFill())
                .order(watermarkDTO.getOrder())
                .align(watermarkDTO.getAlign())
                .interval(watermarkDTO.getInterval())
                .color(watermarkDTO.getColor())
                .build();
    }

    /**
     * 裁剪
     * @param cropDTO 参数
     * @return 样式
     */
    public static String crop(CropDTO cropDTO) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CROP)
                .width(cropDTO.getWidth())
                .height(cropDTO.getHeight())
                .x(cropDTO.getX())
                .y(cropDTO.getY())
                .origin(cropDTO.getOrigin())
                .build();
    }

    /**
     * 质量变换
     * @param qualityDTO 参数
     * @return 样式
     */
    public static String quality(QualityDTO qualityDTO) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.QUALITY)
                .relative(qualityDTO.getRelative())
                .absolute(qualityDTO.getAbsolute())
                .build();
    }

    /**
     * 格式转换
     * @param imageFormatEnum 参数
     * @return 样式
     */
    public static String format(ImageFormatEnum imageFormatEnum) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.FORMAT)
                .format(imageFormatEnum)
                .build();
    }

    /**
     * 自适应方向
     * @param value 参数
     * @return 样式
     */
    public static String autoOrient(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.AUTO_ORIENT)
                .value(value)
                .build();
    }

    /**
     * 内切圆
     * @param value 参数
     * @return 样式
     */
    public static String circle(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CIRCLE)
                .circle(value)
                .build();
    }

    /**
     * 索引切割
     * @param i 图片区域
     * @param x x轴
     * @param y y轴
     * @return 样式
     */
    public static String indexCrop(Integer x, Integer y, Integer i) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INDEX_CROP)
                .x(x).y(y).i(i)
                .build();
    }

    /**
     * 圆角矩形
     * @param value 参数
     * @return 样式
     */
    public static String roundedCorners(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.ROUNDED_CORNERS)
                .radius(value)
                .build();
    }

    /**
     * 模糊效果
     * @param deviation  正态分布的标准差
     * @param radius 模糊半径
     * @return 样式
     */
    public static String blur(Integer radius, Integer deviation) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.BLUR)
                .radius(radius)
                .deviation(deviation)
                .build();
    }

    /**
     * 旋转
     * @param value  参数
     * @return 样式
     */
    public static String rotate(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.ROTATE)
                .value(value)
                .build();
    }

    /**
     * 渐进显示
     * @param value  参数
     * @return 样式
     */
    public static String interlace(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INTERLACE)
                .value(value)
                .build();
    }

    /**
     * 亮度
     * @param value  参数
     * @return 样式
     */
    public static String bright(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.BRIGHT)
                .value(value)
                .build();
    }

    /**
     * 锐化
     * @param value  参数
     * @return 样式
     */
    public static String sharpen(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.SHARPEN)
                .value(value)
                .build();
    }

    /**
     * 锐化
     * @param value  参数
     * @return 样式
     */
    public static String contrast(Integer value) {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CONTRAST)
                .value(value)
                .build();
    }

    /**
     * 图片信息
     * @return 样式
     */
    public static String info() {
        return StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INFO)
                .build();
    }

    /**
     *  自由组合
     * @param imageDTO 组合参数
     * @return 样式
     */
    public static String comprehensive(ImageDTO imageDTO) {

































        return null;

    }


}
