package com.github.darkjrong.oss.api.impl;

import com.github.darkjrong.oss.api.PresignedUrlApi;
import com.github.darkjrong.oss.common.constants.FileConstant;
import com.github.darkjrong.spring.boot.autoconfigure.AliyunOSSProperties;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 签名URL API
 * @author Rong.Jia
 * @date 2021/02/23 22:13
 */
@Component
public class PresignedUrlApiImpl extends BaseApiImpl implements PresignedUrlApi {

    private static final Logger logger = LoggerFactory.getLogger(PresignedUrlApiImpl.class);

    private AliyunOSSProperties aliyunOSSProperties;

    public void setAliyunOSSProperties(AliyunOSSProperties aliyunOSSProperties) {
        this.aliyunOSSProperties = aliyunOSSProperties;
    }

    @Override
    public String getUrl(String bucketName, String objectName) {

        return getUrl(bucketName, objectName, FileConstant.EXPIRATION_TIME, null);
    }

    @Override
    public String getUrl(String bucketName, String objectName, Long expirationTime) {

        return this.getUrl(bucketName, objectName, expirationTime, null);
    }

    @Override
    public String getUrl(String bucketName, String objectName, String style) {

        return getUrl(bucketName, objectName, FileConstant.EXPIRATION_TIME, style);
    }

    @Override
    public String getUrl(String bucketName, String objectName, Long expirationTime, String style) {

        if (Validator.isNull(expirationTime)) {
            expirationTime = FileConstant.EXPIRATION_TIME;
        }

        Date expiration = new Date(DateUtil.current() + expirationTime);

        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);

        if (StrUtil.isNotBlank(style)) {
            req.setProcess(style);
        }

        req.setExpiration(expiration);

        try {
            String signedUrl = getOssClient().generatePresignedUrl(req).toString();
            if (aliyunOSSProperties.getOpenIntranet()) {
                signedUrl = StringUtils.replace(signedUrl, aliyunOSSProperties.getIntranet(), aliyunOSSProperties.getEndpoint());
            }
            return signedUrl;
        }catch (Exception e){
            logger.error("getUrl {}", e.getMessage());
        }

        return null;
    }



}
