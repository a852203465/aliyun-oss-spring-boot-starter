package cn.darkjrong.oss.api;

import cn.darkjrong.oss.common.enums.CompressedFormatEnum;
import cn.darkjrong.oss.common.enums.ImageFormatEnum;
import cn.darkjrong.oss.common.exception.AliyunOSSClientException;
import cn.darkjrong.oss.common.pojo.dto.CropDTO;
import cn.darkjrong.oss.common.pojo.dto.QualityDTO;
import cn.darkjrong.oss.common.pojo.dto.ResizeDTO;
import cn.darkjrong.oss.common.pojo.dto.WatermarkDTO;
import cn.darkjrong.oss.common.pojo.vo.ImageInfoVO;

import java.io.File;

/**
 *  图片处理API 接口
 *
 *  使用图片处理服务时有如下限制：
 * 原图限制
 * 图片格式只支持JPG、PNG、BMP、GIF、WebP、TIFF。
 * 原图大小不能超过20 MB。
 * 除图片旋转对应的原图高或者宽不能超过4,096 px外，其他图片操作对应的原图高或者宽不能超过30,000 px，且总像素不能超过2.5亿 px。
 * 动态图片（例如GIF图片）的像素计算方式为宽*高*图片帧数；非动态图片（例如PNG图片）的像素计算方式为宽*高。
 *
 * 缩放后图片限制
 * 宽或高不能超过16,384 px，且总像素不能超过16,777,216 px。
 *
 * 样式限制
 * 每个存储空间下最多能创建50个样式
 *
 *
 * @author Rong.Jia
 * @date 2021/02/22 19:25
 */
public interface ImageProcessingApi {

    /**
     * 图片缩放
     *
     * <p>
     *  原图限制：
     * 图片格式只能是：JPG、PNG、BMP、GIF、WebP、TIFF。其中GIF格式的图片支持指定宽高缩放，不支持等比缩放（等比缩放情况下，动态图会变成静态图）。
     * 原图大小不能超过20 MB。
     * 宽或高不能超过30,000 px，且总像素不能超过2.5亿 px。
     * 动态图片（例如GIF图片）的像素计算方式为宽*高*图片帧数；非动态图片（例如PNG图片）的像素计算方式为宽*高。
     *
     * 缩放图限制：宽或高不能超过16,384 px，且总像素不能超过16,777,216 px。
     * 若缩放时只指定宽度或者高度：
     * 等比缩放时，会按比例缩放图片。例如原图为200 px*100 px，将高缩放为100 px，则宽缩放为50 px。
     * 固定宽高缩放时，会将原图宽高按照指定值进行缩放。例如原图为200 px*100 px，将高缩放为100 px，则宽也缩放为100 px。
     * 目标缩放图比原图尺寸大时，默认返回原图。您可以增加limit_0参数放大图片。
     * 例如：https://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/resize,w_500,limit_0
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param desFile 本地保存文件
     * @param resizeDTO 缩放参数
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean resize(String bucketName, String objectName, ResizeDTO resizeDTO, File desFile);

    /**
     * 压缩文件
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param compressedFormatEnum 压缩格式
     * @param desFile 本地目标文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum, File desFile);

    /**
     * 图片水印
     *
     * <p>
     *     水印图片只能使用当前存储空间内的图片，网络或本地图片需上传至当前存储空间内方可使用
     *     水印图片目前仅支持JPG、PNG、BMP、GIF、WebP、TIFF格式
     *     文字水印暂不支持繁体中文。
     *      在为图片添加多个水印时，需要注意以下内容：
     *          一张图片上，最多支持3张不同的水印图片做水印。
     *          同一张水印图片可以重复使用，但必须放在不同位置。
     *          各个水印位置不能完全重叠
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param desFile 本地保存文件
     * @param watermarkDTO 水印参数
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO, File desFile);

    /**
     * 裁剪
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param cropDTO 裁剪参数
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean crop(String bucketName, String objectName, CropDTO cropDTO, File desFile);

    /**
     * 质量变换
     *
     * <p>
     *     质量变换仅支持JPG和WebP
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param desFile 本地保存文件
     * @param qualityDTO 变换参数
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean quality(String bucketName, String objectName, QualityDTO qualityDTO, File desFile);

    /**
     * 格式转换
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param desFile 本地保存文件
     * @param imageFormatEnum 图片格式
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum, File desFile);

    /**
     *  获取图片信息
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @return 图片信息
     * @throws AliyunOSSClientException 操作异常
     */
    ImageInfoVO info(String bucketName, String objectName) throws AliyunOSSClientException;

    /**
     * 自适应方向
     *
     * <p>
     *     如果原图没有旋转参数（Orientation），添加auto-orient操作不会对图片进行旋转
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 是否进行自适应旋转
     *              0：保持原图方向，不进行自适应旋转。
     *              1：将图片进行自适应旋转
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean autoOrient(String bucketName, String objectName, Integer value, File desFile);

    /**
     * 内切圆
     *
     * <p>
     *
     *     如果图片的最终格式是PNG、WebP或BMP等支持透明通道的图片，那么图片非圆形区域的部分将会以透明填充。
     *     如果图片的最终格式是JPG，那么非圆形区域是以白色进行填充。推荐保存成PNG格式。
     *     当value取值大于原图最小边的一半时，以原图最小边的一半为值返回内切圆（即r=原图最小边÷2）
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 内切圆的半径 [1,4096]
     * @param desFile 本地保存文件
     * @return  是否成功 true: 成功，false: 失败
     */
    boolean circle(String bucketName, String objectName, Integer value, File desFile);

    /**
     *索引切割
     *
     * <p>
     *     如果指定的索引值大于切割后形成的区域数量，将返回原图。
     *      当x和y同时指定且值合法时，以y参数的值为准
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param x  指定在x轴切割出的每块区域的长度。x参数与y参数只能任选其一  [1,图片宽度]
     * @param y  指定在y轴切割出的每块区域的长度。x参数与y参数只能任选其一 [1,图片高度]
     * @param i  选择切割后返回的图片区域 	[0,区域数) 默认为0，表示第一块
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i, File desFile);

    /**
     *
     * 圆角矩形
     *
     * <p>
     *     如果图片的最终格式是PNG、WebP、BMP等支持透明通道的图片，那么图片圆角外的区域将会以透明填充。
     *     如果图片的最终格式是JPG，那么图片圆角外的区域以白色进行填充。推荐保存成PNG格式。
     *      如果指定圆角的半径大于原图最大内切圆的半径，则按照图片最大内切圆的半径设置圆角（即r=原图最小边÷2）
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param radius 将图片切出圆角，指定圆角的半径。	[1,4096]
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean roundedCorners(String bucketName, String objectName, Integer radius, File desFile);

    /**
     * 模糊效果
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param radius  模糊半径 [1,50] 该值越大，图片越模糊
     * @param deviation 正态分布的标准差 [1,50] 该值越大，图片越模糊
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean blur(String bucketName, String objectName, Integer radius, Integer deviation, File desFile);

    /**
     *
     * 旋转
     *
     * <p>
     *
     *     若图片旋转的角度不是90°、180°、270°、360°时，会导致处理后的图片尺寸变大。
     *      旋转功能对图片的尺寸有限制，图片的宽或者高不能超过4096 px
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 顺时针旋转的角度 [0,360] 默认值：0，表示不旋转
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean rotate(String bucketName, String objectName, Integer value, File desFile);

    /**
     * 渐进显示
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 是否设置图片为渐进显示
     *              1：表示将原图设置成渐进显示。
     *              0：表示将原图设置成标准显示
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean interlace(String bucketName, String objectName, Integer value, File desFile);

    /**
     * 亮度
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 图片的亮度  [-100, 100]
     *              取值＜0：降低图片亮度。
     *              取值=0：不调整图片亮度。
     *              取值＞0：提高图片亮度
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean bright(String bucketName, String objectName, Integer value, File desFile);

    /**
     * 锐化
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 锐化效果的强度  [50,399]
     *             取值越大，图片越清晰，但过大的值可能会导致图片失真。
     *              为达到较优效果，推荐取值为100
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean sharpen(String bucketName, String objectName, Integer value, File desFile);

    /**
     * 对比度
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 图片的对比度  [-100,100]
     *             取值＜0：降低图片对比度。
     *              取值=0：维持原图对比度。
     *              取值＞0：提高图片对比度
     * @param desFile 本地保存文件
     * @return 是否成功 true: 成功，false: 失败
     */
    boolean contrast(String bucketName, String objectName, Integer value, File desFile);

    /**
     * 图片缩放
     *
     * <p>
     *  原图限制：
     * 图片格式只能是：JPG、PNG、BMP、GIF、WebP、TIFF。其中GIF格式的图片支持指定宽高缩放，不支持等比缩放（等比缩放情况下，动态图会变成静态图）。
     * 原图大小不能超过20 MB。
     * 宽或高不能超过30,000 px，且总像素不能超过2.5亿 px。
     * 动态图片（例如GIF图片）的像素计算方式为宽*高*图片帧数；非动态图片（例如PNG图片）的像素计算方式为宽*高。
     *
     * 缩放图限制：宽或高不能超过16,384 px，且总像素不能超过16,777,216 px。
     * 若缩放时只指定宽度或者高度：
     * 等比缩放时，会按比例缩放图片。例如原图为200 px*100 px，将高缩放为100 px，则宽缩放为50 px。
     * 固定宽高缩放时，会将原图宽高按照指定值进行缩放。例如原图为200 px*100 px，将高缩放为100 px，则宽也缩放为100 px。
     * 目标缩放图比原图尺寸大时，默认返回原图。您可以增加limit_0参数放大图片。
     * 例如：https://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/resize,w_500,limit_0
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @param resizeDTO 缩放参数
     * @return 图片URL
     */
    String resize(String bucketName, String objectName, ResizeDTO resizeDTO, Long expirationTime);

    /**
     * 压缩文件
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param compressedFormatEnum 压缩格式
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum, Long expirationTime);

    /**
     * 图片水印
     *
     * <p>
     *     水印图片只能使用当前存储空间内的图片，网络或本地图片需上传至当前存储空间内方可使用
     *     水印图片目前仅支持JPG、PNG、BMP、GIF、WebP、TIFF格式
     *     文字水印暂不支持繁体中文。
     *      在为图片添加多个水印时，需要注意以下内容：
     *          一张图片上，最多支持3张不同的水印图片做水印。
     *          同一张水印图片可以重复使用，但必须放在不同位置。
     *          各个水印位置不能完全重叠
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @param watermarkDTO 水印参数
     * @return 图片URL
     */
    String watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO, Long expirationTime);

    /**
     * 裁剪
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param cropDTO 裁剪参数
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String crop(String bucketName, String objectName, CropDTO cropDTO, Long expirationTime);

    /**
     * 质量变换
     *
     * <p>
     *     质量变换仅支持JPG和WebP
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @param qualityDTO 变换参数
     * @return 图片URL
     */
    String quality(String bucketName, String objectName, QualityDTO qualityDTO, Long expirationTime);

    /**
     * 格式转换
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @param imageFormatEnum 图片格式
     * @return 图片URL
     */
    String format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum, Long expirationTime);

    /**
     * 自适应方向
     *
     * <p>
     *     如果原图没有旋转参数（Orientation），添加auto-orient操作不会对图片进行旋转
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 是否进行自适应旋转
     *              0：保持原图方向，不进行自适应旋转。
     *              1：将图片进行自适应旋转
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String autoOrient(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     * 内切圆
     *
     * <p>
     *
     *     如果图片的最终格式是PNG、WebP或BMP等支持透明通道的图片，那么图片非圆形区域的部分将会以透明填充。
     *     如果图片的最终格式是JPG，那么非圆形区域是以白色进行填充。推荐保存成PNG格式。
     *     当value取值大于原图最小边的一半时，以原图最小边的一半为值返回内切圆（即r=原图最小边÷2）
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 内切圆的半径 [1,4096]
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return  图片URL
     */
    String circle(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     *索引切割
     *
     * <p>
     *     如果指定的索引值大于切割后形成的区域数量，将返回原图。
     *      当x和y同时指定且值合法时，以y参数的值为准
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param x  指定在x轴切割出的每块区域的长度。x参数与y参数只能任选其一  [1,图片宽度]
     * @param y  指定在y轴切割出的每块区域的长度。x参数与y参数只能任选其一 [1,图片高度]
     * @param i  选择切割后返回的图片区域 	[0,区域数) 默认为0，表示第一块
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i, Long expirationTime);

    /**
     *
     * 圆角矩形
     *
     * <p>
     *     如果图片的最终格式是PNG、WebP、BMP等支持透明通道的图片，那么图片圆角外的区域将会以透明填充。
     *     如果图片的最终格式是JPG，那么图片圆角外的区域以白色进行填充。推荐保存成PNG格式。
     *      如果指定圆角的半径大于原图最大内切圆的半径，则按照图片最大内切圆的半径设置圆角（即r=原图最小边÷2）
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param radius 将图片切出圆角，指定圆角的半径。	[1,4096]
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String roundedCorners(String bucketName, String objectName, Integer radius, Long expirationTime);

    /**
     * 模糊效果
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param radius  模糊半径 [1,50] 该值越大，图片越模糊
     * @param deviation 正态分布的标准差 [1,50] 该值越大，图片越模糊
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String blur(String bucketName, String objectName, Integer radius, Integer deviation, Long expirationTime);

    /**
     *
     * 旋转
     *
     * <p>
     *
     *     若图片旋转的角度不是90°、180°、270°、360°时，会导致处理后的图片尺寸变大。
     *      旋转功能对图片的尺寸有限制，图片的宽或者高不能超过4096 px
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 顺时针旋转的角度 [0,360] 默认值：0，表示不旋转
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String rotate(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     * 渐进显示
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 是否设置图片为渐进显示
     *              1：表示将原图设置成渐进显示。
     *              0：表示将原图设置成标准显示
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String interlace(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     * 亮度
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 图片的亮度  [-100, 100]
     *              取值＜0：降低图片亮度。
     *              取值=0：不调整图片亮度。
     *              取值＞0：提高图片亮度
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String bright(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     * 锐化
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 锐化效果的强度  [50,399]
     *             取值越大，图片越清晰，但过大的值可能会导致图片失真。
     *              为达到较优效果，推荐取值为100
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String sharpen(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     * 对比度
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 图片的对比度  [-100,100]
     *             取值＜0：降低图片对比度。
     *              取值=0：维持原图对比度。
     *              取值＞0：提高图片对比度
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String contrast(String bucketName, String objectName, Integer value, Long expirationTime);

    /**
     * 获取图片主色调
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param expirationTime URL链接有效时间 单位毫秒  默认：7200L * 1000
     * @return 图片URL
     */
    String averageHue(String bucketName, String objectName, Long expirationTime);

    /**
     * 图片缩放
     *
     * <p>
     *  原图限制：
     * 图片格式只能是：JPG、PNG、BMP、GIF、WebP、TIFF。其中GIF格式的图片支持指定宽高缩放，不支持等比缩放（等比缩放情况下，动态图会变成静态图）。
     * 原图大小不能超过20 MB。
     * 宽或高不能超过30,000 px，且总像素不能超过2.5亿 px。
     * 动态图片（例如GIF图片）的像素计算方式为宽*高*图片帧数；非动态图片（例如PNG图片）的像素计算方式为宽*高。
     *
     * 缩放图限制：宽或高不能超过16,384 px，且总像素不能超过16,777,216 px。
     * 若缩放时只指定宽度或者高度：
     * 等比缩放时，会按比例缩放图片。例如原图为200 px*100 px，将高缩放为100 px，则宽缩放为50 px。
     * 固定宽高缩放时，会将原图宽高按照指定值进行缩放。例如原图为200 px*100 px，将高缩放为100 px，则宽也缩放为100 px。
     * 目标缩放图比原图尺寸大时，默认返回原图。您可以增加limit_0参数放大图片。
     * 例如：https://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/resize,w_500,limit_0
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param resizeDTO 缩放参数
     * @return 图片URL
     */
    String resize(String bucketName, String objectName, ResizeDTO resizeDTO);

    /**
     * 压缩文件
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param compressedFormatEnum 压缩格式
     * @return 图片URL
     */
    String compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum);

    /**
     * 图片水印
     *
     * <p>
     *     水印图片只能使用当前存储空间内的图片，网络或本地图片需上传至当前存储空间内方可使用
     *     水印图片目前仅支持JPG、PNG、BMP、GIF、WebP、TIFF格式
     *     文字水印暂不支持繁体中文。
     *      在为图片添加多个水印时，需要注意以下内容：
     *          一张图片上，最多支持3张不同的水印图片做水印。
     *          同一张水印图片可以重复使用，但必须放在不同位置。
     *          各个水印位置不能完全重叠
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param watermarkDTO 水印参数
     * @return 图片URL
     */
    String watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO);

    /**
     * 裁剪
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param cropDTO 裁剪参数
     * @return 图片URL
     */
    String crop(String bucketName, String objectName, CropDTO cropDTO);

    /**
     * 质量变换
     *
     * <p>
     *     质量变换仅支持JPG和WebP
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param qualityDTO 变换参数
     * @return 图片URL
     */
    String quality(String bucketName, String objectName, QualityDTO qualityDTO);

    /**
     * 格式转换
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param imageFormatEnum 图片格式
     * @return 图片URL
     */
    String format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum);

    /**
     * 自适应方向
     *
     * <p>
     *     如果原图没有旋转参数（Orientation），添加auto-orient操作不会对图片进行旋转
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 是否进行自适应旋转
     *              0：保持原图方向，不进行自适应旋转。
     *              1：将图片进行自适应旋转
     * @return 图片URL
     */
    String autoOrient(String bucketName, String objectName, Integer value);

    /**
     * 内切圆
     *
     * <p>
     *
     *     如果图片的最终格式是PNG、WebP或BMP等支持透明通道的图片，那么图片非圆形区域的部分将会以透明填充。
     *     如果图片的最终格式是JPG，那么非圆形区域是以白色进行填充。推荐保存成PNG格式。
     *     当value取值大于原图最小边的一半时，以原图最小边的一半为值返回内切圆（即r=原图最小边÷2）
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 内切圆的半径 [1,4096]
     * @return  图片URL
     */
    String circle(String bucketName, String objectName, Integer value);

    /**
     *索引切割
     *
     * <p>
     *     如果指定的索引值大于切割后形成的区域数量，将返回原图。
     *      当x和y同时指定且值合法时，以y参数的值为准
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param x  指定在x轴切割出的每块区域的长度。x参数与y参数只能任选其一  [1,图片宽度]
     * @param y  指定在y轴切割出的每块区域的长度。x参数与y参数只能任选其一 [1,图片高度]
     * @param i  选择切割后返回的图片区域 	[0,区域数) 默认为0，表示第一块
     * @return 图片URL
     */
    String indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i);

    /**
     *
     * 圆角矩形
     *
     * <p>
     *     如果图片的最终格式是PNG、WebP、BMP等支持透明通道的图片，那么图片圆角外的区域将会以透明填充。
     *     如果图片的最终格式是JPG，那么图片圆角外的区域以白色进行填充。推荐保存成PNG格式。
     *      如果指定圆角的半径大于原图最大内切圆的半径，则按照图片最大内切圆的半径设置圆角（即r=原图最小边÷2）
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param radius 将图片切出圆角，指定圆角的半径。	[1,4096]
     * @return 图片URL
     */
    String roundedCorners(String bucketName, String objectName, Integer radius);

    /**
     * 模糊效果
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param radius  模糊半径 [1,50] 该值越大，图片越模糊
     * @param deviation 正态分布的标准差 [1,50] 该值越大，图片越模糊
     * @return 图片URL
     */
    String blur(String bucketName, String objectName, Integer radius, Integer deviation);

    /**
     *
     * 旋转
     *
     * <p>
     *
     *     若图片旋转的角度不是90°、180°、270°、360°时，会导致处理后的图片尺寸变大。
     *      旋转功能对图片的尺寸有限制，图片的宽或者高不能超过4096 px
     *
     * </p>
     *
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 顺时针旋转的角度 [0,360] 默认值：0，表示不旋转
     * @return 图片URL
     */
    String rotate(String bucketName, String objectName, Integer value);

    /**
     * 渐进显示
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 是否设置图片为渐进显示
     *              1：表示将原图设置成渐进显示。
     *              0：表示将原图设置成标准显示
     * @return 图片URL
     */
    String interlace(String bucketName, String objectName, Integer value);

    /**
     * 亮度
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 图片的亮度  [-100, 100]
     *              取值＜0：降低图片亮度。
     *              取值=0：不调整图片亮度。
     *              取值＞0：提高图片亮度
     * @return 图片URL
     */
    String bright(String bucketName, String objectName, Integer value);

    /**
     * 锐化
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 锐化效果的强度  [50,399]
     *             取值越大，图片越清晰，但过大的值可能会导致图片失真。
     *              为达到较优效果，推荐取值为100
     * @return 图片URL
     */
    String sharpen(String bucketName, String objectName, Integer value);

    /**
     * 对比度
     * @param objectName 对象名称
     * @param bucketName 存储空间
     * @param value 图片的对比度  [-100,100]
     *             取值＜0：降低图片对比度。
     *              取值=0：维持原图对比度。
     *              取值＞0：提高图片对比度
     * @return 图片URL
     */
    String contrast(String bucketName, String objectName, Integer value);













}
