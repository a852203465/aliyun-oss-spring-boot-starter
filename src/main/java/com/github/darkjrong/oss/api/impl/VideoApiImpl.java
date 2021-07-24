package com.github.darkjrong.oss.api.impl;

import com.github.darkjrong.oss.api.VideoApi;
import com.github.darkjrong.oss.common.builder.StyleBuilder;
import com.github.darkjrong.oss.common.constants.FileConstant;
import cn.darkjrong.oss.common.enums.*;
import com.github.darkjrong.oss.common.exception.AliyunOSSClientException;
import com.github.darkjrong.oss.common.pojo.dto.VideoSnapshotDTO;
import com.github.darkjrong.oss.common.utils.ExceptionUtils;
import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.github.darkjrong.oss.common.enums.VideoProcessingEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 *  视频处理API实现
 * @author Rong.Jia
 * @date 2021/02/23 21:48
 */
@Component
public class VideoApiImpl extends BaseApiImpl implements VideoApi {

    private static final Logger logger = LoggerFactory.getLogger(VideoApiImpl.class);

    @Override
    public boolean snapshot(String bucketName, String objectName, VideoSnapshotDTO videoSnapshotDTO, File desFile) {

        String style = StyleBuilder.custom().video()
                .processMode(VideoProcessingEnum.SNAPSHOT)
                .time(videoSnapshotDTO.getTime()).width(videoSnapshotDTO.getWidth())
                .height(videoSnapshotDTO.getHeight()).mode(videoSnapshotDTO.getMode())
                .snapshotFormat(videoSnapshotDTO.getFormat()).rotate(videoSnapshotDTO.getRotate())
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public byte[] snapshot(String bucketName, String objectName, VideoSnapshotDTO videoSnapshotDTO) throws AliyunOSSClientException {

        String style = StyleBuilder.custom().video()
                .processMode(VideoProcessingEnum.SNAPSHOT)
                .time(videoSnapshotDTO.getTime()).width(videoSnapshotDTO.getWidth())
                .height(videoSnapshotDTO.getHeight()).mode(videoSnapshotDTO.getMode())
                .snapshotFormat(videoSnapshotDTO.getFormat()).rotate(videoSnapshotDTO.getRotate())
                .build();

        GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
        request.setProcess(style);

        try {
            OSSObject object = getOssClient().getObject(request);
            return IoUtil.readBytes(object.getObjectContent());
        } catch (Exception e) {
            logger.error("snapshot {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public String snapshot(VideoSnapshotDTO videoSnapshotDTO, String bucketName, String objectName) {
        return this.snapshot(videoSnapshotDTO, bucketName, bucketName, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String snapshot(VideoSnapshotDTO videoSnapshotDTO, String bucketName, String objectName, Long expirationTime) {

        String style = StyleBuilder.custom().video()
                .processMode(VideoProcessingEnum.SNAPSHOT)
                .time(videoSnapshotDTO.getTime()).width(videoSnapshotDTO.getWidth())
                .height(videoSnapshotDTO.getHeight()).mode(videoSnapshotDTO.getMode())
                .snapshotFormat(videoSnapshotDTO.getFormat()).rotate(videoSnapshotDTO.getRotate())
                .build();

        return super.processing(bucketName, bucketName, style, expirationTime);
    }


}
