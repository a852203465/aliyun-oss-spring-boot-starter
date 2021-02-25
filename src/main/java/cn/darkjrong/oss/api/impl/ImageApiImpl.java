package cn.darkjrong.oss.api.impl;

import cn.darkjrong.oss.api.ImageApi;
import cn.darkjrong.oss.callback.ImageStyleCallBack;
import cn.darkjrong.oss.common.builder.StyleBuilder;
import cn.darkjrong.oss.common.constants.FileConstant;
import cn.darkjrong.oss.common.enums.CompressedFormatEnum;
import cn.darkjrong.oss.common.enums.ImageFormatEnum;
import cn.darkjrong.oss.common.enums.ImageProcessingEnum;
import cn.darkjrong.oss.common.enums.ZoomModeEnum;
import cn.darkjrong.oss.common.exception.AliyunOSSClientException;
import cn.darkjrong.oss.common.pojo.dto.*;
import cn.darkjrong.oss.common.pojo.vo.ImageInfoVO;
import cn.darkjrong.oss.common.utils.StyleUtils;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 图片处理API 接口实现类
 * @author Rong.Jia
 * @date 2021/02/22 19:26
 */
@Component
public class ImageApiImpl extends BaseApiImpl implements ImageApi {

    private static final Logger logger = LoggerFactory.getLogger(ImageApiImpl.class);

    @Override
    public boolean resize(String bucketName, String objectName, ResizeDTO resizeDTO, File desFile) {
        String style = StyleUtils.resize(resizeDTO);
        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum, File desFile) {
        String style = StyleUtils.compression(compressedFormatEnum);
        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO, File desFile) {
        String style =StyleUtils.watermark(watermarkDTO);
        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean crop(String bucketName, String objectName, CropDTO cropDTO, File desFile) {

        String style = StyleUtils.crop(cropDTO);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean quality(String bucketName, String objectName, QualityDTO qualityDTO, File desFile) {

        String style = StyleUtils.quality(qualityDTO);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum, File desFile) {

        String style = StyleUtils.format(imageFormatEnum);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean autoOrient(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.autoOrient(value);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean circle(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.circle(value);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i, File desFile) {

        String style = StyleUtils.indexCrop(x, y, i);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean roundedCorners(String bucketName, String objectName, Integer radius, File desFile) {

        String style = StyleUtils.roundedCorners(radius);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean blur(String bucketName, String objectName, Integer radius, Integer deviation, File desFile) {

        String style = StyleUtils.blur(radius, deviation);
        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean rotate(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.rotate(value);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean interlace(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.interlace(value);

        return super.processing(bucketName, objectName, style, desFile);

    }

    @Override
    public boolean bright(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.bright(value);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean sharpen(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.sharpen(value);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean contrast(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleUtils.contrast(value);

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public String resize(String bucketName, String objectName, ResizeDTO resizeDTO, Long expirationTime) {

        String style = StyleUtils.resize(resizeDTO);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum, Long expirationTime) {

        String style = StyleUtils.compression(compressedFormatEnum);
        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO, Long expirationTime) {

        String style = StyleUtils.watermark(watermarkDTO);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String crop(String bucketName, String objectName, CropDTO cropDTO, Long expirationTime) {

        String style = StyleUtils.crop(cropDTO);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String quality(String bucketName, String objectName, QualityDTO qualityDTO, Long expirationTime) {

        String style = StyleUtils.quality(qualityDTO);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum, Long expirationTime) {

        String style = StyleUtils.format(imageFormatEnum);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public ImageInfoVO info(String bucketName, String objectName) throws AliyunOSSClientException {

        String style = StyleUtils.info();

        GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
        request.setProcess(style);

        try {
            OSSObject ossObject = getOssClient().getObject(request);
            String result = new String(IoUtil.readBytes(ossObject.getObjectContent()));
            return JSONObject.parseObject(result, ImageInfoVO.class);
        }catch (Exception e) {
            logger.error("info {}", e.getMessage());
            throw new AliyunOSSClientException(e.getMessage());
        }

    }

    @Override
    public String autoOrient(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.autoOrient(value);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String circle(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.circle(value);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i, Long expirationTime) {

        String style = StyleUtils.indexCrop(x, y, i);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String roundedCorners(String bucketName, String objectName, Integer radius, Long expirationTime) {

        String style = StyleUtils.roundedCorners(radius);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String blur(String bucketName, String objectName, Integer radius, Integer deviation, Long expirationTime) {

        String style = StyleUtils.blur(radius, deviation);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String rotate(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.rotate(value);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String interlace(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.interlace(value);

        return super.processing(bucketName, objectName, style, expirationTime);

    }

    @Override
    public String bright(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.bright(value);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String sharpen(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.sharpen(value);
        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String contrast(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleUtils.contrast(value);

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String averageHue(String bucketName, String objectName, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.AVERAGE_HUE)
                .build();

        GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
        request.setProcess(style);

        try {
            OSSObject ossObject = getOssClient().getObject(request);
            JSONObject jsonObject = JSONObject.parseObject(new String(IoUtil.readBytes(ossObject.getObjectContent())));
            return jsonObject.getString("RGB");
        }catch (Exception e) {
            logger.error("averageHue {}", e.getMessage());
        }

        return null;
    }

    @Override
    public String resize(String bucketName, String objectName, ResizeDTO resizeDTO) {
        return this.resize(bucketName, objectName, resizeDTO, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum) {
        return this.compression(bucketName, objectName, compressedFormatEnum, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO) {
        return this.watermark(bucketName, objectName, watermarkDTO, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String crop(String bucketName, String objectName, CropDTO cropDTO) {
        return this.crop(bucketName, objectName, cropDTO, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String quality(String bucketName, String objectName, QualityDTO qualityDTO) {
        return this.quality(bucketName, objectName, qualityDTO, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum) {
        return this.format(bucketName, objectName, imageFormatEnum, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String autoOrient(String bucketName, String objectName, Integer value) {
        return this.autoOrient(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String circle(String bucketName, String objectName, Integer value) {
        return this.circle(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i) {
        return this.indexCrop(bucketName, objectName, x, y, i, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String roundedCorners(String bucketName, String objectName, Integer radius) {
        return this.roundedCorners(bucketName, objectName, radius,  FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String blur(String bucketName, String objectName, Integer radius, Integer deviation) {
        return this.blur(bucketName, objectName, radius, deviation, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String rotate(String bucketName, String objectName, Integer value) {
        return this.rotate(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String interlace(String bucketName, String objectName, Integer value) {
        return this.interlace(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);

    }

    @Override
    public String bright(String bucketName, String objectName, Integer value) {
        return this.bright(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String sharpen(String bucketName, String objectName, Integer value) {
        return this.sharpen(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String contrast(String bucketName, String objectName, Integer value) {
        return this.contrast(bucketName, objectName, value, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public byte[] resize(ResizeDTO resizeDTO, String bucketName, String objectName) {

        String style = StyleUtils.resize(resizeDTO);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] compression(CompressedFormatEnum compressedFormatEnum, String bucketName, String objectName) {

        String style = StyleUtils.compression(compressedFormatEnum);
        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] watermark(WatermarkDTO watermarkDTO, String bucketName, String objectName) {

        String style = StyleUtils.watermark(watermarkDTO);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] crop(CropDTO cropDTO, String bucketName, String objectName) {

        String style = StyleUtils.crop(cropDTO);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] quality(QualityDTO qualityDTO, String bucketName, String objectName) {

        String style = StyleUtils.quality(qualityDTO);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] format(ImageFormatEnum imageFormatEnum, String bucketName, String objectName) {

        String style = StyleUtils.format(imageFormatEnum);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] autoOrient(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.autoOrient(value);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] circle(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.circle(value);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] indexCrop(Integer x, Integer y, Integer i, String bucketName, String objectName) {

        String style = StyleUtils.indexCrop(x, y, i);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] roundedCorners(Integer radius, String bucketName, String objectName) {

        String style = StyleUtils.roundedCorners(radius);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] blur(Integer radius, Integer deviation, String bucketName, String objectName) {

        String style = StyleUtils.blur(radius, deviation);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] rotate(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.rotate(value);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] interlace(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.interlace(value);

        return super.processing(bucketName, objectName, style);

    }

    @Override
    public byte[] bright(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.bright(value);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] sharpen(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.sharpen(value);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public byte[] contrast(Integer value, String bucketName, String objectName) {

        String style = StyleUtils.contrast(value);

        return super.processing(bucketName, objectName, style);
    }

    @Override
    public boolean comprehensive(String bucketName, String objectName, ImageDTO imageDTO, File desFile) {
        return super.processing(bucketName, objectName, StyleUtils.comprehensive(imageDTO), desFile);
    }

    @Override
    public String comprehensive(String bucketName, String objectName, ImageDTO imageDTO) {
        return this.comprehensive(bucketName, objectName, imageDTO, FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String comprehensive(String bucketName, String objectName, ImageDTO imageDTO, Long expirationTime) {
        return super.processing(bucketName, objectName, StyleUtils.comprehensive(imageDTO), expirationTime);
    }

    @Override
    public byte[] comprehensive(ImageDTO imageDTO, String bucketName, String objectName) {
        return super.processing(bucketName, objectName, StyleUtils.comprehensive(imageDTO));
    }

    @Override
    public boolean comprehensive(String bucketName, String objectName, ImageDTO imageDTO, ImageStyleCallBack imageStyleCallBack, File desFile) {
        return super.processing(bucketName, objectName, imageStyleCallBack.comprehensive(imageDTO), desFile);
    }

    @Override
    public String comprehensive(String bucketName, String objectName, ImageStyleCallBack imageStyleCallBack, ImageDTO imageDTO) {
        return super.processing(bucketName, objectName, imageStyleCallBack.comprehensive(imageDTO), FileConstant.EXPIRATION_TIME);
    }

    @Override
    public String comprehensive(String bucketName, String objectName, ImageDTO imageDTO, ImageStyleCallBack imageStyleCallBack, Long expirationTime) {
        return super.processing(bucketName, objectName, imageStyleCallBack.comprehensive(imageDTO), expirationTime);
    }

    @Override
    public byte[] comprehensive(ImageDTO imageDTO, ImageStyleCallBack imageStyleCallBack, String bucketName, String objectName) {
        return super.processing(bucketName, objectName, imageStyleCallBack.comprehensive(imageDTO));
    }
}
