# aliyun-oss-spring-boot-starter
阿里云 OSS 对象存储 Java SDK 封装, 并制作成Spring-boot starter


## 版本说明

1. 升级aliyun-sdk-oss 版本为：3.10.2
2. 对OSS SDK 功能完整封装
3. 修改starter 启动方式

## 使用方式

1. 下载源码

下载源码 并install引入使用

2. 引入依赖

```xml
<dependency>
    <groupId>cn.darkjrong</groupId>
    <artifactId>aliyun-oss-spring-boot-starter</artifactId>
    <version>2.0</version>
</dependency>
```

3. 配置参数(application.properties)  yml配置

```yaml
  aliyun-oss:
  # 外网域名
  endpoint: http://oss-cn-shenzhen.aliyuncs.com
  # ak
  accessKeyId: 
  # aks
  accessKeySecret: 
  # 内网地址
  intranet: http://oss-cn-shenzhen.aliyuncs.com
  # 是否使用内外模式上传， 开启：true, 关闭：false
  openIntranet: true
```
4. API 注入
```java
    
    // 存储空间 API
    @Autowired
    private BucketApi bucketApi;

    // 文件操作 API
    @Autowired
    private FileOperationsApi fileOperationsApi;

    // 图片处理API
    @Autowired
    private ImageProcessingApi imageProcessingApi;

    // 签名URL API
    @Autowired
    private PresignedUrlApi presignedUrlApi;

```

5. 进度回调
```java
/**
 * 进度回调接口 实现
 * @author Rong.Jia
 * @date 2021/02/23 21:17
 */
@Component
public class ProgressCallBackImpl implements ProgressCallBack {

    private static final Logger logger = LoggerFactory.getLogger(ProgressCallBackImpl.class);

    /**
     *
     * @param objectName 正在上传的文件名
     * @param totalBytes 总大小
     * @param completeBytes 已完成大小
     * @param succeed 是否上传/下载成功， true:成功，false：失败
     */
    @Override
    public void progress(String objectName, Long totalBytes, Long completeBytes, Boolean succeed) {

    }
}
```


