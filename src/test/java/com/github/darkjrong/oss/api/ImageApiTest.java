package com.github.darkjrong.oss.api;

import com.github.darkjrong.oss.api.impl.BucketApiImpl;
import com.github.darkjrong.oss.api.impl.ImageApiImpl;
import com.github.darkjrong.oss.api.impl.PresignedUrlApiImpl;
import com.github.darkjrong.oss.callback.ImageStyleCallBack;
import com.github.darkjrong.oss.common.enums.CompressedFormatEnum;
import com.github.darkjrong.oss.common.enums.ZoomModeEnum;
import cn.darkjrong.oss.common.pojo.dto.*;
import com.github.darkjrong.oss.common.pojo.vo.ImageInfoVO;
import com.github.darkjrong.oss.common.utils.StyleUtils;
import cn.hutool.core.io.FileUtil;
import com.github.darkjrong.oss.common.pojo.dto.*;
import org.junit.Test;

import java.io.File;

/**
 *  图片处理测试类
 * @author Rong.Jia
 * @date 2021/02/22 20:39
 */
public class ImageApiTest extends BaseApiTest {

    private static ImageApiImpl imageProcessingApi = new ImageApiImpl();

    static {

        PresignedUrlApiImpl presignedUrlApi = new PresignedUrlApiImpl();
        presignedUrlApi.setAliyunOSSProperties(aliyunOSSProperties);
        presignedUrlApi.setOssClient(ossClient);

        BucketApiImpl bucketApi = new BucketApiImpl();
        bucketApi.setOssClient(ossClient);

        imageProcessingApi.setPresignedUrlApi(presignedUrlApi);
        imageProcessingApi.setOssClient(ossClient);
    }

    @Test
    public void resize() {

        ResizeDTO resizeDTO = new ResizeDTO();
        resizeDTO.setHeight(100);
        resizeDTO.setWidth(100);
        resizeDTO.setZoomModeEnum(ZoomModeEnum.FIXED);

        boolean resize = imageProcessingApi.resize("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                resizeDTO, new File("F:\\1.jpg"));

        System.out.println(resize);

    }

    @Test
    public void compression() {

        boolean compression = imageProcessingApi.compression("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                CompressedFormatEnum.WEBP_M6, new File("F:\\1.jpg"));

        System.out.println(compression);

    }

    @Test
    public void watermark() {

        WatermarkDTO watermarkDTO = new WatermarkDTO();
        watermarkDTO.setText("测试");

        boolean watermark = imageProcessingApi.watermark("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                watermarkDTO, new File("F:\\1.jpg"));

        System.out.println(watermark);

    }

    @Test
    public void crop() {

        CropDTO cropDTO = new CropDTO();
        cropDTO.setX(300);
        cropDTO.setY(100);

        boolean crop = imageProcessingApi.crop("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                cropDTO, new File("F:\\1.jpg"));

        System.out.println(crop);

    }

    @Test
    public void info() {
        ImageInfoVO imageInfoVO = imageProcessingApi.info("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg");

        System.out.println(imageInfoVO.toString());
    }

    @Test
    public void autoOrient() {

        boolean autoOrient = imageProcessingApi.autoOrient("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                1, new File("F:\\1.jpg"));

        System.out.println(autoOrient);

    }

    @Test
    public void circle() {

        boolean circle = imageProcessingApi.circle("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                100, new File("F:\\1.jpg"));

        System.out.println(circle);

    }

    @Test
    public void indexCrop() {

        boolean indexCrop = imageProcessingApi.indexCrop("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                100, null, 0, new File("F:\\1.jpg"));

        System.out.println(indexCrop);

    }

    @Test
    public void roundedCorners() {

        boolean roundedCorners = imageProcessingApi.roundedCorners("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                500, new File("F:\\1.jpg"));

        System.out.println(roundedCorners);

    }

    @Test
    public void blur() {

        boolean blur = imageProcessingApi.blur("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                10, 10, new File("F:\\1.jpg"));

        System.out.println(blur);

    }

    @Test
    public void rotate() {

        boolean rotate = imageProcessingApi.rotate("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                90, new File("F:\\1.jpg"));

        System.out.println(rotate);

    }

    @Test
    public void interlace() {

        boolean interlace = imageProcessingApi.interlace("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                1, new File("F:\\1.jpg"));

        System.out.println(interlace);

    }

    @Test
    public void bright() {

        boolean bright = imageProcessingApi.bright("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                50, new File("F:\\1.jpg"));

        System.out.println(bright);

    }

    @Test
    public void sharpen() {

        boolean sharpen = imageProcessingApi.sharpen("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                100, new File("F:\\1.jpg"));

        System.out.println(sharpen);

    }

    @Test
    public void contrast() {

        boolean contrast = imageProcessingApi.contrast("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                -50, new File("F:\\1.jpg"));

        System.out.println(contrast);

    }

    @Test
    public void averageHue() {

        String averageHue = imageProcessingApi.averageHue("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg", null);

        System.out.println(averageHue);

    }

    @Test
    public void blur2() {

        String blur = imageProcessingApi.blur("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                10, 10);

        System.out.println(blur);

    }

    @Test
    public void blur3() {

        byte[] bytes = imageProcessingApi.blur(10, 10, "mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg");

        FileUtil.writeBytes(bytes, new File("F:\\1.jpg"));


    }

    @Test
    public void comprehensive() {

        ImageDTO imageDTO = new ImageDTO();

        ResizeDTO resizeDTO = new ResizeDTO();
        resizeDTO.setHeight(1000);
        resizeDTO.setWidth(1000);
        resizeDTO.setZoomModeEnum(ZoomModeEnum.FIXED);
        imageDTO.setResize(resizeDTO);

        WatermarkDTO watermarkDTO = new WatermarkDTO();
        watermarkDTO.setText("测试");
        imageDTO.setWatermark(watermarkDTO);

        BlurDTO blurDTO = new BlurDTO();
        blurDTO.setDeviation(10);
        blurDTO.setRadius(20);
        imageDTO.setBlur(blurDTO);

        String comprehensive = StyleUtils.comprehensive(imageDTO);
        System.out.println(comprehensive);

        String comprehensive1 = imageProcessingApi.comprehensive("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg", imageDTO);
        System.out.println(comprehensive1);
    }

    @Test
    public void comprehensive1() {

        ImageDTO imageDTO = new ImageDTO();

        ResizeDTO resizeDTO = new ResizeDTO();
        resizeDTO.setHeight(1000);
        resizeDTO.setWidth(1000);
        resizeDTO.setZoomModeEnum(ZoomModeEnum.FIXED);
        imageDTO.setResize(resizeDTO);

        WatermarkDTO watermarkDTO = new WatermarkDTO();
        watermarkDTO.setText("测试");
        imageDTO.setWatermark(watermarkDTO);

        BlurDTO blurDTO = new BlurDTO();
        blurDTO.setDeviation(10);
        blurDTO.setRadius(20);
        imageDTO.setBlur(blurDTO);

//        String comprehensive = StyleUtils.comprehensive(imageDTO);
//        System.out.println(comprehensive);

        boolean comprehensive1 = imageProcessingApi.comprehensive("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg", imageDTO, new ImageStyleCallBack() {
                    @Override
                    public String comprehensive(ImageDTO imageDTO) {
                        return StyleUtils.comprehensive(imageDTO);
                    }
                }, new File("F:\\1.jpg"));
        System.out.println(comprehensive1);
    }


}
