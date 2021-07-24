package com.github.darkjrong.oss.callback;

import com.github.darkjrong.oss.common.builder.StyleBuilder;
import com.github.darkjrong.oss.common.pojo.dto.ImageDTO;

/**
 *  图片样式回调
 * @author Rong.Jia
 * @date 2021/02/24 19:13
 */
@FunctionalInterface
public interface ImageStyleCallBack {

    /**
     *  样式回调
     * @param imageDTO 组合样式参数
     * @see StyleBuilder
     * @return 样式 格式如：image/resize,fixed,w_1000,h_1000,limit_1/watermark,text_5rWL6K-V/blur,r_20,s_10
     */
    String comprehensive(ImageDTO imageDTO);

}
