package cn.darkjrong.oss.api;

/**
 * 签名URL API
 * @author Rong.Jia
 * @date 2021/02/23 22:13
 */
public interface PresignedUrlApi {

    /**
     * 获得url链接
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @return url链接
     */
    String getUrl(String bucketName, String objectName);

    /**
     * 获得url链接
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒
     * @return url链接
     */
    String getUrl(String bucketName, String objectName, Long expirationTime);

    /**
     * 获得url链接
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param style 样式  仅针对图片, 视频
     * @return url链接
     */
    String getUrl(String bucketName, String objectName, String style);

    /**
     * 获得url链接
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param style 样式  仅针对图片, 视频
     * @param expirationTime URL链接有效时间 单位毫秒
     * @return url链接
     */
    String getUrl(String bucketName, String objectName, Long expirationTime, String style);



}
