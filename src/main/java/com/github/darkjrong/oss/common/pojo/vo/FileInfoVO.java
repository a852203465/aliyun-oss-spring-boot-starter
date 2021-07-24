package com.github.darkjrong.oss.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  文件信息
 * @author Rong.Jia
 * @date 2021/02/21 16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoVO implements Serializable {

    private static final long serialVersionUID = 640091352155745357L;

    /**
     * 分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
     */
    private String uploadId;

    /**
     * 文件访问地址
     */
    private String url;

    /**
     * 分片上传/下载是否成功, true: 成功，false: 失败
     */
    private Boolean succeed;

    /**
     * 版本ID
     */
    private String versionId;



    public FileInfoVO(String uploadId, String url) {
        this.uploadId = uploadId;
        this.url = url;
    }


}
