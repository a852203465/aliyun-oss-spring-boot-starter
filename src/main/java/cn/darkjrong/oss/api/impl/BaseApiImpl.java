package cn.darkjrong.oss.api.impl;

import cn.darkjrong.oss.api.PresignedUrlApi;
import cn.darkjrong.oss.callback.ProgressCallBack;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.GetObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

/**
 * 公共Api 实现
 *
 * @author Rong.Jia
 * @date 2021/02/20 18:16
 */
public class BaseApiImpl {

    private static final Logger logger = LoggerFactory.getLogger(BaseApiImpl.class);

    private OSS ossClient;

    private PresignedUrlApi presignedUrlApi;

    public void setOssClient(OSS ossClient) {
        this.ossClient = ossClient;
    }

    public void setPresignedUrlApi(PresignedUrlApi presignedUrlApi) {
        this.presignedUrlApi = presignedUrlApi;
    }

    protected OSS getOssClient() {
        return ossClient;
    }

    /**
     * 生成文件名
     *
     * @param directory 文件目录
     * @param file      原文件
     * @return 文件名
     */
    protected String getFileName(String directory, File file) {
        return this.getFileName(directory, FileUtil.extName(file));
    }

    /**
     * 生成文件名
     *
     * @param directory 文件目录
     * @param extName   文件扩展名（后缀名），扩展名不带“.”
     * @return 文件名
     */
    protected String getFileName(String directory, String extName) {

        if (!StrUtil.startWith(extName, StrUtil.DOT)) {
            extName = StrUtil.DOT + extName;
        }

        return directory + StrUtil.SLASH + IdUtil.fastSimpleUUID() + StrUtil.DASHED + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_FORMAT) + extName;
    }

    /**
     * 上传进度监听
     *
     * @author Rong.Jia
     * @date 2021/02/21 17:22
     */
    protected class FileProgressListener implements ProgressListener {

        private long completeBytes = 0;
        private long totalBytes = -1;
        private boolean succeed = false;
        private ProgressCallBack progressCallBack;
        private String objectName;

        public FileProgressListener(ProgressCallBack progressCallBack, String objectName) {
            this.progressCallBack = progressCallBack;
        }

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                case TRANSFER_STARTED_EVENT:
                    logger.info("Start to upload/download......");
                    break;
                case RESPONSE_CONTENT_LENGTH_EVENT:
                case REQUEST_CONTENT_LENGTH_EVENT:
                    this.totalBytes = bytes;
                    break;
                case RESPONSE_BYTE_TRANSFER_EVENT:
                case REQUEST_BYTE_TRANSFER_EVENT:
                    this.completeBytes += bytes;
                    if (this.totalBytes != -1) {
                        progressCallBack.progress(objectName, totalBytes, completeBytes, Boolean.TRUE);
                    }
                    break;
                case TRANSFER_COMPLETED_EVENT:

                    // 上传成功
//                    progressCallBack.progress(totalBytes, completeBytes, Boolean.TRUE);
                    this.succeed = Boolean.TRUE;
                    break;
                case TRANSFER_FAILED_EVENT:

                    // 上传失败
                    progressCallBack.progress(objectName, totalBytes, completeBytes, Boolean.FALSE);
                    this.succeed = Boolean.FALSE;
                    break;
                default:
                    break;
            }
        }

        public boolean isSucceed() {
            return succeed;
        }

    }

    /**
     * 图片处理
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param style 样式
     * @param desFile 本地目标文件
     * @return 是否成功 true: 成功，false: 失败
     */
    protected boolean processing(String bucketName, String objectName, String style, File desFile) {

        GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
        request.setProcess(style);

        try {
            getOssClient().getObject(request, desFile);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("processing {}", e.getMessage());
        }

        return false;
    }

    /**
     * 图片处理
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param style 样式
     * @param expirationTime URL链接有效时间 单位毫秒
     * @return 图片URL
     */
    protected String processing(String bucketName, String objectName, String style, Long expirationTime) {
        return presignedUrlApi.getUrl(bucketName, objectName, expirationTime, style);
    }



}