package cn.darkjrong.spring.boot.autoconfigure;

import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 阿里云oss 工厂类
 * @author rong.jia
 * @date 2019/10/22 09:07:22
 */
public class AliyunOSSFactoryBean implements FactoryBean<OSS>, InitializingBean, DisposableBean {

    private OSS ossClient;
    private AliyunOSSProperties properties;

    public void setProperties(AliyunOSSProperties properties) {
        this.properties = properties;
    }

    @Override
    public OSS getObject() {
        return this.ossClient;
    }

    @Override
    public Class<?> getObjectType() {
        return OSS.class;
    }

    @Override
    public boolean isSingleton() {
        return Boolean.TRUE;
    }

    @Override
    public void destroy() {
        if (ObjectUtil.isNotNull(ossClient)) {
            this.ossClient.shutdown();
        }
    }

    @Override
    public void afterPropertiesSet() {

        Assert.notNull(properties.getEndpoint(), "'endpoint' must be not null");
        Assert.notNull(properties.getAccessKeyId(), "'accessKeyId' must be not null");
        Assert.notNull(properties.getAccessKeySecret(), "'accessKeySecret' must be not null");

        Boolean openIntranet = properties.getOpenIntranet();
        if (openIntranet) {
            Assert.notNull(properties.getIntranet(), "'intranet' must be not null");
            this.ossClient = new OSSClientBuilder().build(properties.getIntranet(),properties.getAccessKeyId(), properties.getAccessKeySecret());
        }else {
            this.ossClient = new OSSClientBuilder().build(properties.getEndpoint(),properties.getAccessKeyId(), properties.getAccessKeySecret());

        }
    }

}
