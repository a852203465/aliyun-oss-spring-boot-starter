package cn.darkjrong.oss.common.annotation;

import java.lang.annotation.*;


/**
 * 扫描注解
 * @author Rong.Jia
 * @date 2021/02/02 19:18
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface AliyunOSSScan {

    /**
     *  扫描包集合
     * @return 包集合
     */
    String[] basePackages() default {};

    Class<? extends Annotation> annotationClass() default Annotation.class;


}
