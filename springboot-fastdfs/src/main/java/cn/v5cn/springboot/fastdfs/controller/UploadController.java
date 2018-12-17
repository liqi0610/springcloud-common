package cn.v5cn.springboot.fastdfs.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZYW
 * @version 1.0
 * @date 2018/12/17 15:37
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private FastFileStorageClient fileStorageClient;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(MultipartFile file) throws IOException {
        StorePath storePath = fileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);

        return ResponseEntity.ok(storePath.getFullPath() + "=====" + storePath.getPath() + "+++" + storePath.getGroup());
    }

    @PostMapping("/download")
    public void download(String groupName, String path, HttpServletResponse response) {
        fileStorageClient.downloadFile(groupName,path, (is) -> {
            IOUtils.copy(is,response.getOutputStream());
            return null;
        });
    }

}
