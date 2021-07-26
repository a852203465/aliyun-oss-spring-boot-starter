package cn.darkjrong.oss.common.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  授权策略DTO
 * @author Rong.Jia
 * @date 2021/02/20 23:30
 */
@NoArgsConstructor
@Data
public class BucketPolicyVO implements Serializable {

    private static final long serialVersionUID = -3244681447615890391L;

    /**
     * 版本
     */
    @JSONField(name = "version")
    private String version;

    /**
     * 授权语句
     */
    @JSONField(name = "Statement")
    private List<StatementBean> statement;

    @NoArgsConstructor
    @Data
    public static class StatementBean {

        /**
         * 授权效力包括两种：允许（Allow）和拒绝（Deny）
         */
        @JSONField(name = "Effect")
        private String effect;

        /**
         * 操作是指对具体资源的操作。
         */
        @JSONField(name = "action")
        private List<String> Action;

        /**
         * 限制条件是指授权生效的限制条件
         */
        @JSONField(name = "Principal")
        private List<String> principal;

        /**
         * 资源是指被授权的具体对象
         */
        @JSONField(name = "Resource")
        private List<String> resource;
    }
}
