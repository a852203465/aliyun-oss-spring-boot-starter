package cn.darkjrong.oss.api;

import cn.darkjrong.oss.api.impl.BucketApiImpl;
import cn.darkjrong.oss.api.impl.FileOperationsApiImpl;
import cn.darkjrong.oss.callback.ProgressCallBack;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.ObjectPermission;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * 上传，下载，管理 API 接口测试类
 * @author Rong.Jia
 * @date 2021/02/21 15:09
 */
public class FileOperationsApiTest extends BaseApiTest {

    private static FileOperationsApiImpl fileOperationsApi = new FileOperationsApiImpl();

    static {

        BucketApiImpl bucketApi = new BucketApiImpl();
        bucketApi.setOssClient(ossClient);
        fileOperationsApi.setAliyunOSSProperties(aliyunOSSProperties);
        fileOperationsApi.setBucketApi(bucketApi);
        fileOperationsApi.setOssClient(ossClient);
    }

    @Test
    public void uploadFile() {

        System.out.println(fileOperationsApi.uploadFile("mrj123456mrj",
                DateUtil.today(), new File("F:\\我的图片\\美女\\1.jpg"), CollectionUtil.newHashMap()));

    }

    @Test
    public void shardUploadFile() {

        System.out.println(fileOperationsApi.shardUploadFile("mrj123456mrj",
                DateUtil.today(), new File("F:\\我的图片\\美女\\1.jpg"), 1024L * 1024, null));

    }

    @Test
    public void uploadFile2() throws FileNotFoundException {

        System.out.println(fileOperationsApi.uploadFile("mrj123456mrj",
                DateUtil.today(), new File("F:\\我的图片\\美女\\1.jpg"),null, new ProgressCallBack() {
                    @Override
                    public void progress(String objectName, Long totalBytes, Long completeBytes, Boolean succeed) {
                        System.out.println("totalBytes : " + totalBytes + ", completeBytes : " + completeBytes + ", succeed : " + succeed);
                    }
                }));

    }

    @Test
    public void downloadFile() {

        byte[] downloadFile = fileOperationsApi.downloadFile("mrj123456mrj", "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg");

        FileUtil.writeBytes(downloadFile, new File("F:\\1.jpg"));


    }

    @Test
    public void downloadFile2() {

        boolean downloadFile = fileOperationsApi.downloadFile("mrj123456mrj", "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg", new File("F:\\1.jpg"), new ProgressCallBack() {
            @Override
            public void progress(String objectName, Long totalBytes, Long completeBytes, Boolean succeed) {
                System.out.println("totalBytes : " + totalBytes + ", completeBytes : " + completeBytes + ", succeed : " + succeed);
            }
        });

        System.out.println(downloadFile);

    }

    @Test
    public void doesObjectExist() {

        boolean objectExist = fileOperationsApi.doesObjectExist("mrj123456mrj", "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg");
        System.out.println(objectExist);

    }

    @Test
    public void setObjectAcl() {

        boolean objectAcl = fileOperationsApi.setObjectAcl("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg",
                CannedAccessControlList.PublicRead);
        System.out.println(objectAcl);

    }

    @Test
    public void getObjectAcl() {

        ObjectPermission objectAcl = fileOperationsApi.getObjectAcl("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg");
        System.out.println(objectAcl.toString());

    }

    @Test
    public void getObjectMetadata() throws ParseException {

        ObjectMetadata objectMetadata = fileOperationsApi.getObjectMetadata("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg");
        System.out.println(objectMetadata.getContentType());
        System.out.println(objectMetadata.getLastModified());
        System.out.println(objectMetadata.getExpirationTime());
    }

    @Test
    public void speedLimitDownloadFile() {

        boolean downloadFile = fileOperationsApi.speedLimitDownloadFile("mrj123456mrj",
                "2021-02-21/98c3f58faab94d518661b9d761d193a4-20210221181905631.jpg", new File("F:\\1.jpg"));
        System.out.println(downloadFile);
    }



}
