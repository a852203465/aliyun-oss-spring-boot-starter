package com.github.darkjrong.oss.api;

import com.github.darkjrong.oss.api.impl.BucketApiImpl;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.aliyun.oss.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 存储空间测试类
 *
 * @author Rong.Jia
 * @date 2021/02/20 18:28
 */
public class BucketApiTest extends BaseApiTest {

    private static BucketApiImpl bucketApi = new BucketApiImpl();

    static {
        bucketApi.setOssClient(ossClient);
    }

    @Test
    public void createBucket() {

        Bucket bucket = bucketApi.createBucket("mrj123421156mrj");

        System.out.println(bucket.toString());
    }

    @Test
    public void findBuckets() {

        System.out.println(bucketApi.getBuckets(null, false));

    }

    @Test
    public void doesBucketExist() {

        System.out.println(bucketApi.doesBucketExist("mrj123456mrj"));

    }

    @Test
    public void getBucketLocation() {

        System.out.println(bucketApi.getBucketLocation("mrj123456mrj"));
    }

    @Test
    public void getBucketInfo() {
        System.out.println(JSON.toJSONString(bucketApi.getBucketInfo("mrj123456mrj")));
    }

    @Test
    public void setBucketAcl() {
        System.out.println(bucketApi.setBucketAcl("mrj123456mrj0", CannedAccessControlList.Private));

    }

    @Test
    public void getBucketAcl() {
        System.out.println(bucketApi.getBucketAcl("mrj123456mrj0"));

    }

    @Test
    public void setBucketTagging() {

        Map<String, String> tags = CollUtil.newHashMap();
        tags.put("tag1", "tag1");
        tags.put("tag2", "tag2");

        System.out.println(bucketApi.setBucketTagging("mrj123456mrj0", tags));

    }

    @Test
    public void getBucketTagging() {

        System.out.println(bucketApi.getBucketTagging("mrj123456mrj0"));

    }

    @Test
    public void deleteBucketTagging() {

        System.out.println(bucketApi.deleteBucketTagging("mrj123456mrj0"));

    }

    @Test
    public void getBucketPolicy() {

        System.out.println(bucketApi.getBucketPolicy("mrj123456mrj0"));

    }

    @Test
    public void setBucketInventoryConfiguration() {

        String destBucketName ="<yourDestinationBucketName>";
        String accountId ="<yourDestinationBucketAccountId>";
        String roleArn ="<yourDestinationBucketRoleArn>";

        // 创建清单配置。
        InventoryConfiguration inventoryConfiguration = new InventoryConfiguration();

        // 设置清单配置id。
        String inventoryId = "testid";
        inventoryConfiguration.setInventoryId(inventoryId);

        // 设置清单中包含的object属性。
        List<String> fields = new ArrayList<String>();
        fields.add(InventoryOptionalFields.Size);
        fields.add(InventoryOptionalFields.LastModifiedDate);
        fields.add(InventoryOptionalFields.IsMultipartUploaded);
        fields.add(InventoryOptionalFields.StorageClass);
        fields.add(InventoryOptionalFields.ETag);
        fields.add(InventoryOptionalFields.EncryptionStatus);
        inventoryConfiguration.setOptionalFields(fields);

        // 设置清单的生成计划，以下示例为每周一次。其中，Weekly对应每周一次，Daily对应每天一次。
        inventoryConfiguration.setSchedule(new InventorySchedule().withFrequency(InventoryFrequency.Weekly));

        // 设置清单中包含的object的版本为当前版本。如果设置为InventoryIncludedObjectVersions.All则表示object的所有版本，在版本控制状态下生效。
        inventoryConfiguration.setIncludedObjectVersions(InventoryIncludedObjectVersions.Current);

        // 清单配置是否启用的标识, true或false。
        inventoryConfiguration.setEnabled(true);

        // 设置清单筛选规则，指定筛选object的前缀。
        InventoryFilter inventoryFilter = new InventoryFilter().withPrefix("obj-prefix");
        inventoryConfiguration.setInventoryFilter(inventoryFilter);

        // 创建清单的bucket目的地配置。
        InventoryOSSBucketDestination ossInvDest = new InventoryOSSBucketDestination();
        // 设置产生清单结果的存储路径前缀。
        ossInvDest.setPrefix("destination-prefix");
        // 设置清单格式。
        ossInvDest.setFormat(InventoryFormat.CSV);
        // 目的地bucket的用户accountId。
        ossInvDest.setAccountId(accountId);
        // 目的地bucket的roleArn。
        ossInvDest.setRoleArn(roleArn);
        // 目的地bucket的名称。
        ossInvDest.setBucket(destBucketName);

        // 如果需要使用KMS加密清单，请参考如下设置。
        // InventoryEncryption inventoryEncryption = new InventoryEncryption();
        // InventoryServerSideEncryptionKMS serverSideKmsEncryption = new InventoryServerSideEncryptionKMS().withKeyId("test-kms-id");
        // inventoryEncryption.setServerSideKmsEncryption(serverSideKmsEncryption);
        // ossInvDest.setEncryption(inventoryEncryption);

        // 如果需要使用OSS服务端加密清单，请参考如下设置。
        // InventoryEncryption inventoryEncryption = new InventoryEncryption();
        // inventoryEncryption.setServerSideOssEncryption(new InventoryServerSideEncryptionOSS());
        // ossInvDest.setEncryption(inventoryEncryption);

        // 设置清单的目的地。
        InventoryDestination destination = new InventoryDestination();
        destination.setOssBucketDestination(ossInvDest);
        inventoryConfiguration.setDestination(destination);

        System.out.println(bucketApi.setBucketInventoryConfiguration("mrj123456mrj0", inventoryConfiguration));

    }

    @Test
    public void getBucketInventoryConfiguration() {

        System.out.println(bucketApi.getBucketInventoryConfiguration("mrj123456mrj0", "test"));

    }


    @Test
    public void getBucketLifecycle() {

        List<LifecycleRule> lifecycleRuleList = bucketApi.getBucketLifecycle("mrj123456mrj0");

        for (LifecycleRule r : lifecycleRuleList) {

            // 查看规则id。
            System.out.println("rule id: " + r.getId());

            // 查看规则状态。
            System.out.println("rule status: " + r.getStatus());

            // 查看规则前缀。
            System.out.println("rule prefix: " + r.getPrefix());

            // 查看规则标签。
            if (r.hasTags()) {
                System.out.println("rule tagging: "+ r.getTags().toString());
            }

            // 查看过期天数规则。
            if (r.hasExpirationDays()) {
                System.out.println("rule expiration days: " + r.getExpirationDays());
            }

            // 查看过期日期规则。
            if (r.hasCreatedBeforeDate()) {
                System.out.println("rule expiration create before days: " + r.getCreatedBeforeDate());
            }

            // 查看过期分片规则。
            if(r.hasAbortMultipartUpload()) {
                if(r.getAbortMultipartUpload().hasExpirationDays()) {
                    System.out.println("rule abort uppart days: " + r.getAbortMultipartUpload().getExpirationDays());
                }

                if (r.getAbortMultipartUpload().hasCreatedBeforeDate()) {
                    System.out.println("rule abort uppart create before date: " + r.getAbortMultipartUpload().getCreatedBeforeDate());
                }
            }

            // 查看存储类型转换规则。
            if (r.getStorageTransition().size() > 0) {
                for (LifecycleRule.StorageTransition transition : r.getStorageTransition()) {
                    if (transition.hasExpirationDays()) {
                        System.out.println("rule storage trans days: " + transition.getExpirationDays() +
                                " trans storage class: " + transition.getStorageClass());
                    }

                    if (transition.hasCreatedBeforeDate()) {
                        System.out.println("rule storage trans before create date: " + transition.getCreatedBeforeDate());
                    }
                }
            }

            // 查看是否自动删除过期删除标记。
            if (r.hasExpiredDeleteMarker()) {
                System.out.println("rule expired delete marker: " + r.getExpiredDeleteMarker());
            }

            // 查看非当前版本Object存储类型转换规则。
            if (r.hasNoncurrentVersionStorageTransitions()) {
                for (LifecycleRule.NoncurrentVersionStorageTransition transition : r.getNoncurrentVersionStorageTransitions()) {
                    System.out.println("rule noncurrent versions trans days:" + transition.getNoncurrentDays() +
                            " trans storage class: " + transition.getStorageClass());
                }
            }

            // 查看非当前版本Object过期规则。
            if (r.hasNoncurrentVersionExpiration()) {
                System.out.println("rule noncurrent versions expiration days:" + r.getNoncurrentVersionExpiration().getNoncurrentDays());
            }
        }

    }

    @Test
    public void getBucketRequestPayment() {

        System.out.println(bucketApi.getBucketRequestPayment("mrj123456mrj0").toString());

    }

    @Test
    public void setBucketReferer() {
        System.out.println(bucketApi.setBucketReferer("mrj123456mrj0", CollectionUtil.newArrayList()));
    }

    @Test
    public void getBucketReferer() {
        System.out.println(bucketApi.getBucketReferer("mrj123456mrj0"));
    }

    @Test
    public void deleteBucketReferer() {
        System.out.println(bucketApi.deleteBucketReferer("mrj123456mrj0"));
    }

    @Test
    public void setBucketLogging() {
        System.out.println(bucketApi.setBucketLogging("mrj123456mrj0", "mrj123456mrjlogs1", "logs"));
    }

    @Test
    public void getBucketLogging() {
        System.out.println(JSON.toJSONString(bucketApi.getBucketLogging("mrj123456mrj0")));
    }

    @Test
    public void getBucketReplication() {

        List<ReplicationRule> ruleList = bucketApi.getBucketReplication("mrj123456mrj0");

        for (ReplicationRule rule : ruleList) {
            System.out.println(rule.getReplicationRuleID());
            System.out.println(rule.getTargetBucketLocation());
            System.out.println(rule.getTargetBucketName());
        }

    }

    @Test
    public void getBucketReplicationLocation() {
        List<String> locations = bucketApi.getBucketReplicationLocation("mrj123456mrj0");
        for (String loc : locations) {
            System.out.println(loc);
        }
    }

    @Test
    public void getBucketCORSRules() {
        List<SetBucketCORSRequest.CORSRule> corsRules = bucketApi.getBucketCORSRules("mrj123456mrj0");
        for (SetBucketCORSRequest.CORSRule rule : corsRules) {
            for (String allowedOrigin1 : rule.getAllowedOrigins()) {
                // 获取允许的跨域请求源。
                System.out.println(allowedOrigin1);
            }

            for (String allowedMethod1 : rule.getAllowedMethods()) {
                // 获取允许的跨域请求方法。
                System.out.println(allowedMethod1);
            }

            if (rule.getAllowedHeaders().size() > 0){
                for (String allowedHeader1 : rule.getAllowedHeaders()) {
                    // 获取允许的头部列表。
                    System.out.println(allowedHeader1);
                }
            }

            if (rule.getExposeHeaders().size() > 0) {
                for (String exposeHeader : rule.getExposeHeaders()) {
                    // 获取允许的头部。
                    System.out.println(exposeHeader);
                }
            }

            if ( null != rule.getMaxAgeSeconds()) {
                System.out.println(rule.getMaxAgeSeconds());
            }
        }
    }

    @Test
    public void deleteBucketCORSRules() {
        System.out.println(bucketApi.deleteBucketCORSRules("mrj123456mrj0"));
    }

    @Test
    public void getBucketVersioning() {

        System.out.println(bucketApi.getBucketVersioning("mrj123456mrj0"));

    }










}
