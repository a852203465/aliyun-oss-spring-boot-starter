package com.github.darkjrong.oss.common.utils;

import com.github.darkjrong.oss.common.builder.StyleBuilder;
import com.github.darkjrong.oss.common.constants.ImageProcessingConstant;
import com.github.darkjrong.oss.common.enums.CompressedFormatEnum;
import com.github.darkjrong.oss.common.enums.ImageFormatEnum;
import com.github.darkjrong.oss.common.enums.ImageProcessingEnum;
import com.github.darkjrong.oss.common.enums.ZoomModeEnum;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.darkjrong.oss.common.pojo.dto.*;

import java.lang.reflect.Field;

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
        if (ObjectUtil.isNotNull(resizeDTO)) {
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
                style = style + ImageProcessingConstant.COLOR + StrUtil.UNDERLINE + resizeDTO.getColor();
            }

            return style;
        }
        return StrUtil.EMPTY;
    }

    /**
     * 压缩文件样式
     * @param compressedFormatEnum 格式
     * @return 样式
     */
    public static String compression(CompressedFormatEnum compressedFormatEnum) {
        if (ObjectUtil.isNotNull(compressedFormatEnum)) {
            return StyleBuilder.custom().image().processMode(ImageProcessingEnum.FORMAT)
                    .compression(compressedFormatEnum).build();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 图片水印
     * @param watermarkDTO 参数
     * @return 样式
     */
    public static String watermark(WatermarkDTO watermarkDTO) {
        if (ObjectUtil.isNotNull(watermarkDTO)) {
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
        return StrUtil.EMPTY;
    }

    /**
     * 裁剪
     * @param cropDTO 参数
     * @return 样式
     */
    public static String crop(CropDTO cropDTO) {
        if (ObjectUtil.isNotNull(cropDTO)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.CROP)
                    .width(cropDTO.getWidth())
                    .height(cropDTO.getHeight())
                    .x(cropDTO.getX())
                    .y(cropDTO.getY())
                    .origin(cropDTO.getOrigin())
                    .build();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 质量变换
     * @param qualityDTO 参数
     * @return 样式
     */
    public static String quality(QualityDTO qualityDTO) {
        if (ObjectUtil.isNotNull(qualityDTO)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.QUALITY)
                    .relative(qualityDTO.getRelative())
                    .absolute(qualityDTO.getAbsolute())
                    .build();
        }
       return StrUtil.EMPTY;
    }

    /**
     * 格式转换
     * @param imageFormatEnum 参数
     * @return 样式
     */
    public static String format(ImageFormatEnum imageFormatEnum) {
        if (ObjectUtil.isNotNull(imageFormatEnum)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.FORMAT)
                    .format(imageFormatEnum)
                    .build();
        }
       return StrUtil.EMPTY;
    }

    /**
     * 内切圆
     * @param value 参数
     * @return 样式
     */
    public static String circle(Integer value) {
        if (ObjectUtil.isNotNull(value)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.CIRCLE)
                    .circle(value)
                    .build();
        }
       return StrUtil.EMPTY;
    }

    /**
     * 索引切割
     * @param i 图片区域
     * @param x x轴
     * @param y y轴
     * @return 样式
     */
    public static String indexCrop(Integer x, Integer y, Integer i) {
        if (ObjectUtil.isNotNull(x) || ObjectUtil.isNotNull(y) || ObjectUtil.isNotNull(i)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.INDEX_CROP)
                    .x(x).y(y).i(i)
                    .build();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 圆角矩形
     * @param value 参数
     * @return 样式
     */
    public static String roundedCorners(Integer value) {
        if (ObjectUtil.isNotNull(value)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.ROUNDED_CORNERS)
                    .radius(value)
                    .build();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 模糊效果
     * @param deviation  正态分布的标准差
     * @param radius 模糊半径
     * @return 样式
     */
    public static String blur(Integer radius, Integer deviation) {
        if (ObjectUtil.isNotNull(radius) || ObjectUtil.isNotNull(deviation)) {
            return StyleBuilder.custom().image()
                    .processMode(ImageProcessingEnum.BLUR)
                    .radius(radius)
                    .deviation(deviation)
                    .build();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 自适应方向
     * @param value 参数
     * @return 样式
     */
    public static String autoOrient(Integer value) {
        return value(ImageProcessingEnum.AUTO_ORIENT, value);
    }

    /**
     * 旋转
     * @param value  参数
     * @return 样式
     */
    public static String rotate(Integer value) {
        return value(ImageProcessingEnum.ROTATE, value);
    }

    /**
     * 渐进显示
     * @param value  参数
     * @return 样式
     */
    public static String interlace(Integer value) {
        return value(ImageProcessingEnum.INTERLACE, value);
    }

    /**
     * 亮度
     * @param value  参数
     * @return 样式
     */
    public static String bright(Integer value) {
        return value(ImageProcessingEnum.BRIGHT, value);
    }

    /**
     * 锐化
     * @param value  参数
     * @return 样式
     */
    public static String sharpen(Integer value) {
        return value(ImageProcessingEnum.SHARPEN, value);
    }

    /**
     * 锐化
     * @param value  参数
     * @return 样式
     */
    public static String contrast(Integer value) {
        return value(ImageProcessingEnum.CONTRAST, value);
    }

    /**
     *  设置样式，不做任何拼接
     * @param imageProcessingEnum 处理方式
     * @param value 值
     * @return 样式
     */
    private static String value(ImageProcessingEnum imageProcessingEnum, Integer value) {
        if (ObjectUtil.isNotNull(imageProcessingEnum) && ObjectUtil.isNotNull(value)) {
            return StyleBuilder.custom().image()
                    .processMode(imageProcessingEnum)
                    .value(value)
                    .build();
        }
        return StrUtil.EMPTY;
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

        Field[] fields = ReflectUtil.getFields(imageDTO.getClass());

        String style = StrUtil.EMPTY;

        for (Field field : fields) {
            style = getStyle(imageDTO, style, field);
        }

        if (StrUtil.count(style, "image") > 1) {
            style = "image" + StrUtil.replace(style, "image", StrUtil.EMPTY);
        }

        return style;
    }

    private static String getStyle(ImageDTO imageDTO, String style, Field field) {

        Class<?> fieldType = field.getType();
        Object fieldValue = ReflectUtil.getFieldValue(imageDTO, field);
        if (fieldType.equals(ResizeDTO.class)) {
            ResizeDTO resizeDTO = (ResizeDTO) fieldValue;
            if (ObjectUtil.isNotNull(resizeDTO)) {
                style = StyleBuilder.custom().other(style, StyleUtils.resize(resizeDTO)).build();
            }
        }else if (fieldType.equals(ImageFormatEnum.class)) {
            ImageFormatEnum imageFormatEnum = (ImageFormatEnum) fieldValue;
            if (ObjectUtil.isNotNull(imageFormatEnum)) {
                style = StyleBuilder.custom().other(style, StyleUtils.format(imageFormatEnum)).build();
            }
        }else if (fieldType.equals(WatermarkDTO.class)) {
            WatermarkDTO watermarkDTO = (WatermarkDTO) fieldValue;
            if (ObjectUtil.isNotNull(watermarkDTO)) {
                style = StyleBuilder.custom().other(style, StyleUtils.watermark(watermarkDTO)).build();
            }
        }else if (fieldType.equals(CompressedFormatEnum.class)) {
            CompressedFormatEnum compressedFormatEnum = (CompressedFormatEnum) fieldValue;
            if (ObjectUtil.isNotNull(compressedFormatEnum)) {
                style = StyleBuilder.custom().other(style, StyleUtils.compression(compressedFormatEnum)).build();
            }
        }else if (fieldType.equals(CropDTO.class)) {
            CropDTO cropDTO = (CropDTO) fieldValue;
            if (ObjectUtil.isNotNull(cropDTO)) {
                style = StyleBuilder.custom().other(style, StyleUtils.crop(cropDTO)).build();
            }
        }else if (fieldType.equals(QualityDTO.class)) {
            QualityDTO qualityDTO = (QualityDTO) fieldValue;
            if (ObjectUtil.isNotNull(qualityDTO)) {
                style = StyleBuilder.custom().other(style, StyleUtils.quality(qualityDTO)).build();
            }
        }else if (fieldType.equals(IndexCropDTO.class)) {
            IndexCropDTO indexCropDTO = (IndexCropDTO) fieldValue;
            if (ObjectUtil.isNotNull(indexCropDTO)) {
                style = StyleBuilder.custom()
                        .other(style, StyleUtils.indexCrop(indexCropDTO.getX(),
                                indexCropDTO.getY(), indexCropDTO.getI())).build();
            }
        }else if (fieldType.equals(BlurDTO.class)) {
            BlurDTO blurDTO = (BlurDTO) fieldValue;
            if (ObjectUtil.isNotNull(blurDTO)) {
                style = StyleBuilder.custom()
                        .other(style, StyleUtils.blur(blurDTO.getRadius(), blurDTO.getDeviation())).build();
            }
        }else if (fieldType.equals(Integer.class)) {
            String name = field.getName();
            Integer value = Convert.toInt(fieldValue);
            if ("autoOrient".equals(name)) {
                style = StyleBuilder.custom().other(style, autoOrient(value)).build();
            }else if ("circle".equals(name)) {
                style = StyleBuilder.custom().other(style, circle(value)).build();
            }else if ("rotate".equals(name)) {
                style = StyleBuilder.custom().other(style, rotate(value)).build();
            }else if ("interlace".equals(name)) {
                style = StyleBuilder.custom().other(style, interlace(value)).build();
            }else if ("bright".equals(name)) {
                style = StyleBuilder.custom().other(style, bright(value)).build();
            }else if ("sharpen".equals(name)) {
                style = StyleBuilder.custom().other(style, sharpen(value)).build();
            }else if ("contrast".equals(name)) {
                style = StyleBuilder.custom().other(style, contrast(value)).build();
            }else if ("roundedCorners".equals(name)) {
                style = StyleBuilder.custom().other(style, roundedCorners(value)).build();
            }
        }

        return style;
    }


    public static void main(String[] args) {

        ImageDTO imageDTO = new ImageDTO();

        ResizeDTO resizeDTO = new ResizeDTO();
        resizeDTO.setHeight(1000);
        resizeDTO.setWidth(1000);
        resizeDTO.setZoomModeEnum(ZoomModeEnum.FIXED);
        imageDTO.setResize(resizeDTO);

        WatermarkDTO watermarkDTO = new WatermarkDTO();
        watermarkDTO.setText("测试");
        imageDTO.setWatermark(watermarkDTO);

        BlurDTO blurDTO = new BlurDTO();
        blurDTO.setDeviation(10);
        blurDTO.setRadius(20);
        imageDTO.setBlur(blurDTO);

        String comprehensive = StyleUtils.comprehensive(imageDTO);
        System.out.println(comprehensive);
    }



}
