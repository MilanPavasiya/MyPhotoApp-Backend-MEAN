package com.myphotoapp.resource;

import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.S3Object;
import com.myphotoapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/file")
public class FileResource {

    @Autowired
    private FileService fileService;

    // View files
    @GetMapping("/view")
    public void view(@RequestParam("key") String key, HttpServletResponse response) throws IOException {
        S3Object object = fileService.getFile(key);
        response.setContentType(object.getObjectMetadata().getContentType());
        response.getOutputStream().write(object.getObjectContent().readAllBytes());
    }

    // Download a files
    @GetMapping("download")
    public ResponseEntity<Resource> download(@RequestParam("key") String key) throws IOException {
        S3Object object = fileService.getFile(key);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(object.getObjectMetadata().getContentType()))
                .header(Headers.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .body(new ByteArrayResource(object.getObjectContent().readAllBytes()));
    }

    // Get files from FileId
    @GetMapping("/show/{fileId}")
    @CrossOrigin
    public void showFile(@PathVariable("fileId") String fileId, HttpServletResponse response) throws IOException {
        S3Object object = fileService.getFileByFileId(fileId);
        response.setContentType(object.getObjectMetadata().getContentType());
        response.getOutputStream().write(object.getObjectContent().readAllBytes());
    }

    // Upload files
    @PostMapping("/upload")
    @CrossOrigin
    public boolean upload(@RequestParam("file0")MultipartFile file0){
        return fileService.upload(file0);
    }

    // Delete files
    @DeleteMapping
    public void delete(@RequestParam("key") String key){
        fileService.deleteFile(key);
    }
}
