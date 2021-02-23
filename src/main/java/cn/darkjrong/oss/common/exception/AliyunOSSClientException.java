package cn.darkjrong.oss.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *   项目自定义异常
 * @author Rong.Jia
 * @date 2019/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AliyunOSSClientException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1501020198729282518L;

    /**
     *  异常code 码
     */
    private Integer code;

    /**
     * 异常详细信息
     */
    private String message;

    public AliyunOSSClientException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public AliyunOSSClientException(String message){
        this.code = 4999;
        this.message = message;
    }

    public AliyunOSSClientException(Integer code, String message, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
    }



}
