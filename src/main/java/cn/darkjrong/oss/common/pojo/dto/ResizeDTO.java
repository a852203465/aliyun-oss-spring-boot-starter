package cn.darkjrong.oss.common.pojo.dto;

import cn.darkjrong.oss.common.enums.ZoomModeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  缩放 参数
 * @author Rong.Jia
 * @date 2021/02/22 21:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResizeDTO implements Serializable {

    private static final long serialVersionUID = -8028387269754925342L;

    /**
     * 缩放的模式
     */
    private ZoomModeEnum zoomModeEnum;

    /**
     * 目标缩放图的宽度  [1,4096]
     */
    private Integer width;

    /**
     * 目标缩放图的高度 [1,4096]
     */
    private Integer height;

    /**
     * 目标缩放图的最长边  [1,4096]
     */
    private Integer longest;

    /**
     * 目标缩放图的最短边 [1,4096]
     */
    private Integer shortest;

    /**
     * 目标缩放图大于原图时是否进行缩放
     * 1（默认值）：表示不按指定参数进行缩放，直接返回原图。
     * 0：按指定参数进行缩放。
     */
    private Integer limit;

    /**
     * 当缩放模式选择为pad（缩放填充）时，可以设置填充的颜色
     * RGB颜色值
     */
    private String color;



}
