package cn.v5cn.minio.demo;

import cn.v5cn.minio.demo.service.MinioService;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-02-11 20:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private MinioService minioService;

    @Test
    public void putFileTest() throws IOException {
        File file = FileUtils.getFile("/Users/zhuyanwei/Downloads/22.pdf");
        FileInputStream stream = FileUtils.openInputStream(file);
        Map<String,String> metadata = new HashMap<>();
        metadata.put("orgName","ddd.pdf");
        minioService.putFile("test1","test.pdf",stream,file.length(),metadata,null);
    }

    @Test
    public void getFileMetadata() {
        minioService.getFileMetadata("test1","test.pdf");
    }
}
