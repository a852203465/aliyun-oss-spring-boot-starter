package cn.darkjrong.oss.api.impl;

import cn.darkjrong.oss.api.BucketApi;
import cn.darkjrong.oss.common.exception.AliyunOSSClientException;
import cn.darkjrong.oss.common.pojo.dto.BucketPolicyDTO;
import cn.darkjrong.oss.common.pojo.vo.BucketPolicyVO;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 存储空间 API 实现
 * @author Rong.Jia
 * @date 2021/02/20 18:14
 */
@Component
public class BucketApiImpl extends BaseApiImpl implements BucketApi {

    private static final Logger logger = LoggerFactory.getLogger(BucketApiImpl.class);

    @Override
    public Bucket createBucket(String bucketName) throws AliyunOSSClientException {

        try {
            return this.createBucket(bucketName, StorageClass.Standard,
                    DataRedundancyType.ZRS, CannedAccessControlList.Private);
        }catch (Exception e) {
            logger.error("createBucket {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public Bucket createBucket(String bucketName, StorageClass storageClass,
                               DataRedundancyType dataRedundancyType,
                               CannedAccessControlList cannedAccessControlList) throws AliyunOSSClientException {
        try {

            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setStorageClass(storageClass);
            createBucketRequest.setDataRedundancyType(dataRedundancyType);
            createBucketRequest.setCannedACL(cannedAccessControlList);
            return getOssClient().createBucket(createBucketRequest);
        }catch (Exception e) {
            logger.error("createBucket {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public List<Bucket> getBuckets(String bucketPrefix, Boolean flag) throws AliyunOSSClientException {

        try {

            List<Bucket> bucketList = null;

            if (!flag) {
                bucketList = getOssClient().listBuckets();
            }else {
                ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
                listBucketsRequest.setPrefix(bucketPrefix);
                bucketList = getOssClient().listBuckets(listBucketsRequest).getBucketList();
            }
            return bucketList;
        }catch (Exception e) {
            logger.error("findBuckets {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean doesBucketExist(String bucketName) {
        try {
            return getOssClient().doesBucketExist(bucketName);
        }catch (Exception e) {
            logger.error("doesBucketExist {}", e.getMessage());
        }
        return Boolean.FALSE;
    }

    @Override
    public String getBucketLocation(String bucketName) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketLocation(bucketName);
        }catch (Exception e) {
            logger.error("getBucketLocation {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public BucketInfo getBucketInfo(String bucketName) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketInfo(bucketName);
        }catch (Exception e) {
            logger.error("getBucketInfo {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean setBucketAcl(String bucketName, CannedAccessControlList cannedAccessControlList) {

        try {
            getOssClient().setBucketAcl(bucketName, cannedAccessControlList);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketAcl {}", e.getMessage());
        }

        return false;
    }

    @Override
    public AccessControlList getBucketAcl(String bucketName) throws AliyunOSSClientException {

        try {
            return getOssClient().getBucketAcl(bucketName);
        }catch (Exception e) {
            logger.error("getBucketAcl {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean deleteBucket(String bucketName) {

        try {
            getOssClient().deleteBucket(bucketName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucket {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketTagging(String bucketName, Map<String, String> tags) {

        try {
            if (tags == null || CollUtil.isEmpty(tags)) return Boolean.FALSE;

            getOssClient().setBucketTagging(bucketName, tags);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketTagging {}", e.getMessage());
        }

        return false;
    }

    @Override
    public Map<String, String> getBucketTagging(String bucketName) throws AliyunOSSClientException {

        try {
            return getOssClient().getBucketTagging(bucketName).getAllTags();
        }catch (Exception e) {
            logger.error("getBucketTagging {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public List<Bucket> getBuckets(String tagKey, String tagValue) throws AliyunOSSClientException {

        try {

            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
            listBucketsRequest.setTag(tagKey, tagValue);

            return this.getBuckets(listBucketsRequest);
        }catch (Exception e) {
            logger.error("getBucketsByTag {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public List<Bucket> getBuckets(ListBucketsRequest listBucketsRequest) throws AliyunOSSClientException {

        try {
            return getOssClient().listBuckets(listBucketsRequest).getBucketList();
        }catch (Exception e) {
            logger.error("getBuckets {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean deleteBucketTagging(String bucketName) {

        try {
            getOssClient().deleteBucketTagging(bucketName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketTagging {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketPolicy(String bucketName, BucketPolicyDTO bucketPolicyDTO) {

        try {
            getOssClient().setBucketPolicy(bucketName, JSON.toJSONString(bucketPolicyDTO));
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketPolicy {}", e.getMessage());
        }

        return false;
    }

    @Override
    public BucketPolicyVO getBucketPolicy(String bucketName) {

        try {

            GetBucketPolicyResult policyResult = getOssClient().getBucketPolicy(bucketName);
            String policyText = policyResult.getPolicyText();
            return JSONObject.parseObject(policyText, BucketPolicyVO.class);
        }catch (Exception e) {
            logger.error("getBucketPolicy {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteBucketPolicy(String bucketName) {

        try {
            getOssClient().deleteBucketPolicy(bucketName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketPolicy {}", e.getMessage());
        }

        return false;

    }

    @Override
    public boolean setBucketInventoryConfiguration(String bucketName, InventoryConfiguration inventoryConfiguration) {

        try {
            getOssClient().setBucketInventoryConfiguration(bucketName, inventoryConfiguration);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketInventoryConfiguration {}", e.getMessage());
        }

        return false;
    }

    @Override
    public GetBucketInventoryConfigurationResult getBucketInventoryConfiguration(String bucketName, String inventoryId) {
        try {
            return getOssClient().getBucketInventoryConfiguration(bucketName, inventoryId);
        }catch (Exception e) {
            logger.error("getBucketInventoryConfiguration {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteBucketInventoryConfiguration(String bucketName, String inventoryId) {

        try {
            getOssClient().deleteBucketInventoryConfiguration(bucketName, inventoryId);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketInventoryConfiguration {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Map<String, String> matchTags,
                                      Integer day, boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);

        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix,
                LifecycleRule.RuleStatus.Enabled, day);
        rule.setTags(matchTags);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);
        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Map<String, String> matchTags,
                                      Long time, boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);
        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled);
        rule.setTags(matchTags);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);
        rule.setCreatedBeforeDate(new Date(time));
        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Integer day, boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);
        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled);
        LifecycleRule.AbortMultipartUpload abortMultipartUpload = new LifecycleRule.AbortMultipartUpload();
        abortMultipartUpload.setExpirationDays(day);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);
        rule.setAbortMultipartUpload(abortMultipartUpload);
        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Long time, boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);
        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled);
        LifecycleRule.AbortMultipartUpload abortMultipartUpload = new LifecycleRule.AbortMultipartUpload();
        abortMultipartUpload.setCreatedBeforeDate(new Date(time));
        rule.setAbortMultipartUpload(abortMultipartUpload);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);
        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Integer expirationDays,
                                      StorageClass storageClass, boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);

        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled);

        List<LifecycleRule.StorageTransition> storageTransitions = new ArrayList<>();

        LifecycleRule.StorageTransition storageTransition = new LifecycleRule.StorageTransition();
        storageTransition.setStorageClass(storageClass);
        storageTransition.setExpirationDays(expirationDays);
        storageTransitions.add(storageTransition);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);
        rule.setStorageTransition(storageTransitions);

        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Long time,
                                      StorageClass storageClass, boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);

        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled);

        List<LifecycleRule.StorageTransition> storageTransitions = new ArrayList<>();

        LifecycleRule.StorageTransition storageTransition = new LifecycleRule.StorageTransition();
        storageTransition.setStorageClass(storageClass);
        storageTransition.setCreatedBeforeDate(new Date(time));
        storageTransitions.add(storageTransition);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);
        rule.setStorageTransition(storageTransitions);

        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLifecycle(String bucketName, String ruleId,
                                      String matchPrefix, Integer storageClassDays,
                                      Integer deleteDays, StorageClass storageClass,
                                      boolean expiredDeleteMarker) {

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest(bucketName);

        LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled);
        rule.setExpiredDeleteMarker(expiredDeleteMarker);

        LifecycleRule.NoncurrentVersionStorageTransition storageTransition =
                new LifecycleRule.NoncurrentVersionStorageTransition()
                        .withNoncurrentDays(storageClassDays).withStrorageClass(storageClass);

        if (Validator.isNotNull(deleteDays)) {
            LifecycleRule.NoncurrentVersionExpiration deleteExpiration = new LifecycleRule.NoncurrentVersionExpiration()
                    .withNoncurrentDays(deleteDays);

            rule.setNoncurrentVersionExpiration(deleteExpiration);
        }

        rule.setNoncurrentVersionStorageTransitions(CollectionUtil.newArrayList(storageTransition));

        request.AddLifecycleRule(rule);

        try {
            getOssClient().setBucketLifecycle(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public List<LifecycleRule> getBucketLifecycle(String bucketName) {

        try {
            return getOssClient().getBucketLifecycle(bucketName);
        }catch (Exception e) {
            logger.error("getBucketLifecycle {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteBucketLifecycle(String bucketName) {
        try {
            getOssClient().deleteBucketLifecycle(bucketName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketLifecycle {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketRequestPayment(String bucketName, Payer payer) {

        try {
            getOssClient().setBucketRequestPayment(bucketName, payer);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketRequestPayment {}", e.getMessage());
        }

        return false;
    }

    @Override
    public Payer getBucketRequestPayment(String bucketName) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketRequestPayment(bucketName).getPayer();
        }catch (Exception e) {
            logger.error("getBucketRequestPayment {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean setBucketReferer(String bucketName, List<String> refererList) {

        try {
            BucketReferer br = new BucketReferer(Boolean.TRUE, refererList);
            getOssClient().setBucketReferer(bucketName, br);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketReferer {}", e.getMessage());
        }

        return false;
    }

    @Override
    public List<String> getBucketReferer(String bucketName) throws AliyunOSSClientException {

        try {
            BucketReferer bucketReferer = getOssClient().getBucketReferer(bucketName);
            return bucketReferer.getRefererList();
        }catch (Exception e) {
            logger.error("getBucketReferer {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean deleteBucketReferer(String bucketName) {

        try {
            BucketReferer br = new BucketReferer();
            getOssClient().setBucketReferer(bucketName, br);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketReferer {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketLogging(String bucketName, String logBucketName, String logDir) {

        try {

            if (!this.doesBucketExist(logBucketName)){
                this.createBucket(logBucketName);
            }

            SetBucketLoggingRequest request = new SetBucketLoggingRequest(bucketName);
            request.setTargetBucket(logBucketName);
            request.setTargetPrefix(logDir);
            getOssClient().setBucketLogging(request);

            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketLogging {}", e.getMessage());
        }

        return false;
    }

    @Override
    public BucketLoggingResult getBucketLogging(String bucketName) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketLogging(bucketName);
        }catch (Exception e) {
            logger.error("getBucketLogging {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean closeBucketLogging(String bucketName) {

        try {

            SetBucketLoggingRequest request = new SetBucketLoggingRequest(bucketName);
            request.setTargetBucket(null);
            request.setTargetPrefix(null);

            getOssClient().setBucketLogging(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("getBucketLogging {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketWebsite(String bucketName, String indexDocument, String errorDocument) {

        try {

            SetBucketWebsiteRequest request = new SetBucketWebsiteRequest(bucketName);
            request.setIndexDocument(indexDocument);
            request.setErrorDocument(errorDocument);

            getOssClient().setBucketWebsite(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketWebsite {}", e.getMessage());
        }

        return false;
    }

    @Override
    public BucketWebsiteResult getBucketWebsite(String bucketName) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketWebsite(bucketName);
        }catch (Exception e) {
            logger.error("getBucketWebsite {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean deleteBucketWebsite(String bucketName) {

        try {
            getOssClient().deleteBucketWebsite(bucketName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketWebsite {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean enableBucketReplication(String sourceBucketName, String replicationRuleId, String targetBucketName, String targetBucketLocation, boolean enableHistoricalObjectReplication) {

        try {
            AddBucketReplicationRequest request = new AddBucketReplicationRequest(sourceBucketName);
            request.setReplicationRuleID(replicationRuleId);
            request.setTargetBucketName(targetBucketName);
            request.setTargetBucketLocation(targetBucketLocation);
            request.setEnableHistoricalObjectReplication(enableHistoricalObjectReplication);
            getOssClient().addBucketReplication(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("addBucketReplication {}", e.getMessage());
        }

        return false;
    }

    @Override
    public List<ReplicationRule> getBucketReplication(String bucketName) throws AliyunOSSClientException {

        try {
            return getOssClient().getBucketReplication(bucketName);
        }catch (Exception e) {
            logger.error("getBucketReplication {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public BucketReplicationProgress getBucketReplicationProgress(String bucketName, String replicationRuleId) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketReplicationProgress(bucketName, replicationRuleId);
        }catch (Exception e) {
            logger.error("getBucketReplicationProgress {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public List<String> getBucketReplicationLocation(String bucketName) {
        try {
            return getOssClient().getBucketReplicationLocation(bucketName);
        }catch (Exception e) {
            logger.error("getBucketReplicationLocation {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean closeBucketReplication(String bucketName, String replicationRuleId) {

        try {
            getOssClient().deleteBucketReplication(bucketName, replicationRuleId);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("closeBucketReplication {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketCORS(String bucketName, List<String> allowedOrigin, List<String> allowedMethod, List<String> allowedHeader, List<String> exposedHeader, Integer maxAgeSeconds) {

        SetBucketCORSRequest request = new SetBucketCORSRequest(bucketName);

        List<SetBucketCORSRequest.CORSRule> putCorsRules = new ArrayList<>();
        SetBucketCORSRequest.CORSRule corRule = new SetBucketCORSRequest.CORSRule();

        corRule.setAllowedMethods(allowedMethod);
        corRule.setAllowedOrigins(allowedOrigin);
        corRule.setAllowedHeaders(allowedHeader);
        corRule.setExposeHeaders(exposedHeader);
        corRule.setMaxAgeSeconds(maxAgeSeconds);

        putCorsRules.add(corRule);
        request.setCorsRules(putCorsRules);

        try {
            getOssClient().setBucketCORS(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketCORS {}", e.getMessage());
        }

        return false;
    }

    @Override
    public List<SetBucketCORSRequest.CORSRule> getBucketCORSRules(String bucketName) throws AliyunOSSClientException {
        try {
            return getOssClient().getBucketCORSRules(bucketName);
        }catch (Exception e) {
            logger.error("getBucketCORSRules {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }
    }

    @Override
    public boolean deleteBucketCORSRules(String bucketName) {

        try {
            getOssClient().deleteBucketCORSRules(bucketName);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("deleteBucketCORSRules {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setBucketVersioning(String bucketName) {

        BucketVersioningConfiguration configuration = new BucketVersioningConfiguration();
        configuration.setStatus(BucketVersioningConfiguration.ENABLED);
        SetBucketVersioningRequest request = new SetBucketVersioningRequest(bucketName, configuration);

        try {
            getOssClient().setBucketVersioning(request);
            return Boolean.TRUE;
        }catch (Exception e) {
            logger.error("setBucketVersioning {}", e.getMessage());
        }

        return false;
    }

    @Override
    public String getBucketVersioning(String bucketName) {
        try {
            BucketVersioningConfiguration versionConfiguration = getOssClient().getBucketVersioning(bucketName);
            return versionConfiguration.getStatus();
        }catch (Exception e) {
            logger.error("getBucketVersioning {}", e.getMessage());
        }

        return null;
    }

    @Override
    public boolean setBucketEncryption(String bucketName, String kmsMasterKeyId) {
        return this.setBucketEncryption(bucketName, SSEAlgorithm.KMS, kmsMasterKeyId);
    }

    @Override
    public boolean setBucketEncryption(String bucketName, SSEAlgorithm sseAlgorithm, String kmsMasterKeyId) {

        ServerSideEncryptionByDefault applyServerSideEncryptionByDefault = new ServerSideEncryptionByDefault(SSEAlgorithm.KMS);
        applyServerSideEncryptionByDefault.setSSEAlgorithm(sseAlgorithm);

        if (SSEAlgorithm.KMS == sseAlgorithm && StrUtil.isNotBlank(kmsMasterKeyId)) {
            applyServerSideEncryptionByDefault.setKMSMasterKeyID(kmsMasterKeyId);
        }

        ServerSideEncryptionConfiguration sseConfig = new ServerSideEncryptionConfiguration();
        sseConfig.setApplyServerSideEncryptionByDefault(applyServerSideEncryptionByDefault);

        try {

            SetBucketEncryptionRequest request = new SetBucketEncryptionRequest(bucketName, sseConfig);
            getOssClient().setBucketEncryption(request);
        }catch (Exception e) {
            logger.error("setBucketEncryption {}", e.getMessage());
        }

        return false;
    }


}
