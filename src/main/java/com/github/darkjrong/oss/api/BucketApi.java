package com.github.darkjrong.oss.api;

import com.github.darkjrong.oss.common.exception.AliyunOSSClientException;
import com.github.darkjrong.oss.common.pojo.dto.BucketPolicyDTO;
import com.github.darkjrong.oss.common.pojo.vo.BucketPolicyVO;
import com.aliyun.oss.model.*;

import java.util.List;
import java.util.Map;

/**
 *  存储空间 API
 * @author Rong.Jia
 * @date 2021/02/20 18:13
 */
public interface BucketApi {

    /**
     * 创建存储空间
     * @param bucketName 存储空间名
     * @return 存储空间信息
     * @throws AliyunOSSClientException 操作异常
     */
    Bucket createBucket(String bucketName) throws AliyunOSSClientException;

    /**
     * 创建存储空间
     * @param bucketName 存储空间名
     * @param dataRedundancyType 数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。
     *                           如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
     * @param storageClass 存储空间的存储类型
     * @param cannedAccessControlList 存储空间的权限
     * @return 存储空间信息
     * @throws AliyunOSSClientException 操作异常
     */
    Bucket createBucket(String bucketName, StorageClass storageClass,
                        DataRedundancyType dataRedundancyType,
                        CannedAccessControlList cannedAccessControlList) throws AliyunOSSClientException;

    /**
     * 查询存储空间列表
     * @param bucketPrefix 前缀
     * @param flag 是否以前缀查询，false:"bucketPrefix" 无效
     * @return 存储空间列表
     * @throws AliyunOSSClientException 操作异常
     */
    List<Bucket> getBuckets(String bucketPrefix, Boolean flag) throws AliyunOSSClientException;

    /**
     * 是否存在指定存储空间
     * @param bucketName 存储空间名
     * @return true: 存在，false: 不存在
     */
    boolean doesBucketExist(String bucketName);

    /**
     * 获取存储空间的地域
     * @param bucketName  存储空间
     * @return 地域
     * @throws AliyunOSSClientException 操作异常
     */
    String getBucketLocation(String bucketName) throws AliyunOSSClientException;

    /**
     * 获取存储空间的信息
     * @param bucketName 存储空间
     * @return  存储空间的信息
     * @throws AliyunOSSClientException 操作异常
     */
    BucketInfo getBucketInfo(String bucketName) throws AliyunOSSClientException;

    /**
     * 设置存储空间访问权限
     * @param bucketName  存储空间
     * @param cannedAccessControlList 访问权限
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketAcl(String bucketName, CannedAccessControlList cannedAccessControlList);

    /**
     * 获取存储空间的访问权限
     * @param bucketName 存储空间
     * @return  存储空间的访问权限
     * @throws AliyunOSSClientException 操作异常
     */
    AccessControlList getBucketAcl(String bucketName) throws AliyunOSSClientException;

    /**
     * 删除存储空间
     * @param bucketName  存储空间
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean deleteBucket(String bucketName);

    /**
     * 设置Bucket标签
     * @param bucketName  存储空间
     * @param tags 标签信息
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketTagging(String bucketName, Map<String, String> tags);

    /**
     * 获取Bucket标签
     * @param bucketName 存储空间
     * @return  Bucket标签
     * @throws AliyunOSSClientException 操作异常
     */
    Map<String, String> getBucketTagging(String bucketName) throws AliyunOSSClientException;

    /**
     * 列举带指定标签的Bucket
     * @param tagKey 标签key
     * @param tagValue 标签value
     * @return  Bucket信息
     * @throws AliyunOSSClientException 操作异常
     */
    List<Bucket> getBuckets(String tagKey, String tagValue) throws AliyunOSSClientException;

    /**
     * 查询Bucket
     * @param listBucketsRequest 查询条件
     * @return  Bucket信息
     * @throws AliyunOSSClientException 操作异常
     */
    List<Bucket> getBuckets(ListBucketsRequest listBucketsRequest) throws AliyunOSSClientException;

    /**
     * 删除Bucket标签
     * @param bucketName  存储空间
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean deleteBucketTagging(String bucketName);

    /**
     * 设置Bucket 授权策略
     * @param bucketName  存储空间
     * @param bucketPolicyDTO 授权策略
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketPolicy(String bucketName, BucketPolicyDTO bucketPolicyDTO);

    /**
     * 获取Bucket 授权策略信息
     * @param bucketName  存储空间
     * @return 授权策略信息 不存在则返回 null
     */
    BucketPolicyVO getBucketPolicy(String bucketName);

    /**
     * 删除Bucket 授权策略
     * @param bucketName  存储空间
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean deleteBucketPolicy(String bucketName);

    /**
     * 添加清单配置
     * @param bucketName 存储空间
     * @param inventoryConfiguration 清单配置新消息
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketInventoryConfiguration(String bucketName, InventoryConfiguration inventoryConfiguration);

    /**
     * 获取清单配置信息
     * @param bucketName 存储空间
     * @param inventoryId 清单配置ID
     * @return 清单配置信息 没有配置则返回 null
     */
    GetBucketInventoryConfigurationResult getBucketInventoryConfiguration(String bucketName, String inventoryId);

    /**
     * 删除清单配置信息
     * @param bucketName 存储空间
     * @param inventoryId 清单配置ID
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean deleteBucketInventoryConfiguration(String bucketName, String inventoryId);

    /**
     * 设置生命周期 距最后修改时间 指定天数后过期
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param matchTags 标签
     * @param day 天数（多少天后过期）
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId, String matchPrefix,
                               Map<String, String> matchTags,
                               Integer day, boolean expiredDeleteMarker);

    /**
     * 设置生命周期 指定日期之前创建的文件过期
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param matchTags 标签
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @param time 时间，单位：毫秒
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId, String matchPrefix,
                               Map<String, String> matchTags, Long time, boolean expiredDeleteMarker);

    /**
     * 设置生命周期 设置分片指定 天 后过期
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param day 天数（多少天后过期）
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId,
                               String matchPrefix, Integer day, boolean expiredDeleteMarker);

    /**
     * 设置生命周期 设置指定日期之前的分片过期
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param time 时间  单位：毫秒
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId,
                               String matchPrefix, Long time, boolean expiredDeleteMarker);

    /**
     * 设置生命周期 设置存储类型
     *
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @param expirationDays 天数   多少天后进行转换存储类型
     * @param storageClass 存储类型
     *                      Standard("Standard")：标准
     *                      IA("IA"): 低频访问
     *                      Archive("Archive"): 归档
     *                      ColdArchive("ColdArchive"): 冷归档
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId,
                               String matchPrefix, Integer expirationDays,
                               StorageClass storageClass, boolean expiredDeleteMarker);

    /**
     * 设置生命周期 修改指定日期前的文件的存储类型
     *
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param time 日期， 单位：毫秒
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @param storageClass 存储类型
     *                      Standard("Standard")：标准
     *                      IA("IA"): 低频访问
     *                      Archive("Archive"): 归档
     *                      ColdArchive("ColdArchive"): 冷归档
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId,
                               String matchPrefix, Long time,
                               StorageClass storageClass, boolean expiredDeleteMarker);

    /**
     * 设置生命周期 对非当前版本的 修改指定日期前的文件的存储类型
     *
     * @param bucketName 存储空间
     * @param ruleId 规则ID
     * @param matchPrefix 文件匹配前缀
     * @param storageClassDays 天数， 多少天后转换非当前版本的存储类型的时间
     * @param deleteDays 天数， 多少天后删除非当前版本, 为空则不删除
     * @param expiredDeleteMarker 是否自动移除过期删除标记
     * @param storageClass 存储类型
     *                      Standard("Standard")：标准
     *                      IA("IA"): 低频访问
     *                      Archive("Archive"): 归档
     *                      ColdArchive("ColdArchive"): 冷归档
     * @return 是否成功， true: 成功，false: 不成功
     */
    boolean setBucketLifecycle(String bucketName, String ruleId, String matchPrefix,
                               Integer storageClassDays, Integer deleteDays, StorageClass storageClass, boolean expiredDeleteMarker);

    /**
     * 查询存储空间的生命周期规则
     * @param bucketName 存储空间
     * @return 生命周期规则信息 不存在/未设置  则返回 null
     */
    List<LifecycleRule> getBucketLifecycle(String bucketName);

    /**
     * 删除存储空间的生命周期规则
     * @param bucketName 存储空间
     * @return 是否成功 true：成功， false: 失败
     */
    boolean deleteBucketLifecycle(String bucketName);

    /**
     * 设置请求者付费模式
     * @param payer 付费模式
     * @param bucketName 存储空间
     * @return 是否成功 true：成功， false: 失败
     */
    boolean setBucketRequestPayment(String bucketName, Payer payer);

    /**
     * 获取请求者付费模式配置
     * @param bucketName 存储空间
     * @return 配置信息
     * @throws AliyunOSSClientException 操作异常
     */
    Payer getBucketRequestPayment(String bucketName) throws AliyunOSSClientException;

    /**
     * 设置防盗链
     * @param bucketName 存储空间
     * @param refererList 添加Referer白名单。Referer参数支持通配符星号（*）和问号（？）
     * @return  是否成功 true：成功， false: 失败
     */
    boolean setBucketReferer(String bucketName, List<String> refererList);

    /**
     * 获取防盗链信息
     * @param bucketName 存储空间
     * @return  存储空间Referer白名单列表
     * @throws AliyunOSSClientException 操作异常
     */
    List<String> getBucketReferer(String bucketName) throws AliyunOSSClientException;

    /**
     * 清空防盗链
     * @param bucketName 存储空间
     * @return  是否成功 true：成功， false: 失败
     */
    boolean deleteBucketReferer(String bucketName);

    /**
     * 开启访问日志记录
     * @param bucketName 存储空间
     * @param logBucketName  存放日志文件的存储空间
     * @param logDir 日志文件存放的目录
     * @return 是否成功 true：成功， false: 失败
     */
    boolean setBucketLogging(String bucketName, String logBucketName, String logDir);

    /**
     * 查看访问日志设置
     * @param bucketName 存储空间
     * @return 日志设置信息
     * @throws AliyunOSSClientException 操作异常
     */
    BucketLoggingResult getBucketLogging(String bucketName) throws AliyunOSSClientException;

    /**
     * 关闭访问日志记录
     * @param bucketName 存储空间
     * @return 是否成功 true：成功， false: 失败
     */
    boolean closeBucketLogging(String bucketName);

    /**
     * 设置静态网站托管
     * @param bucketName 存储空间
     * @param indexDocument 网页
     * @param errorDocument 错误网页
     * @return 是否成功 true：成功， false: 失败
     */
    boolean setBucketWebsite(String bucketName, String indexDocument, String errorDocument);

    /**
     * 查看静态网站托管配置
     * @param bucketName 存储空间
     * @return 配置信息
     * @throws AliyunOSSClientException 操作异常
     */
    BucketWebsiteResult getBucketWebsite(String bucketName) throws AliyunOSSClientException;

    /**
     * 删除静态网站托管配置
     * @param bucketName 存储空间
     * @return 是否成功 true：成功， false: 失败
     */
    boolean deleteBucketWebsite(String bucketName);

    /**
     * 开启跨区域复制
     * @param sourceBucketName 原存储空间
     * @param replicationRuleId 操作ID
     * @param targetBucketName 目标存储空间
     * @param targetBucketLocation 目标Endpoint， 如：oss-cn-beijing
     * @param enableHistoricalObjectReplication 是否禁止同步历史数据。默认会同步历史数据
     * @return 是否成功 true：成功， false: 失败
     */
    boolean enableBucketReplication(String sourceBucketName, String replicationRuleId,
                                    String targetBucketName, String targetBucketLocation,
                                    boolean enableHistoricalObjectReplication);

    /**
     * 查看跨区域复制
     * @param bucketName 存储空间
     * @return 跨区域复制信息
     * @throws AliyunOSSClientException 操作异常(未配置)
     */
    List<ReplicationRule> getBucketReplication(String bucketName) throws AliyunOSSClientException;

    /**
     * 查看跨区域复制进度
     * @param bucketName 存储空间
     * @param replicationRuleId 操作ID
     * @return 进度信息
     * @throws AliyunOSSClientException 操作异常(未配置)
     */
    BucketReplicationProgress getBucketReplicationProgress(String bucketName, String replicationRuleId) throws AliyunOSSClientException;

    /**
     * 查看可同步的目标地域
     * @param bucketName 存储空间
     * @return 目标地域信息, 没有则返回Null
     */
    List<String> getBucketReplicationLocation(String bucketName);

    /**
     * 关闭跨区域复制
     * @param bucketName 存储空间
     * @param replicationRuleId 操作ID
     * @return 是否成功 true：成功， false: 失败
     */
    boolean closeBucketReplication(String bucketName, String replicationRuleId);

    /**
     * 设置跨域资源共享规则, 每个存储空间最多允许10条规则
     * @param bucketName  存储空间
     * @param allowedOrigin 允许跨域请求的来源
     * @param allowedMethod 允许的跨域请求方法(GET/PUT/DELETE/POST/HEAD)
     * @param allowedHeader 是否允许预取指令（OPTIONS）中Access-Control-Request-Headers头中指定的Header
     *                      AllowedOrigins和AllowedMethods最多支持一个星号（*）通配符。星号（*）表示允许所有的域来源或者操作
     * @param exposedHeader 允许用户从应用程序中访问的响应头
     *                      AllowedOrigins和AllowedMethods最多支持一个星号（*）通配符。星号（*）表示允许所有的域来源或者操作
     * @param maxAgeSeconds 浏览器对特定资源的预取（OPTIONS）请求返回结果的缓存时间，单位为秒
     * @return 是否成功 true：成功， false: 失败
     */
    boolean setBucketCORS(String bucketName, List<String> allowedOrigin,
                          List<String> allowedMethod, List<String> allowedHeader,
                          List<String> exposedHeader, Integer maxAgeSeconds);

    /**
     *  获取跨域资源共享规则
     * @param bucketName  存储空间
     * @return 共享规则
     * @throws AliyunOSSClientException CORS未配置异常
     */
    List<SetBucketCORSRequest.CORSRule> getBucketCORSRules(String bucketName) throws AliyunOSSClientException;

    /**
     * 删除跨域资源共享规则
     * @param bucketName  存储空间
     * @return  是否成功 true：成功， false: 失败
     */
    boolean deleteBucketCORSRules(String bucketName);

    /**
     * 设置Bucket版本控制
     * @param bucketName  存储空间
     * @return  是否成功 true：成功， false: 失败
     */
    boolean setBucketVersioning(String bucketName);

    /**
     * 获取Bucket版本控制状态
     * @param bucketName 存储空间
     * @return 版本控制状态信息
     */
    String getBucketVersioning(String bucketName);

    /**
     * 配置Bucket加密
     * @param bucketName 存储空间
     * @param kmsMasterKeyId OSS托管的CMK加密ID
     * @return 是否成功，true: 成功，false: 失败
     */
    boolean setBucketEncryption(String bucketName, String kmsMasterKeyId);

    /**
     * 配置Bucket加密
     * @param bucketName 存储空间
     * @param sseAlgorithm 加密协议
     * @param kmsMasterKeyId OSS托管的CMK加密ID, SSEAlgorithm为KMS 有效
     * @return 是否成功，true: 成功，false: 失败
     */
    boolean setBucketEncryption(String bucketName, SSEAlgorithm sseAlgorithm, String kmsMasterKeyId);






















}
