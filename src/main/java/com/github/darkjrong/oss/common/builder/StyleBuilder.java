package com.github.darkjrong.oss.common.builder;

import com.github.darkjrong.oss.common.constants.ImageProcessingConstant;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.darkjrong.oss.common.enums.*;

/**
 * 样式处理工具类
 *
 * @author Rong.Jia
 * @date 2021/02/22 20:05
 */
public class StyleBuilder {

    private StyleBuilder() {
    }

    public static StyleBuilder custom() {
        return new StyleBuilder();
    }

    /**
     * 记录样式信息
     */
    private StringBuffer buffer = new StringBuffer();

    /**
     * 初始化
     * @return 返回当前对象
     */
    public StyleBuilder image(){
        if (!StrUtil.contains(buffer.toString(), "image")) {
            buffer.append("image");
        }

        return this;
    }

    /**
     * 初始化
     * @return 返回当前对象
     */
    public StyleBuilder video(){
        if (!StrUtil.contains(buffer.toString(), "video")) {
            buffer.append("video");
        }
        return this;
    }

    /**
     * 指定缩放模式
     *
     * @param zoomModeEnum 缩放模式
     * @return 返回当前对象
     */
    public StyleBuilder zoomMode(ZoomModeEnum zoomModeEnum) {
        if (ObjectUtil.isNotNull(zoomModeEnum)) {
            buffer.append(zoomModeEnum.getValue()).append(StrUtil.COMMA);
        }

        return this;
    }

    /**
     * 指定处理模式
     *
     * @param  imageProcessingEnum 处理模式
     * @return 返回当前对象
     */
    public StyleBuilder processMode(ImageProcessingEnum imageProcessingEnum) {
        if (ObjectUtil.isNotNull(imageProcessingEnum)) {
            buffer.append(imageProcessingEnum.getValue()).append(StrUtil.COMMA);
        }

        return this;
    }

    /**
     * 指定处理模式
     *
     * @param  videoProcessingEnum 处理模式
     * @return 返回当前对象
     */
    public StyleBuilder processMode(VideoProcessingEnum videoProcessingEnum) {
        if (ObjectUtil.isNotNull(videoProcessingEnum)) {
            buffer.append(videoProcessingEnum.getValue()).append(StrUtil.COMMA);
        }

        return this;
    }

    /**
     * 指定宽度
     *
     * @param  width 宽度
     * @return 返回当前对象
     */
    public StyleBuilder width(Integer width) {
        if (Validator.isNotNull(width)) {
            buffer.append(joint(ImageProcessingConstant.W, width));
        }
        return this;
    }

    /**
     * 指定高度
     *
     * @param  height 高度
     * @return 返回当前对象
     */
    public StyleBuilder height(Integer height) {
        if (Validator.isNotNull(height)) {
            buffer.append(joint(ImageProcessingConstant.H, height));
        }
        return this;
    }

    /**
     * 指定最长边
     *
     * @param  longest 最长边
     * @return 返回当前对象
     */
    public StyleBuilder longest(Integer longest) {
        if (Validator.isNotNull(longest)) {
            buffer.append(joint(ImageProcessingConstant.L, longest));
        }
        return this;
    }

    /**
     * 指定最短边
     *
     * @param  shortest 最短边
     * @return 返回当前对象
     */
    public StyleBuilder shortest(Integer shortest) {
        if (Validator.isNotNull(shortest)) {
            buffer.append(joint(ImageProcessingConstant.S, shortest));
        }
        return this;
    }

    /**
     * 指定目标缩放图大于原图时是否进行缩放
     *
     * @param  limit 目标缩放图大于原图时是否进行缩放
     * @return 返回当前对象
     */
    public StyleBuilder limit(Integer limit) {
        if (Validator.isNull(limit)) {
            limit = 1;
        }
        buffer.append(joint(ImageProcessingConstant.LIMIT, limit));
        return this;
    }

    /**
     * 指定填充的颜色
     *
     * @param  color 填充的颜色
     * @return 返回当前对象
     */
    public StyleBuilder color(String color) {
        if (Validator.isNotNull(color)) {
            buffer.append(joint(ImageProcessingConstant.COLOR, color));
        }
        return this;
    }

    /**
     * 自定义信息
     *
     * @param key	key
     * @param value	value
     * @return 返回当前对象
     */
    public StyleBuilder other(String key, String value) {
        if (ObjectUtil.isNotNull(key) && ObjectUtil.isNotNull(value)) {
            if (StrUtil.isNotBlank(key)) {
                buffer.append(key);
            }
            buffer.append(value);
        }
        return this;
    }

    /**
     * 指定压缩格式
     *
     * @param compressedFormatEnum	压缩格式
     * @return 返回当前对象
     */
    public StyleBuilder compression(CompressedFormatEnum compressedFormatEnum) {
        if (ObjectUtil.isNotNull(compressedFormatEnum)) {
            buffer.append(compressedFormatEnum.getValue()).append(StrUtil.COMMA);
        }

        return this;
    }

    /**
     * 指定透明度
     *
     * @param transparency	透明度
     * @return 返回当前对象
     */
    public StyleBuilder transparency(Integer transparency) {
        buffer.append(joint(ImageProcessingConstant.T, transparency));
        return this;
    }

    /**
     * 指定位置
     *
     * @param location	位置
     * @return 返回当前对象
     */
    public StyleBuilder location(String location) {
        buffer.append(joint(ImageProcessingConstant.G, location));
        return this;
    }

    /**
     * 指定水平边距
     *
     * @param horizontalMargin	水平边距
     * @return 返回当前对象
     */
    public StyleBuilder horizontalMargin(Integer horizontalMargin) {
        buffer.append(joint(ImageProcessingConstant.X, horizontalMargin));
        return this;
    }

    /**
     * 指定垂直边距
     *
     * @param verticalMargin	垂直边距
     * @return 返回当前对象
     */
    public StyleBuilder verticalMargin(Integer verticalMargin) {
        buffer.append(joint(ImageProcessingConstant.Y, verticalMargin));
        return this;
    }

    /**
     * 指定中线垂直偏移
     *
     * @param voffset	中线垂直偏移
     * @return 返回当前对象
     */
    public StyleBuilder voffset(Integer voffset) {
        buffer.append(joint(ImageProcessingConstant.VOFFSET, voffset));
        return this;
    }

    /**
     * 指定图片水印参数
     *
     * @param image	图片水印参数
     * @return 返回当前对象
     */
    public StyleBuilder image(String image) {
        buffer.append(joint(ImageProcessingConstant.IMAGE, image));
        return this;
    }

    /**
     * 指定缩放比例
     *
     * @param proportion	缩放比例
     * @return 返回当前对象
     */
    public StyleBuilder proportion(Integer proportion) {
        buffer.append(joint(ImageProcessingConstant.P, proportion));
        return this;
    }

    /**
     * 指定文字内容
     *
     * @param text	文字内容
     * @return 返回当前对象
     */
    public StyleBuilder text(String text) {
        buffer.append(joint(ImageProcessingConstant.TEXT, text));
        return this;
    }

    /**
     * 指定文字的字体
     *
     * @param type	文字水印的字体
     * @return 返回当前对象
     */
    public StyleBuilder type(TextTypeEnum type) {
        if (ObjectUtil.isNotNull(type)) {
            buffer.append(joint(ImageProcessingConstant.TYPE, type.getValue()));
        }

        return this;
    }

    /**
     * 指定文字大小
     *
     * @param size	文字大小
     * @return 返回当前对象
     */
    public StyleBuilder size(Integer size) {
        buffer.append(joint(ImageProcessingConstant.SIZE, size));
        return this;
    }

    /**
     * 指定阴影透明度
     *
     * @param shadow	阴影透明度
     * @return 返回当前对象
     */
    public StyleBuilder shadow(Integer shadow) {
        buffer.append(joint(ImageProcessingConstant.SHADOW, shadow));
        return this;
    }

    /**
     * 指定顺时针旋转角度
     *
     * @param rotate	阴影透明度
     * @return 返回当前对象
     */
    public StyleBuilder rotate(Integer rotate) {
        buffer.append(joint(ImageProcessingConstant.ROTATE, rotate));
        return this;
    }

    /**
     * 指定是否将文字水印铺满原图
     *
     * @param fill	是否将文字水印铺满原图
     * @return 返回当前对象
     */
    public StyleBuilder fill(Integer fill) {
        buffer.append(joint(ImageProcessingConstant.FILL, fill));
        return this;
    }

    /**
     * 指定文字和图片水印的前后顺序
     *
     * @param order	文字和图片水印的前后顺序
     * @return 返回当前对象
     */
    public StyleBuilder order(Integer order) {
        buffer.append(joint(ImageProcessingConstant.ORDER, order));
        return this;
    }

    /**
     * 指定文字水印和图片水印的对齐方式
     *
     * @param align	文字水印和图片水印的对齐方式
     * @return 返回当前对象
     */
    public StyleBuilder align(Integer align) {
        buffer.append(joint(ImageProcessingConstant.ALIGN, align));
        return this;
    }

    /**
     * 指定文字水印和图片水印间的间距
     *
     * @param interval	文字水印和图片水印间的间距
     * @return 返回当前对象
     */
    public StyleBuilder interval(Integer interval) {
        buffer.append(joint(ImageProcessingConstant.INTERVAL, interval));
        return this;
    }

    /**
     * 指定裁剪起点横坐标
     *
     * @param x	裁剪起点横坐标
     * @return 返回当前对象
     */
    public StyleBuilder x(Integer x) {
        buffer.append(joint(ImageProcessingConstant.X, x));
        return this;
    }

    /**
     * 指定裁剪起点纵坐标
     *
     * @param y	裁剪起点纵坐标
     * @return 返回当前对象
     */
    public StyleBuilder y(Integer y) {
        buffer.append(joint(ImageProcessingConstant.Y, y));
        return this;
    }

    /**
     * 指定裁剪的原点位置
     *
     * @param origin	裁剪的原点位置
     * @return 返回当前对象
     */
    public StyleBuilder origin(OriginEnum origin) {
        if (ObjectUtil.isNotNull(origin)) {
            buffer.append(joint(ImageProcessingConstant.G, origin.name()));
        }
        return this;
    }

    /**
     * 指定相对质量
     *
     * @param relative	相对质量
     * @return 返回当前对象
     */
    public StyleBuilder relative(Integer relative) {
        buffer.append(joint(ImageProcessingConstant.Q, relative));
        return this;
    }

    /**
     * 指定绝对质量
     *
     * @param absolute	绝对质量
     * @return 返回当前对象
     */
    public StyleBuilder absolute(Integer absolute) {
        buffer.append(joint(ImageProcessingConstant.Q.toUpperCase(), absolute));
        return this;
    }

    /**
     * 指定图片格式
     *
     * @param format	图片格式
     * @return 返回当前对象
     */
    public StyleBuilder format(ImageFormatEnum format) {
        if (ObjectUtil.isNotNull(format)) {
            buffer.append(format.name().toLowerCase()).append(StrUtil.COMMA);
        }
        return this;
    }

    /**
     * 指定内切圆的半径
     *
     * @param value	内切圆的半径
     * @return 返回当前对象
     */
    public StyleBuilder circle(Integer value) {
        buffer.append(joint(ImageProcessingConstant.R, value));
        return this;
    }

    /**
     * 指定切割后返回的图片区域
     *
     * @param value	切割后返回的图片区域
     * @return 返回当前对象
     */
    public StyleBuilder i(Integer value) {
        buffer.append(joint(ImageProcessingConstant.I, value));
        return this;
    }

    /**
     * 指定圆角的半径
     *
     * @param radius	圆角的半径
     * @return 返回当前对象
     */
    public StyleBuilder radius(Integer radius) {
        buffer.append(joint(ImageProcessingConstant.R, radius));
        return this;
    }

    /**
     * 指定正态分布的标准差
     *
     * @param deviation	正态分布的标准差
     * @return 返回当前对象
     */
    public StyleBuilder deviation(Integer deviation) {
        buffer.append(joint(ImageProcessingConstant.S, deviation));
        return this;
    }

    /**
     * 指定自适应旋转
     *
     * @param value	自适应旋转
     * @return 返回当前对象
     */
    public StyleBuilder value(Integer value) {
        if (ObjectUtil.isNotNull(value)) {
            buffer.append(value).append(StrUtil.COMMA);
        }
        return this;
    }

    /**
     * 指定截图时间
     *
     * @param time	截图时间
     * @return 返回当前对象
     */
    public StyleBuilder time(Long time) {
        buffer.append(joint(ImageProcessingConstant.T, time));
        return this;
    }

    /**
     * 指定截图模式
     *
     * @param mode	截图模式
     * @return 返回当前对象
     */
    public StyleBuilder mode(SnapshotMode mode) {
        if (ObjectUtil.isNotNull(mode)) {
            buffer.append(joint(ImageProcessingConstant.M, mode.name()));
        }
        return this;
    }

    /**
     * 指定图片的格式
     *
     * @param formatEnum	图片的格式
     * @return 返回当前对象
     */
    public StyleBuilder snapshotFormat(ImageFormatEnum formatEnum) {
        if (ObjectUtil.isNotNull(formatEnum)) {
            buffer.append(joint(ImageProcessingConstant.F, formatEnum.name().toLowerCase()));
        }
        return this;
    }

    /**
     * 指定旋转图片
     *
     * @param rotateEnum	旋转图片
     * @return 返回当前对象
     */
    public StyleBuilder rotate(RotateEnum rotateEnum) {
        if (ObjectUtil.isNotNull(rotateEnum)) {
            buffer.append(joint(ImageProcessingConstant.AR, rotateEnum.name().toLowerCase()));
        }
        return this;
    }







    private static String joint(Object obj1, Object obj2) {
        if (ObjectUtil.isAllNotEmpty(obj1, obj2)) {
            return Convert.toStr(obj1) + StrUtil.UNDERLINE + Convert.toStr(obj2) + StrUtil.COMMA;
        }

        return StrUtil.EMPTY;
    }

    /**
     * 返回样式信息
     *
     * @return 返回样式信息
     */
    public String build() {
        String style = StrUtil.removeSuffix(buffer.toString(), StrUtil.COMMA);
        buffer = null;
        return style;
    }


}
