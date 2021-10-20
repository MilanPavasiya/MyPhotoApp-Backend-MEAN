package com.myphotoapp.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    public boolean upload(MultipartFile file0) {

        BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.CA_CENTRAL_1).build();

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file0.getSize());
            metadata.setContentType(file0.getContentType());

            s3.putObject("", file0.getOriginalFilename(), file0.getInputStream(), metadata);

            return true;
        } catch (AmazonServiceException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public S3Object getFile(String key){
        BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.CA_CENTRAL_1).build();

        return s3.getObject("", key);
    }

    public S3Object getFileByFileId(String fileId) {
        BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.CA_CENTRAL_1).build();

        return s3.getObject("", fileId);
    }

    public void deleteFile(String key){
        BasicAWSCredentials credentials = new BasicAWSCredentials("", "");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.CA_CENTRAL_1).build();
        s3.deleteObject("", key);
    }
}
