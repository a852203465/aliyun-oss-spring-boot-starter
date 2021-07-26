package cn.darkjrong.oss.common.pojo.dto;

import cn.darkjrong.oss.common.enums.ImageFormatEnum;
import cn.darkjrong.oss.common.enums.RotateEnum;
import cn.darkjrong.oss.common.enums.SnapshotMode;
import lombok.Data;

import java.io.Serializable;

/**
 *  视频截图参数
 * @author Rong.Jia
 * @date 2021/02/23 22:22
 */
@Data
public class VideoSnapshotDTO implements Serializable {

    private static final long serialVersionUID = -1305602276291469932L;

    /**
     * 截图时间 [0,视频时长] 单位：ms
     */
    private Long time;

    /**
     * 截图宽度 如果指定为0，则自动计算  [0,视频宽度] 单位：像素（px）
     */
    private Integer width;

    /**
     * 截图高度，如果指定为0，则自动计算；如果w和h都为0，则输出为原视频宽高。 [0,视频高度]位：像素（px）
     */
    private Integer height;

    /**
     * 截图模式，不指定则为默认模式，根据时间精确截图。如果指定为fast，则截取该时间点之前的最近的一个关键帧。
     */
    private SnapshotMode mode;

    /**
     * 图片的格式 暂只支持 jpg、png
     */
    private ImageFormatEnum format;

    /**
     * 否根据视频信息自动旋转图片。如果指定为auto，则会在截图生成之后根据视频旋转信息进行自动旋转
     */
    private RotateEnum rotate;









}
