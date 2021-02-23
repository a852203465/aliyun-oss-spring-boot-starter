package cn.darkjrong.oss.api.impl;

import cn.darkjrong.oss.api.ImageApi;
import cn.darkjrong.oss.common.builder.StyleBuilder;
import cn.darkjrong.oss.common.constants.FileConstant;
import cn.darkjrong.oss.common.enums.CompressedFormatEnum;
import cn.darkjrong.oss.common.enums.ImageFormatEnum;
import cn.darkjrong.oss.common.enums.ImageProcessingEnum;
import cn.darkjrong.oss.common.enums.ZoomModeEnum;
import cn.darkjrong.oss.common.exception.AliyunOSSClientException;
import cn.darkjrong.oss.common.pojo.dto.CropDTO;
import cn.darkjrong.oss.common.pojo.dto.QualityDTO;
import cn.darkjrong.oss.common.pojo.dto.ResizeDTO;
import cn.darkjrong.oss.common.pojo.dto.WatermarkDTO;
import cn.darkjrong.oss.common.pojo.vo.ImageInfoVO;
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

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.RESIZE)
                .zoomMode(resizeDTO.getZoomModeEnum())
                .width(resizeDTO.getWidth())
                .height(resizeDTO.getHeight())
                .longest(resizeDTO.getLongest())
                .shortest(resizeDTO.getShortest())
                .limit(resizeDTO.getLimit())
                .color(resizeDTO.getColor())
                .build();

        if (ZoomModeEnum.PAD == resizeDTO.getZoomModeEnum()) {
            style = StyleBuilder.custom().other(style, StyleBuilder.custom().color(resizeDTO.getColor()).build()).build();
        }

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum, File desFile) {

        String style = StyleBuilder.custom().image().processMode(ImageProcessingEnum.FORMAT).compression(compressedFormatEnum).build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.WATERMARK)
                .transparency(watermarkDTO.getTransparency())
                .location(watermarkDTO.getLocation())
                .horizontalMargin(watermarkDTO.getHorizontalMargin())
                .verticalMargin(watermarkDTO.getVerticalMargin())
                .voffset(watermarkDTO.getVoffset())
                .image(watermarkDTO.getImage())
                .proportion(watermarkDTO.getProportion())
                .text(watermarkDTO.getText())
                .type(watermarkDTO.getType())
                .size(watermarkDTO.getSize())
                .shadow(watermarkDTO.getShadow())
                .rotate(watermarkDTO.getRotate())
                .fill(watermarkDTO.getFill())
                .order(watermarkDTO.getOrder())
                .align(watermarkDTO.getAlign())
                .interval(watermarkDTO.getInterval())
                .color(watermarkDTO.getColor())
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean crop(String bucketName, String objectName, CropDTO cropDTO, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CROP)
                .width(cropDTO.getWidth())
                .height(cropDTO.getHeight())
                .x(cropDTO.getX())
                .y(cropDTO.getY())
                .origin(cropDTO.getOrigin())
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean quality(String bucketName, String objectName, QualityDTO qualityDTO, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.QUALITY)
                .relative(qualityDTO.getRelative())
                .absolute(qualityDTO.getAbsolute())
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.FORMAT)
                .format(imageFormatEnum)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean autoOrient(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.AUTO_ORIENT)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean circle(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CIRCLE)
                .circle(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INDEX_CROP)
                .x(x).y(y).i(i)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean roundedCorners(String bucketName, String objectName, Integer radius, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.ROUNDED_CORNERS)
                .radius(radius)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean blur(String bucketName, String objectName, Integer radius, Integer deviation, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.BLUR)
                .radius(radius)
                .deviation(deviation)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean rotate(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.ROTATE)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean interlace(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INTERLACE)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);

    }

    @Override
    public boolean bright(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.BRIGHT)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean sharpen(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.SHARPEN)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public boolean contrast(String bucketName, String objectName, Integer value, File desFile) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CONTRAST)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, desFile);
    }

    @Override
    public String resize(String bucketName, String objectName, ResizeDTO resizeDTO, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.RESIZE)
                .zoomMode(resizeDTO.getZoomModeEnum())
                .width(resizeDTO.getWidth())
                .height(resizeDTO.getHeight())
                .longest(resizeDTO.getLongest())
                .shortest(resizeDTO.getShortest())
                .limit(resizeDTO.getLimit())
                .color(resizeDTO.getColor())
                .build();

        if (ZoomModeEnum.PAD == resizeDTO.getZoomModeEnum()) {
            style = StyleBuilder.custom().other(style, StyleBuilder.custom().color(resizeDTO.getColor()).build()).build();
        }

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String compression(String bucketName, String objectName, CompressedFormatEnum compressedFormatEnum, Long expirationTime) {

        String style = StyleBuilder.custom().image().processMode(ImageProcessingEnum.FORMAT).compression(compressedFormatEnum).build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String watermark(String bucketName, String objectName, WatermarkDTO watermarkDTO, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.WATERMARK)
                .transparency(watermarkDTO.getTransparency())
                .location(watermarkDTO.getLocation())
                .horizontalMargin(watermarkDTO.getHorizontalMargin())
                .verticalMargin(watermarkDTO.getVerticalMargin())
                .voffset(watermarkDTO.getVoffset())
                .image(watermarkDTO.getImage())
                .proportion(watermarkDTO.getProportion())
                .text(watermarkDTO.getText())
                .type(watermarkDTO.getType())
                .size(watermarkDTO.getSize())
                .shadow(watermarkDTO.getShadow())
                .rotate(watermarkDTO.getRotate())
                .fill(watermarkDTO.getFill())
                .order(watermarkDTO.getOrder())
                .align(watermarkDTO.getAlign())
                .interval(watermarkDTO.getInterval())
                .color(watermarkDTO.getColor())
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String crop(String bucketName, String objectName, CropDTO cropDTO, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CROP)
                .width(cropDTO.getWidth())
                .height(cropDTO.getHeight())
                .x(cropDTO.getX())
                .y(cropDTO.getY())
                .origin(cropDTO.getOrigin())
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String quality(String bucketName, String objectName, QualityDTO qualityDTO, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.QUALITY)
                .relative(qualityDTO.getRelative())
                .absolute(qualityDTO.getAbsolute())
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String format(String bucketName, String objectName, ImageFormatEnum imageFormatEnum, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.FORMAT)
                .format(imageFormatEnum)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public ImageInfoVO info(String bucketName, String objectName) throws AliyunOSSClientException {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INFO)
                .build();

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

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.AUTO_ORIENT)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String circle(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CIRCLE)
                .circle(value)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String indexCrop(String bucketName, String objectName, Integer x, Integer y, Integer i, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INDEX_CROP)
                .x(x).y(y).i(i)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String roundedCorners(String bucketName, String objectName, Integer radius, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.ROUNDED_CORNERS)
                .radius(radius)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String blur(String bucketName, String objectName, Integer radius, Integer deviation, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.BLUR)
                .radius(radius)
                .deviation(deviation)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String rotate(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.ROTATE)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String interlace(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.INTERLACE)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);

    }

    @Override
    public String bright(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.BRIGHT)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String sharpen(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.SHARPEN)
                .value(value)
                .build();

        return super.processing(bucketName, objectName, style, expirationTime);
    }

    @Override
    public String contrast(String bucketName, String objectName, Integer value, Long expirationTime) {

        String style = StyleBuilder.custom().image()
                .processMode(ImageProcessingEnum.CONTRAST)
                .value(value)
                .build();

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



}
