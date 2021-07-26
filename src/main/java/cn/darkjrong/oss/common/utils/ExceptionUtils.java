package cn.darkjrong.oss.common.utils;

import com.aliyun.oss.OSSException;

/**
 * 异常工具类
 *
 * @author Rong.Jia
 * @date 2021/07/24 10:59:03
 */
public class ExceptionUtils {

    public static String exception(Exception e) {

        if (e instanceof OSSException) {
            OSSException exception = (OSSException)e;
            return String.format("Error Code : %s Error Message : %s", exception.getErrorCode(), exception.getErrorMessage());
        } else {
            return e.getMessage();
        }
    }









}
