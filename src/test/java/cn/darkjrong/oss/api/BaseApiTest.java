package cn.darkjrong.oss.api;

import cn.darkjrong.oss.api.impl.BaseApiImpl;
import cn.darkjrong.spring.boot.autoconfigure.AliyunOSSFactoryBean;
import cn.darkjrong.spring.boot.autoconfigure.AliyunOSSProperties;
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

        AliyunOSSFactoryBean aliyunOSSFactoryBean = new AliyunOSSFactoryBean();
        aliyunOSSFactoryBean.setProperties(aliyunOSSProperties);
        aliyunOSSFactoryBean.afterPropertiesSet();
        ossClient = aliyunOSSFactoryBean.getObject();
    }
















}
