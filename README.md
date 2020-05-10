# aliyun-oss-spring-boot-starter
阿里云 OSS 对象存储 Java SDK 封装, 并制作成Spring-boot starter

#使用方式
自己下载install引入使用

```
<dependency>
    <groupId>cn.darkjrong</groupId>
    <artifactId>aliyun-oss-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

3. 配置参数(application.properties)  yml配置，必须配置enabled: true，否则默认false不起作用

```properties
    aliyun.oss.endpoint=
    aliyun.oss.accessKeyId=
    aliyun.oss.enabled=
    aliyun.oss.accessKeySecret=
    aliyun.oss.intranet=
    aliyun.oss.openIntranet=
```

> openIntranet :     1: open，close: 0

4. 代码使用
```java
    @Autowired
    private OssClient ossClient;
```
