package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 海康 云眸 配置类
 *
 * @author Rong.Jia
 * @date 2021/01/28 16:18:22
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun-oss")
public class AliyunOSSProperties {

    /**
     * 外网域名
     */
    private String endpoint;

    /**
     * ak
     */
    private String accessKeyId;

    /**
     * aks
     */
    private String accessKeySecret;

    /**
     * 内网地址
     */
    private String intranet;

    /**
     * 是否使用内网模式上传
     * 开启：true, 关闭：false
     */
    private Boolean openIntranet = false;




}
