package cn.darkjrong.oss.common.constants;

import cn.hutool.core.io.unit.DataSize;
import cn.hutool.core.io.unit.DataUnit;

/**
 * 文件常量类
 * @author Rong.Jia
 * @date 2021/02/21 15:47
 */
public class FileConstant {

    public static final String JPEG_SUFFIX = ".jpeg";

    public static final Long PART_SIZE = DataSize.of(10, DataUnit.MEGABYTES).toMegabytes();

    public static final int LIMIT_SPEED = 200 * 1024;

    public static final Long EXPIRATION_TIME = 7200L * 1000;




}
