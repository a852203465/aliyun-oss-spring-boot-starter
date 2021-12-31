package cn.darkjrong.oss.api;

import cn.darkjrong.oss.api.impl.PresignedUrlApiImpl;
import cn.darkjrong.oss.api.impl.VideoApiImpl;
import cn.darkjrong.oss.common.enums.ImageFormatEnum;
import cn.darkjrong.oss.common.pojo.dto.VideoSnapshotDTO;
import org.junit.jupiter.api.Test;

/**
 *
 * 视频处理API测试
 * @author Rong.Jia
 * @date 2021/02/23 22:31
 */
public class VideoApiTest extends BaseApiTest {

    private static VideoApiImpl videoApi = new VideoApiImpl();

    static {

        PresignedUrlApiImpl presignedUrlApi = new PresignedUrlApiImpl();
        presignedUrlApi.setAliyunOSSProperties(aliyunOSSProperties);
        presignedUrlApi.setOssClient(ossClient);

        videoApi.setPresignedUrlApi(presignedUrlApi);
        videoApi.setOssClient(ossClient);
    }

    @Test
    public void snapshot() {

        VideoSnapshotDTO videoSnapshotDTO = new VideoSnapshotDTO();
        videoSnapshotDTO.setTime(50000L);
        videoSnapshotDTO.setFormat(ImageFormatEnum.JPG);
        videoSnapshotDTO.setWidth(800);
        videoSnapshotDTO.setHeight(600);

        String snapshot = videoApi.snapshot(videoSnapshotDTO, "mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.mp4");

        System.out.println(snapshot);

    }




}
