package com.github.darkjrong.oss.api;

import com.github.darkjrong.oss.common.exception.AliyunOSSClientException;
import com.github.darkjrong.oss.common.pojo.dto.VideoSnapshotDTO;

import java.io.File;

/**
 *  视频处理API
 * @author Rong.Jia
 * @date 2021/02/23 21:47
 */
public interface VideoApi {

    /**
     * 视频截帧
     *
     * <p>
     *     当前仅支持对视频编码格式为H264的视频文件进行视频截帧。
     *     OSS当前没有默认保存视频截帧的操作，视频截帧的图片需手动下载到本地
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param desFile 本地保存文件
     * @param videoSnapshotDTO 截图参数
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean snapshot(String bucketName, String objectName, VideoSnapshotDTO videoSnapshotDTO, File desFile);

    /**
     * 视频截帧
     *
     * <p>
     *     当前仅支持对视频编码格式为H264的视频文件进行视频截帧。
     *     OSS当前没有默认保存视频截帧的操作，视频截帧的图片需手动下载到本地
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param videoSnapshotDTO 截图参数
     * @return 文件字节数组
     * @throws AliyunOSSClientException 操作异常
     */
    byte[] snapshot(String bucketName, String objectName, VideoSnapshotDTO videoSnapshotDTO) throws AliyunOSSClientException;

    /**
     * 视频截帧
     *
     * <p>
     *     当前仅支持对视频编码格式为H264的视频文件进行视频截帧。
     *     OSS当前没有默认保存视频截帧的操作，视频截帧的图片需手动下载到本地
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param videoSnapshotDTO 截图参数
     * @return 文件字节数组
     */
    String snapshot(VideoSnapshotDTO videoSnapshotDTO, String bucketName, String objectName);

    /**
     * 视频截帧
     *
     * <p>
     *     当前仅支持对视频编码格式为H264的视频文件进行视频截帧。
     *     OSS当前没有默认保存视频截帧的操作，视频截帧的图片需手动下载到本地
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒
     * @param videoSnapshotDTO 截图参数
     * @return 文件字节数组
     */
    String snapshot(VideoSnapshotDTO videoSnapshotDTO, String bucketName, String objectName, Long expirationTime);





















}
