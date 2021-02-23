package cn.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 * 视频处理枚举类
 *
 * @author Rong.Jia
 * @date 2021/02/22 19:29
 */
@Getter
public enum VideoProcessingEnum {


    //  视频截帧
    SNAPSHOT("/snapshot"),


    ;

    private String value;

    VideoProcessingEnum(String value) {
        this.value = value;
    }


}
