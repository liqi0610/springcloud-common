package cn.v5cn.minio.demo.service.impl;

import cn.v5cn.minio.demo.exception.MyMinioException;
import cn.v5cn.minio.demo.service.MinioService;
import cn.v5cn.minio.demo.service.model.FileMetadata;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-02-11 15:40
 */
@Service
public class MinioServiceImpl implements MinioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioServiceImpl.class);

    @Autowired
    private MinioClient minioClient;


    @Override
    public boolean putFile(String bucketName, String objectName, InputStream stream, Long size, Map<String, String> headerMap, String contentType) {
        try {
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist) {
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName,objectName,stream,size,headerMap,null,contentType);
        } catch (InvalidBucketNameException
                | XmlPullParserException
                | ErrorResponseException
                | InternalException
                | InvalidArgumentException
                | InsufficientDataException
                | NoResponseException
                | InvalidKeyException
                | IOException
                | RegionConflictException
                | NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(),e);
            throw new MyMinioException(e);
        }
        return true;
    }

    @Override
    public InputStream getFile(String bucketName,String objectName) {
        try {
            return minioClient.getObject(bucketName,objectName);
        } catch (InvalidBucketNameException
                | NoSuchAlgorithmException
                | InsufficientDataException
                | IOException
                | InvalidKeyException
                | NoResponseException
                | XmlPullParserException
                | ErrorResponseException
                | InternalException
                | InvalidArgumentException e) {
            LOGGER.error(e.getMessage(),e);
            throw new MyMinioException(e);
        }
    }

    @Override
    public FileMetadata getFileMetadata(String bucketName, String objectName){
        try {
            ObjectStat objectStat = minioClient.statObject(bucketName, objectName);

            String origName = "";

            List<String> metadata = objectStat.httpHeaders().get(MinioService.META_DATA_PREFIX);
            if(metadata != null && !metadata.isEmpty()) {
                origName = metadata.get(0);
            }
            InputStream stream = minioClient.getObject(bucketName, objectName);

            return new FileMetadata(stream,origName,objectStat.createdTime(),objectStat.length(),objectStat.contentType());
        } catch (InvalidBucketNameException
                | NoSuchAlgorithmException
                | InsufficientDataException
                | IOException
                | InvalidKeyException
                | NoResponseException
                | XmlPullParserException
                | ErrorResponseException
                | InternalException
                | InvalidArgumentException e) {
            LOGGER.error(e.getMessage(),e);
            throw new MyMinioException(e);
        }
    }

    @Override
    public boolean removeFile(String bucketName, String objectName) {
        try {
            minioClient.removeObject(bucketName,objectName);
        } catch (InvalidBucketNameException
                | NoSuchAlgorithmException
                | InsufficientDataException
                | IOException
                | InvalidKeyException
                | NoResponseException
                | XmlPullParserException
                | ErrorResponseException
                | InternalException
                | InvalidArgumentException e) {
            LOGGER.error(e.getMessage(),e);
            throw new MyMinioException(e);
        }
        return true;
    }
}
