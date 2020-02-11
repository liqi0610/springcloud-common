package cn.v5cn.minio.demo.service;

import io.minio.ServerSideEncryption;

import java.io.InputStream;
import java.util.Map;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-02-11 15:39
 */
public interface MinioService {
    /**
     * <p>
     *     上传文件到Minio中
     * </p>
     * @param bucketName 桶名称
     * @param objectName 写入桶中的名称
     * @param stream 数据流
     * @param size 数据大小
     * @param headerMap 元数据
     * @param contentType 文件类型
     * @return 返回是否写入成功
     */
    boolean putFile(String bucketName, String objectName, InputStream stream, Long size, Map<String, String> headerMap,String contentType);

    /**
     * <p>
     *     获取Minio中的文件
     * </p>
     * @param bucketName 桶名称
     * @param objectName 桶中文件名称
     * @return 返回 InputStream
     */
    InputStream getFile(String bucketName,String objectName);

    InputStream getFileMetadata(String bucketName,String objectName);
}
