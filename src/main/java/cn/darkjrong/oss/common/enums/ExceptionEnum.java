package cn.darkjrong.oss.common.enums;

import lombok.Getter;

/**
 *  异常信息枚举类
 * @author Rong.Jia
 * @date 2021/02/21 15:21
 */
@Getter
public enum ExceptionEnum {

    //
    BUCKET_NAME_CANNOT_BE_NULL("BucketName cannot be null"),
    FILE_DOES_NOT_EXIST("File does not exist"),
    BYTES_CANNOT_BE_EMPTY("Bytes cannot be empty"),
    DIRECTORY_CANNOT_BE_EMPTY("Directory cannot be empty"),
    THE_INPUT_STREAM_CANNOT_BE_EMPTY("The input stream cannot be empty"),
    FILE_NAME_CANNOT_BE_EMPTY("File name cannot be empty"),




    ;



    private String value;

    ExceptionEnum(String value){
        this.value = value;
    }





}
