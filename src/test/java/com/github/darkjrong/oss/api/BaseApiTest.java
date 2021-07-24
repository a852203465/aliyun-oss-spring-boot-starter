package com.github.darkjrong.oss.api;

import com.github.darkjrong.spring.boot.autoconfigure.AliyunOSSFactoryBean;
import com.github.darkjrong.spring.boot.autoconfigure.AliyunOSSProperties;
import com.aliyun.oss.OSS;

/**
 *  测试类初始化
 * @author Rong.Jia
 * @date 2021/02/20 18:29
 */
public class BaseApiTest {

    protected static AliyunOSSProperties aliyunOSSProperties;
    protected static OSS ossClient;

    static {
        aliyunOSSProperties = new AliyunOSSProperties();
        aliyunOSSProperties.setEndpoint("http://oss-cn-shenzhen.aliyuncs.com");
        aliyunOSSProperties.setOpenIntranet(false);
        aliyunOSSProperties.setAccessKeyId("123131");
        aliyunOSSProperties.setAccessKeySecret("123213121221");
        AliyunOSSFactoryBean aliyunOSSFactoryBean = new AliyunOSSFactoryBean();
        aliyunOSSFactoryBean.setProperties(aliyunOSSProperties);
        aliyunOSSFactoryBean.afterPropertiesSet();
        ossClient = aliyunOSSFactoryBean.getObject();
    }
















}
