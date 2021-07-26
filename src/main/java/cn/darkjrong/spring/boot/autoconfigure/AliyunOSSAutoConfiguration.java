package cn.darkjrong.spring.boot.autoconfigure;

import cn.darkjrong.oss.common.annotation.AliyunOSSScan;
import cn.darkjrong.oss.common.registrar.AliyunOSSImportRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 阿里云 OSS 配置类
 *
 * @author Rong.Jia
 * @date 2021/01/28 16:18:22
 */
@Configuration
@Import(AliyunOSSImportRegistrar.class)
@AliyunOSSScan(basePackages = {"cn.darkjrong.oss"}, annotationClass = Component.class)
@ConditionalOnClass({AliyunOSSProperties.class})
@EnableConfigurationProperties({AliyunOSSProperties.class})
public class AliyunOSSAutoConfiguration {

    private final AliyunOSSProperties properties;

    public AliyunOSSAutoConfiguration(AliyunOSSProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AliyunOSSFactoryBean ossClientFactoryBean() {
        final AliyunOSSFactoryBean factoryBean = new AliyunOSSFactoryBean();
        factoryBean.setProperties(this.properties);
        return factoryBean;
    }

}
