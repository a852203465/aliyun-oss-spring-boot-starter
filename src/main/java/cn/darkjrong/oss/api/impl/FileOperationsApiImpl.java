package cn.darkjrong.oss.api.impl;

import cn.darkjrong.oss.api.BucketApi;
import cn.darkjrong.oss.api.FileOperationsApi;
import cn.darkjrong.oss.callback.ProgressCallBack;
import cn.darkjrong.oss.common.constants.FileConstant;
import cn.darkjrong.oss.common.enums.ExceptionEnum;
import cn.darkjrong.oss.common.exception.AliyunOSSClientException;
import cn.darkjrong.oss.common.pojo.vo.FileInfoVO;
import cn.darkjrong.oss.common.utils.ExceptionUtils;
import cn.darkjrong.spring.boot.autoconfigure.AliyunOSSProperties;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 上传，下载，管理 API 接口实现类
 * @author Rong.Jia
 * @date 2021/02/21 15:08
 */
@Component
public class FileOperationsApiImpl extends BaseApiImpl implements FileOperationsApi {

    private static final Logger logger = LoggerFactory.getLogger(FileOperationsApiImpl.class);

    private BucketApi bucketApi;

    public void setBucketApi(BucketApi bucketApi) {
        this.bucketApi = bucketApi;
    }

    @Override
    public FileInfoVO uploadFile(String bucketName, String directory, File file, Map<String, String> tags) throws AliyunOSSClientException {

        if (!FileUtil.exist(file)) {
            logger.error("uploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        String extName = StrUtil.DOT + FileUtil.extName(file);
        return this.uploadFile(bucketName, directory, FileUtil.getInputStream(file), extName, tags);
    }

    @Override
    public FileInfoVO uploadFile(String bucketName, String directory, byte[] bytes, Map<String, String> tags) throws AliyunOSSClientException {

        if (ArrayUtil.isEmpty(bytes)) {
            logger.error("Bytes cannot be empty");
            throw new IllegalArgumentException(ExceptionEnum.BYTES_CANNOT_BE_EMPTY.getValue());
        }

        return this.uploadFile(bucketName, directory, new ByteArrayInputStream(bytes), tags);
    }

    @Override
    public FileInfoVO uploadFile(String bucketName, String directory, InputStream inputStream, String extName, Map<String, String> tags) throws AliyunOSSClientException {

        Assert.notNull(inputStream, ExceptionEnum.THE_INPUT_STREAM_CANNOT_BE_EMPTY.getValue());
        Assert.notBlank(bucketName, ExceptionEnum.BUCKET_NAME_CANNOT_BE_NULL.getValue());
        Assert.notBlank(directory, ExceptionEnum.DIRECTORY_CANNOT_BE_EMPTY.getValue());

        if (!bucketApi.doesBucketExist(bucketName)) {
            bucketApi.createBucket(bucketName);
        }

        String fileName = getFileName(directory, extName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);

        if (ObjectUtil.isNotNull(tags) && CollectionUtil.isNotEmpty(tags) &&tags.size() > 0) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectTagging(tags);
            putObjectRequest.setMetadata(metadata);
        }

        try {

            PutObjectResult putObjectResult = getOssClient().putObject(putObjectRequest);

            FileInfoVO fileInfoVO = new FileInfoVO();
            fileInfoVO.setUrl(fileName);
            fileInfoVO.setVersionId(putObjectResult.getVersionId());

            return fileInfoVO;
        }catch (Exception e) {
            logger.error("uploadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public FileInfoVO uploadFile(String bucketName, String directory, InputStream inputStream, Map<String, String> tags) throws AliyunOSSClientException {
        return this.uploadFile(bucketName, directory, inputStream, FileConstant.JPEG_SUFFIX, tags);
    }

    @Override
    public FileInfoVO shardUploadFile(String bucketName, String directory, File file, Map<String, String> tags) throws AliyunOSSClientException {

        return this.shardUploadFile(bucketName, directory, file, FileConstant.PART_SIZE, tags);
    }

    @Override
    public FileInfoVO shardUploadFile(String bucketName, String directory, File file, Long partSize, Map<String, String> tags) throws AliyunOSSClientException {

        if (!FileUtil.exist(file)) {
            logger.error("shardUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        Assert.notBlank(bucketName, ExceptionEnum.BUCKET_NAME_CANNOT_BE_NULL.getValue());
        Assert.notBlank(directory, ExceptionEnum.DIRECTORY_CANNOT_BE_EMPTY.getValue());

        if (!bucketApi.doesBucketExist(bucketName)) {
            bucketApi.createBucket(bucketName);
        }

        if (Validator.isNull(partSize) || partSize <=0) {
            partSize = FileConstant.PART_SIZE;
        }

        String objectName = getFileName(directory, file);

        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags = new ArrayList<>();

        long fileLength = FileUtil.size(file);
        int partCount = (int) (fileLength / partSize);

        if (fileLength % partSize != 0) {
            partCount++;
        }

        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);

        if (ObjectUtil.isNotNull(tags) && CollectionUtil.isNotEmpty(tags) &&tags.size() > 0) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectTagging(tags);
            request.setObjectMetadata(metadata);
        }

        try {

            InitiateMultipartUploadResult result = getOssClient().initiateMultipartUpload(request);

            // 返回uploadId，它是分片上传事件的唯一标识
            String uploadId = result.getUploadId();

            for (int i = 0; i < partCount; i++) {

                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;

                InputStream instep = FileUtil.getInputStream(file);

                instep.skip(startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(objectName);
                uploadPartRequest.setUploadId(uploadId);
                uploadPartRequest.setInputStream(instep);
                uploadPartRequest.setPartSize(curPartSize);
                uploadPartRequest.setPartNumber(i + 1);
                UploadPartResult uploadPartResult = getOssClient().uploadPart(uploadPartRequest);
                partETags.add(uploadPartResult.getPartETag());

                IoUtil.close(instep);
            }

            partETags.sort(Comparator.comparingInt(PartETag::getPartNumber));

            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);
            CompleteMultipartUploadResult completeMultipartUploadResult = getOssClient().completeMultipartUpload(completeMultipartUploadRequest);

            FileInfoVO fileInfoVO = new FileInfoVO(uploadId, objectName);
            fileInfoVO.setVersionId(completeMultipartUploadResult.getVersionId());

            return fileInfoVO;
        }catch (Exception e) {
            logger.error("shardUploadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }

    }

    @Override
    public boolean abortShardUpload(String bucketName, String objectName, String uploadId) {

        AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest(bucketName, objectName, uploadId);

        try {
            getOssClient().abortMultipartUpload(abortMultipartUploadRequest);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("abortShardUpload {}", e.getMessage());
        }

        return false;
    }

    @Override
    public FileInfoVO uploadFile(String bucketName, String directory, File file, Map<String, String> tags, ProgressCallBack progressCallBack) throws AliyunOSSClientException {

        Assert.notBlank(bucketName, ExceptionEnum.BUCKET_NAME_CANNOT_BE_NULL.getValue());
        Assert.notBlank(directory, ExceptionEnum.DIRECTORY_CANNOT_BE_EMPTY.getValue());

        if (!FileUtil.exist(file)) {
            logger.error("uploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        if (!bucketApi.doesBucketExist(bucketName)) {
            bucketApi.createBucket(bucketName);
        }

        String objectName = getFileName(directory, file);
        FileProgressListener fileProgressListener = new FileProgressListener(progressCallBack, objectName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file).<PutObjectRequest>withProgressListener(fileProgressListener);

        if (ObjectUtil.isNotNull(tags) && CollectionUtil.isNotEmpty(tags) &&tags.size() > 0) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectTagging(tags);
            putObjectRequest.setMetadata(metadata);
        }

        try {

            getOssClient().putObject(putObjectRequest);

            FileInfoVO fileInfoVO = new FileInfoVO();
            fileInfoVO.setUrl(objectName);
            fileInfoVO.setSucceed(fileProgressListener.isSucceed());

            return fileInfoVO;
        } catch (Exception e) {
            logger.error("uploadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public byte[] downloadFile(String bucketName, String objectName) throws AliyunOSSClientException {

        OSSObject ossObject = null;

        try {
            ossObject = getOssClient().getObject(bucketName, objectName);
            return IoUtil.readBytes(ossObject.getObjectContent());
        }catch (Exception e) {
            logger.error("downloadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }finally {
            if (ObjectUtil.isNotNull(ossObject)) {
                IoUtil.close(ossObject);
            }
        }
    }

    @Override
    public File downloadFile(String bucketName, String objectName, String localFileName) throws AliyunOSSClientException {

        Assert.notBlank(localFileName, ExceptionEnum.FILE_NAME_CANNOT_BE_EMPTY.getValue());

        try {
            File file = new File(localFileName);
            getOssClient().getObject(new GetObjectRequest(bucketName, objectName), file);
            return file;
        }catch (Exception e) {
            logger.error("downloadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public File downloadFile(String bucketName, String objectName, File desFile) throws AliyunOSSClientException {
        try {
            getOssClient().getObject(new GetObjectRequest(bucketName, objectName), desFile);
            return desFile;
        }catch (Exception e) {
            logger.error("downloadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public boolean downloadFile(String bucketName, String objectName, File localFile, ProgressCallBack progressCallBack) {

        try {

            FileProgressListener fileProgressListener = new FileProgressListener(progressCallBack, objectName);
            getOssClient().getObject(new GetObjectRequest(bucketName, objectName).<GetObjectRequest>withProgressListener(fileProgressListener), localFile);
            return fileProgressListener.isSucceed();
        }catch (Exception e) {
            logger.error("downloadFile {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean doesObjectExist(String bucketName, String objectName) {

        try {
            return getOssClient().doesObjectExist(bucketName, objectName);
        }catch (Exception e) {
            logger.error("doesObjectExist {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setObjectAcl(String bucketName, String objectName, CannedAccessControlList cannedAccessControlList) {

        try {
            getOssClient().setObjectAcl(bucketName, objectName, cannedAccessControlList);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setObjectAcl {}", e.getMessage());
        }

        return false;
    }

    @Override
    public ObjectPermission getObjectAcl(String bucketName, String objectName) throws AliyunOSSClientException {

        try {
            ObjectAcl objectAcl = getOssClient().getObjectAcl(bucketName, objectName);
            return objectAcl.getPermission();
        }catch (Exception e) {
            logger.error("getObjectAcl {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws AliyunOSSClientException {
        try {
            return getOssClient().getObjectMetadata(bucketName, objectName);
        }catch (Exception e) {
            logger.error("getObjectMetadata {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }
    }

    @Override
    public boolean putObjectStorageClass(String bucketName, String objectName, StorageClass storageClass) {

        CopyObjectRequest request = new CopyObjectRequest(bucketName, objectName, bucketName, objectName) ;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setHeader("x-oss-storage-class", storageClass);
        request.setNewObjectMetadata(objectMetadata);

        try {

            if (restoreObject(bucketName, objectName)) {
                getOssClient().copyObject(request);
                return Boolean.TRUE;
            }
        }catch (Exception e) {
            logger.error("putObjectStorageClass {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreObject(String bucketName, String objectName) {

        try {

            ObjectMetadata objectMetadata = getOssClient().getObjectMetadata(bucketName, objectName);

            StorageClass storageClass = objectMetadata.getObjectStorageClass();
            if (storageClass == StorageClass.Archive) {
                getOssClient().restoreObject(bucketName, objectName);
                do {
                    Thread.sleep(1000);
                    objectMetadata = getOssClient().getObjectMetadata(bucketName, objectName);
                } while (!objectMetadata.isRestoreCompleted());
            }

            return objectMetadata.isRestoreCompleted();
        }catch (Exception e) {
            logger.error("restoreObject {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteObject(String bucketName, String objectName) {

        try {
            getOssClient().deleteObject(bucketName, objectName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteObject {}", e.getMessage());
        }

        return false;
    }

    @Override
    public List<String> deleteObject(String bucketName, List<String> objectNames) {

        try {

            DeleteObjectsResult deleteObjectsResult = getOssClient()
                    .deleteObjects(new DeleteObjectsRequest(bucketName).withQuiet(Boolean.TRUE).withKeys(objectNames));

            return deleteObjectsResult.getDeletedObjects();
        }catch (Exception e) {
            logger.error("deleteObject {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean copyObject(String sourceBucketName, String sourceObjectName, String destinationBucketName, String destinationObjectName) {

        try {
            getOssClient().copyObject(sourceBucketName, sourceObjectName,
                    destinationBucketName, destinationObjectName);

            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("copyObject {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean copyBigObject(String sourceBucketName, String sourceObjectName, String destinationBucketName, String destinationObjectName) {

        try {

            ObjectMetadata objectMetadata = getOssClient().getObjectMetadata(sourceBucketName, sourceObjectName);
            long contentLength = objectMetadata.getContentLength();
            long partSize = FileConstant.PART_SIZE;

            int partCount = (int) (contentLength / partSize);
            if (contentLength % partSize != 0) {
                partCount++;
            }

            InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(destinationBucketName, destinationObjectName);
            InitiateMultipartUploadResult initiateMultipartUploadResult = getOssClient().initiateMultipartUpload(initiateMultipartUploadRequest);
            String uploadId = initiateMultipartUploadResult.getUploadId();

            List<PartETag> partETags = new ArrayList<>();

            for (int i = 0; i < partCount; i++) {
                long skipBytes = partSize * i;
                long size = Math.min(partSize, contentLength - skipBytes);

                UploadPartCopyRequest uploadPartCopyRequest = new UploadPartCopyRequest(sourceBucketName, sourceObjectName, destinationBucketName, destinationObjectName);
                uploadPartCopyRequest.setUploadId(uploadId);
                uploadPartCopyRequest.setPartSize(size);
                uploadPartCopyRequest.setBeginIndex(skipBytes);
                uploadPartCopyRequest.setPartNumber(i + 1);
                UploadPartCopyResult uploadPartCopyResult = getOssClient().uploadPartCopy(uploadPartCopyRequest);

                partETags.add(uploadPartCopyResult.getPartETag());
            }

            CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
                    destinationBucketName, destinationObjectName, uploadId, partETags);
            getOssClient().completeMultipartUpload(completeMultipartUploadRequest);

            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("copyBigObject {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setObjectTagging(String bucketName, String objectName, Map<String, String> tags) {

        try {
            getOssClient().setObjectTagging(bucketName, objectName, tags);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setObjectTagging {}", e.getMessage());
        }

        return false;
    }

    @Override
    public Map<String, String> getObjectTagging(String bucketName, String objectName) {

        try {
            TagSet tagSet = getOssClient().getObjectTagging(bucketName, objectName);
            return tagSet.getAllTags();
        }catch (Exception e) {
            logger.error("getObjectTagging {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteObjectTagging(String bucketName, String objectName) {

        try {
            getOssClient().deleteObjectTagging(bucketName, objectName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteObjectTagging {}", e.getMessage());
        }

        return false;
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, File file, Integer limitSpeed, Map<String, String> tags) throws AliyunOSSClientException {

        if (!FileUtil.exist(file)) {
            logger.error("speedLimitUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        return this.speedLimitUploadFile(bucketName, directory, FileUtil.getInputStream(file), limitSpeed, tags, FileUtil.extName(file));
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Integer limitSpeed, Map<String, String> tags) throws AliyunOSSClientException {

        if (ArrayUtil.isEmpty(bytes)) {
            logger.error("speedLimitUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        return this.speedLimitUploadFile(bucketName, directory, new ByteArrayInputStream(bytes), limitSpeed, tags, FileConstant.JPEG_SUFFIX);
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Integer limitSpeed, Map<String, String> tags) throws AliyunOSSClientException {
        return this.speedLimitUploadFile(bucketName, directory, inputStream, limitSpeed, tags, FileConstant.JPEG_SUFFIX);
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, File file, Map<String, String> tags) throws AliyunOSSClientException {

        if (!FileUtil.exist(file)) {
            logger.error("speedLimitUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        return this.speedLimitUploadFile(bucketName, directory, FileUtil.getInputStream(file), FileConstant.LIMIT_SPEED, tags, FileUtil.extName(file));
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Map<String, String> tags) throws AliyunOSSClientException {

        if (ArrayUtil.isEmpty(bytes)) {
            logger.error("speedLimitUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        return this.speedLimitUploadFile(bucketName, directory, new ByteArrayInputStream(bytes), FileConstant.LIMIT_SPEED, tags, FileConstant.JPEG_SUFFIX);
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Map<String, String> tags) throws AliyunOSSClientException {

        return this.speedLimitUploadFile(bucketName, directory, inputStream, FileConstant.LIMIT_SPEED, tags, FileConstant.JPEG_SUFFIX);

    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Integer limitSpeed, Map<String, String> tags, String extName) throws AliyunOSSClientException {

        if (ArrayUtil.isEmpty(bytes)) {
            logger.error("speedLimitUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }

        return this.speedLimitUploadFile(bucketName, directory, new ByteArrayInputStream(bytes), limitSpeed, tags, extName);
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Integer limitSpeed, Map<String, String> tags, String extName) throws AliyunOSSClientException {

        Assert.notBlank(bucketName, ExceptionEnum.BUCKET_NAME_CANNOT_BE_NULL.getValue());
        Assert.notBlank(directory, ExceptionEnum.DIRECTORY_CANNOT_BE_EMPTY.getValue());
        Assert.notNull(inputStream, ExceptionEnum.THE_INPUT_STREAM_CANNOT_BE_EMPTY.getValue());

        if (Validator.isNull(limitSpeed) || limitSpeed <= 0) {
            limitSpeed = FileConstant.LIMIT_SPEED;
        }

        limitSpeed = limitSpeed * 8;

        String fileName = getFileName(directory, extName);

        if (!bucketApi.doesBucketExist(bucketName)) {
            bucketApi.createBucket(bucketName);
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
        putObjectRequest.setTrafficLimit(limitSpeed);

        if (ObjectUtil.isNotNull(tags) && CollectionUtil.isNotEmpty(tags) &&tags.size() > 0) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setObjectTagging(tags);
            putObjectRequest.setMetadata(metadata);
        }

        try {

            PutObjectResult putObjectResult = getOssClient().putObject(putObjectRequest);

            FileInfoVO fileInfoVO = new FileInfoVO();
            fileInfoVO.setVersionId(putObjectResult.getVersionId());
            fileInfoVO.setUrl(fileName);

            return fileInfoVO;
        }catch (Exception e) {
            logger.error("speedLimitUploadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, byte[] bytes, Map<String, String> tags, String extName) throws AliyunOSSClientException {
        if (ArrayUtil.isEmpty(bytes)) {
            logger.error("speedLimitUploadFile() File does not exist");
            throw new AliyunOSSClientException(ExceptionEnum.FILE_DOES_NOT_EXIST.getValue());
        }
        return this.speedLimitUploadFile(bucketName, directory, new ByteArrayInputStream(bytes), tags, extName);
    }

    @Override
    public FileInfoVO speedLimitUploadFile(String bucketName, String directory, InputStream inputStream, Map<String, String> tags, String extName) throws AliyunOSSClientException {
        return this.speedLimitUploadFile(bucketName, directory, inputStream, FileConstant.LIMIT_SPEED, tags, extName);
    }

    @Override
    public boolean speedLimitDownloadFile(String bucketName, String objectName, Integer limitSpeed, File desFile) {


        if (Validator.isNull(limitSpeed) || limitSpeed <= 0) {
            limitSpeed = FileConstant.LIMIT_SPEED;
        }

        limitSpeed = limitSpeed * 8;

        try {

            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectName);
            getObjectRequest.setTrafficLimit(limitSpeed);
            getOssClient().getObject(getObjectRequest, desFile);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("speedLimitDownloadFile {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean speedLimitDownloadFile(String bucketName, String objectName, File desFile) {
        return this.speedLimitDownloadFile(bucketName, objectName, FileConstant.LIMIT_SPEED, desFile);
    }

    @Override
    public byte[] speedLimitDownloadFile(String bucketName, String objectName, Integer limitSpeed) throws AliyunOSSClientException {


        if (Validator.isNull(limitSpeed) || limitSpeed <= 0) {
            limitSpeed = FileConstant.LIMIT_SPEED;
        }

        limitSpeed = limitSpeed * 8;

        try {

            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectName);
            getObjectRequest.setTrafficLimit(limitSpeed);
            OSSObject ossObject = getOssClient().getObject(getObjectRequest);
            return IoUtil.readBytes(ossObject.getObjectContent());
        }catch (Exception e) {
            logger.error("speedLimitDownloadFile {}", e.getMessage());
            throw new AliyunOSSClientException(ExceptionUtils.exception(e));
        }

    }

    @Override
    public byte[] speedLimitDownloadFile(String bucketName, String objectName) throws AliyunOSSClientException {
        return this.speedLimitDownloadFile(bucketName, objectName, FileConstant.LIMIT_SPEED);
    }



}
