package cn.darkjrong.oss.api;

import cn.darkjrong.oss.callback.ProgressCallBack;
import cn.darkjrong.oss.common.exception.AliyunOSSClientException;
import cn.darkjrong.oss.common.pojo.vo.FileInfoVO;
import com.aliyun.oss.model.*;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *  上传，下载，管理 API 接口
 * @author Rong.Jia
 * @date 2021/02/21 15:08
 */
public interface FileOperationsApi {

    /**
     * 上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息 可以为空
     * @param file 待上传文件
     * @return 文件访问地址
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO uploadFile(String bucketName, String directory, File file, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息 可以为空
     * @param bytes 待上传文件
     * @return 文件访问地址
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO uploadFile(String bucketName, String directory, byte[] bytes, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息 可以为空
     * @param inputStream 文件流
     * @param extName  文件扩展名（后缀名），扩展名不带“.”
     * @return 文件信息
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO uploadFile(String bucketName, String directory, InputStream inputStream, String extName, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param inputStream 文件流
     * @param tags 标签信息 可以为空
     * @return 文件访问地址
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO uploadFile(String bucketName, String directory, InputStream inputStream, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 分片上传
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param file 待上传文件
     * @param tags 标签信息
     * @return 文件信息
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO shardUploadFile(String bucketName, String directory, File file, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 分片上传
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param partSize  分片大小
     * @param tags 标签信息
     * @param file 待上传文件
     * @return 文件信息
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO shardUploadFile(String bucketName, String directory, File file, Long partSize, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 取消分片上传事件
     * @param bucketName 存储空间
     * @param objectName 文件名
     * @param uploadId 分片上传事件的唯一标识
     * @return 是否成功，true:成功，false: 失败
     */
    boolean abortShardUpload(String bucketName, String objectName, String uploadId);

    /**
     * 上传文件， 带上传进度，进度从回调函数返回
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param file 待上传文件
     * @param tags 标签信息，可以为空
     * @param progressCallBack 进度回调
     * @return  文件上传结果
     * @throws AliyunOSSClientException 文件上传异常
     */
    FileInfoVO uploadFile(String bucketName, String directory, File file, Map<String, String> tags, ProgressCallBack progressCallBack) throws AliyunOSSClientException;

    /**
     * 下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 文件 字节数组
     * @throws AliyunOSSClientException 下载异常
     */
    byte[] downloadFile(String bucketName, String objectName) throws AliyunOSSClientException;

    /**
     * 下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param localFileName 本地路径文件
     * @return 文件
     * @throws AliyunOSSClientException 下载异常
     */
    File downloadFile(String bucketName, String objectName, String localFileName) throws AliyunOSSClientException;

    /**
     * 下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param desFile 本地路径文件
     * @return 文件
     * @throws AliyunOSSClientException 下载异常
     */
    File downloadFile(String bucketName, String objectName, File desFile) throws AliyunOSSClientException;

    /**
     * 下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param localFile 本地路径文件
     * @param progressCallBack 进度回调
     * @return 是否下载成功，true:成功，false: 失败
     * @throws AliyunOSSClientException 下载异常
     */
    boolean downloadFile(String bucketName, String objectName, File localFile, ProgressCallBack progressCallBack);

    /**
     * 文件是否存在
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 是否存在，true: 是，false: 否
     */
    boolean doesObjectExist(String bucketName, String objectName);

    /**
     * 设置文件访问权限
     * @param bucketName 存储空间
     * @param objectName 完成路径文件名
     * @param cannedAccessControlList  访问权限
     *                                 CannedAccessControlList.Default: 继承Bucket
     *                                 CannedAccessControlList.Private：私有
     *                                 CannedAccessControlList.PublicRead：公共读
     *                                 CannedAccessControlList.PublicReadWrite：公共读写
     * @return 是否成功，true: 是，false: 否
     */
    boolean setObjectAcl(String bucketName, String objectName, CannedAccessControlList cannedAccessControlList);

    /**
     * 获取文件访问权限
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 权限信息
     * @throws AliyunOSSClientException 操作异常
     */
    ObjectPermission getObjectAcl(String bucketName, String objectName) throws AliyunOSSClientException;

    /**
     * 获取文件的全部元信息
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 元信息
     * @throws AliyunOSSClientException 操作异常
     */
    ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws AliyunOSSClientException;

    /**
     * 转换文件存储类型
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param storageClass  存储类型
     * @return  是否成功，true: 是，false: 否
     */
    boolean putObjectStorageClass(String bucketName, String objectName, StorageClass storageClass);

    /**
     * 解冻文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 是否成功，true: 是，false: 否
     */
    boolean restoreObject(String bucketName, String objectName);

    /**
     * 删除单个文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 是否成功，true: 是，false: 否
     */
    boolean deleteObject(String bucketName, String objectName);

    /**
     * 删除多个文件
     * @param bucketName 存储空间
     * @param objectNames 完整路径文件名
     * @return 失败列表
     */
    List<String> deleteObject(String bucketName, List<String> objectNames);

    /**
     *  拷贝文件, 针对小文件
     * @param sourceBucketName 原存储空间
     * @param sourceObjectName 原完整路径文件名
     * @param destinationBucketName 目标存储空间
     * @param destinationObjectName 目标完整路径文件名
     * @return 是否成功，true: 是，false: 否
     */
    boolean copyObject(String sourceBucketName, String sourceObjectName,
                       String destinationBucketName, String destinationObjectName);

    /**
     *  拷贝文件, 针对大文件
     * @param sourceBucketName 原存储空间
     * @param sourceObjectName 原完整路径文件名
     * @param destinationBucketName 目标存储空间
     * @param destinationObjectName 目标完整路径文件名
     * @return 是否成功，true: 是，false: 否
     */
    boolean copyBigObject(String sourceBucketName, String sourceObjectName,
                          String destinationBucketName, String destinationObjectName);

    /**
     * 对已上传Object添加或更改对象标签
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param tags 对象标签
     * @return 是否成功，true: 是，false: 否
     */
    boolean setObjectTagging(String bucketName, String objectName, Map<String, String> tags);

    /**
     * 获取对象标签
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 对象标签信息 如果不存在 则返回null
     */
    Map<String, String> getObjectTagging(String bucketName, String objectName);

    /**
     * 删除对象标签
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 是否成功，true: 是，false: 否
     */
    boolean deleteObjectTagging(String bucketName, String objectName);

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param file 待上传文件
     * @param limitSpeed 限速 单位：KB/s
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, File file, Integer limitSpeed, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param bytes 文件字节数组
     * @param limitSpeed 限速 单位：KB/s
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Integer limitSpeed, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param inputStream 文件输入流
     * @param limitSpeed 限速 单位：KB/s
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Integer limitSpeed, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param bytes 文件字节数组
     * @param extName  文件扩展名（后缀名），扩展名不带“.”
     * @param limitSpeed 限速 单位：KB/s
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Integer limitSpeed, Map<String, String> tags, String extName) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param extName  文件扩展名（后缀名），扩展名不带“.”
     * @param inputStream 文件输入流
     * @param limitSpeed 限速 单位：KB/s
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Integer limitSpeed, Map<String, String> tags, String extName) throws AliyunOSSClientException;


    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param file 待上传文件
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, File file, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param bytes 文件字节数组
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param inputStream 文件输入流
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Map<String, String> tags) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param extName  文件扩展名（后缀名），扩展名不带“.”
     * @param bytes 文件字节数组
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Map<String, String> tags, String extName) throws AliyunOSSClientException;

    /**
     * 限速上传文件
     * @param bucketName 存储空间
     * @param directory 上传目录
     * @param tags 标签信息
     * @param extName  文件扩展名（后缀名），扩展名不带“.”
     * @param inputStream 文件输入流
     * @return 文件信息
     * @throws AliyunOSSClientException 操作异常
     */
    FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Map<String, String> tags, String extName) throws AliyunOSSClientException;

    /**
     * 限速下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param limitSpeed 限速 单位：KB/s
     * @param desFile 目标文件
     * @return 是否成功，true: 成功，false: 失败
     */
    boolean speedLimitDownloadFile(String bucketName, String objectName, Integer limitSpeed, File desFile);

    /**
     * 限速下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param desFile 目标文件
     * @return 是否成功，true: 成功，false: 失败
     */
    boolean speedLimitDownloadFile(String bucketName, String objectName, File desFile);

    /**
     * 限速下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @param limitSpeed 限速 单位：KB/s
     * @return 文件字节数组
     * @throws AliyunOSSClientException 下载异常
     */
    byte[] speedLimitDownloadFile(String bucketName, String objectName, Integer limitSpeed) throws AliyunOSSClientException;

    /**
     * 限速下载文件
     * @param bucketName 存储空间
     * @param objectName 完整路径文件名
     * @return 文件字节数组
     * @throws AliyunOSSClientException 下载异常
     */
    byte[] speedLimitDownloadFile(String bucketName, String objectName) throws AliyunOSSClientException;









}
