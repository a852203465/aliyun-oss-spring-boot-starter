package cn.darkjrong.oss.common.registrar;

import cn.darkjrong.oss.common.annotation.AliyunOSSScan;
import cn.darkjrong.spring.boot.autoconfigure.AliyunOSSAutoConfiguration;
import cn.hutool.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 实现{@link ImportBeanDefinitionRegistrar}接口实现扫描，修改Bean的装配模型
 * @author Rong.Jia
 * @date 2021/02/03 08:20
 */
public class AliyunOSSImportRegistrar implements ImportBeanDefinitionRegistrar {

    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSImportRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        try {

            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(AliyunOSSScan.class.getName()));
            if (ObjectUtil.isNotNull(annoAttrs)) {

                AliyunOSSScanner scanner = new AliyunOSSScanner(registry);

                Class<? extends Annotation> annotationClass = annoAttrs.getClass("annotationClass");
                if (!Annotation.class.equals(annotationClass)) {
                    scanner.setAnnotationClass(annotationClass);
                }

                List<String> basePackages = new ArrayList<String>();
                for (String pkg : annoAttrs.getStringArray("basePackages")) {
                    if (StringUtils.hasText(pkg)) {
                        basePackages.add(pkg);
                    }
                }

                scanner.registerFilters();
                scanner.doScan(StringUtils.toStringArray(basePackages));
            }

        }catch (Exception e) {
            logger.error("Could not determine auto-configuration package, automatic scanning disabled. {}", e.getMessage());
        }
    }
}
