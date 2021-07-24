package com.github.darkjrong.oss.common.pojo.dto;

import com.github.darkjrong.oss.common.enums.TextTypeEnum;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  水印参数
 * @author Rong.Jia
 * @date 2021/02/22 22:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatermarkDTO implements Serializable {

    private static final long serialVersionUID = 519470544652091837L;

    /*-----------------基础参数---------------------*/

    /**
     * 透明度
     *
     * [0,100]
     * 默认值：100， 表示透明度100%（不透明）
     *
     */
    private Integer transparency;

    /**
     * 水印在图片中的位置
     *
     * nw：左上
     * north：中上
     * ne：右上
     * west：左中
     * center：中部
     * east：右中
     * sw：左下
     * south：中下
     * se：右下
     */
    private String location;

    /**
     * 水平边距
     *
     * 即距离图片边缘的水平距离。这个参数只有当水印位置是左上、左中、左下、右上、右中、右下才有意义。
     *
     * [0,4096]
     * 默认值：10
     *
     * 单位：像素（px）
     *
     */
    private Integer horizontalMargin;

    /**
     * 垂直边距
     *
     * 即距离图片边缘的垂直距离， 这个参数只有当水印位置是左上、中上、右上、左下、中下、右下才有意义
     *
     * [0,4096]
     * 默认值：10
     *
     * 单位：px
     *
     */
    private Integer verticalMargin;

    /**
     * 中线垂直偏移
     *
     * 当水印位置在左中、中部、右中时，可以指定水印位置根据中线往上或者往下偏移
     *
     *[-1000,1000]
     * 默认值：0
     *
     * 单位：px
     *
     */
    private Integer voffset;


    /*-----------------图片水印参数---------------------*/

    /**
     * 图片水印参数, 水印图片的完整Object名称
     *
     * 水印图片只能使用当前存储空间内的Object
     *
     */
    private String image;

    /*-----------------水印图片预处理参数---------------------*/

    /**
     * 缩放比例
     *
     * 指定水印图片按照主图的比例进行缩放，取值为缩放的百分比。如设置参数值为10，如果主图为100×100， 水印图片大小就为10×10。当主图变成了200×200，水印图片大小就为20×20。
     *
     * [1,100]
     *
     */
    private Integer proportion;

    /*-----------------文字水印参数---------------------*/

    /**
     * 文字内容
     *
     * 最大长度为64个字符（最多21个汉字）
     */
    private String text;

    /**
     * 文字水印的字体
     */
    private TextTypeEnum type;

    /**
     * 文字颜色, RGB颜色值
     */
    private String color;

    /**
     * 文字大小
     *
     * (0,1000]
     * 默认值：40
     *
     * 单位：px
     *
     */
    private Integer size;

    /**
     * 阴影透明度
     *
     * [0,100]
     * 默认值：0，表示没有阴影。
     *
     */
    private Integer shadow;

    /**
     * 顺时针旋转角度
     *
     * [0,360]
     * 默认值：0，表示不旋转
     *
     */
    private Integer rotate;


    /**
     * 文字水印铺满原图
     *
     * 1：表示将文字水印铺满原图。
     * 0（默认值）：表示不将文字水印铺满全图。
     *
     */
    private Integer fill;


    /*-----------------文字水印参数---------------------*/

    /**
     * 文字和图片水印的前后顺序
     *
     * 0（默认值）：表示图片水印在前。
     * 1：表示文字水印在前
     *
     */
    private Integer order;

    /**
     * 文字水印和图片水印的对齐方式
     *
     * 0：表示文字水印和图片水印上对齐。
     * 1：表示文字水印和图片水印中对齐。
     * 2（默认值）：表示文字水印和图片水印下对齐
     *
     */
    private Integer align;

    /**
     * 文字水印和图片水印间的间距
     *
     * [0,1000]
     * 默认值：0
     *
     * 单位：px
     *
     */
    private Integer interval;

    public String getImage() {
        return StrUtil.isNotBlank(image) ? Base64.encodeUrlSafe(image) : image;
    }

    public String getText() {
        return StrUtil.isNotBlank(text) ? Base64.encodeUrlSafe(text) : text;
    }
}
