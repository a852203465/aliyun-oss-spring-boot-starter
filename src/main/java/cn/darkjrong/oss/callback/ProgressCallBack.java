package cn.darkjrong.oss.callback;

/**
 *  上传进度回调接口
 * @author Rong.Jia
 * @date 2021/02/21 17:14
 */
@FunctionalInterface
public interface ProgressCallBack {

    /**
     * 进度回调
     * @param totalBytes 总大小
     * @param completeBytes 已完成大小
     * @param objectName 正在上传的文件名
     * @param succeed 是否上传/下载成功， true:成功，false：失败
     */
    void progress(String objectName, Long totalBytes, Long completeBytes, Boolean succeed);

}
