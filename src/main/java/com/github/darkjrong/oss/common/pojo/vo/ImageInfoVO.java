package com.github.darkjrong.oss.common.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  图片信息
 * @author Rong.Jia
 * @date 2021/02/23 18:57
 */
@NoArgsConstructor
@Data
public class ImageInfoVO implements Serializable {

    private static final long serialVersionUID = -7696565162046265942L;

    @JSONField(name = "Compression")
    private CompressionBean compression;

    @JSONField(name = "DateTime")
    private DateTimeBean dateTime;

    @JSONField(name = "ExifTag")
    private ExifTagBean exifTag;

    @JSONField(name = "FileSize")
    private FileSizeBean fileSize;

    @JSONField(name = "Format")
    private FormatBean format;

    @JSONField(name = "GPSLatitude")
    private GPSLatitudeBean gpsLatitude;

    @JSONField(name = "GPSLatitudeRef")
    private GPSLatitudeRefBean gpsLatitudeRef;

    @JSONField(name = "GPSLongitude")
    private GPSLongitudeBean gpsLongitude;

    @JSONField(name = "GPSLongitudeRef")
    private GPSLongitudeRefBean gpsLongitudeRef;

    @JSONField(name = "GPSMapDatum")
    private GPSMapDatumBean gpsMapDatum;

    @JSONField(name = "GPSTag")
    private GPSTagBean gpsTag;

    @JSONField(name = "GPSVersionID")
    private GPSVersionIDBean gpsVersionId;

    @JSONField(name = "ImageHeight")
    private ImageHeightBean imageHeight;

    @JSONField(name = "ImageWidth")
    private ImageWidthBean imageWidth;

    @JSONField(name = "JPEGInterchangeFormat")
    private JPEGInterchangeFormatBean jpegInterchangeFormat;

    @JSONField(name = "JPEGInterchangeFormatLength")
    private JPEGInterchangeFormatLengthBean jpegInterchangeFormatLength;

    @JSONField(name = "Orientation")
    private OrientationBean orientation;

    @JSONField(name = "ResolutionUnit")
    private ResolutionUnitBean resolutionUnit;

    @JSONField(name = "Software")
    private SoftwareBean software;

    @JSONField(name = "XResolution")
    private XResolutionBean xResolution;

    @JSONField(name = "YResolution")
    private YResolutionBean yResolution;

    @NoArgsConstructor
    @Data
    public static class CompressionBean {
        /**
         * value : 6
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class DateTimeBean {
        /**
         * value : 2015:02:11 15:38:27
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class ExifTagBean {
        /**
         * value : 2212
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class FileSizeBean {
        /**
         * value : 23471
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class FormatBean {
        /**
         * value : jpg
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSLatitudeBean {
        /**
         * value : 0deg
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSLatitudeRefBean {
        /**
         * value : North
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSLongitudeBean {
        /**
         * value : 0deg
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSLongitudeRefBean {
        /**
         * value : East
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSMapDatumBean {
        /**
         * value : WGS-84
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSTagBean {
        /**
         * value : 4292
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class GPSVersionIDBean {
        /**
         * value : 2 2 0 0
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class ImageHeightBean {
        /**
         * value : 333
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class ImageWidthBean {
        /**
         * value : 424
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class JPEGInterchangeFormatBean {
        /**
         * value : 4518
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class JPEGInterchangeFormatLengthBean {
        /**
         * value : 3232
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class OrientationBean {
        /**
         * value : 7
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class ResolutionUnitBean {
        /**
         * value : 2
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class SoftwareBean {
        /**
         * value : Microsoft Windows Photo Viewer 6.1.7600.16385
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class XResolutionBean {
        /**
         * value : 96/1
         */

        private String value;
    }

    @NoArgsConstructor
    @Data
    public static class YResolutionBean {
        /**
         * value : 96/1
         */

        private String value;
    }
}
