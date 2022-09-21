package com.userService.user.Config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@Component
public class S3Repository {

    @Value("${user.accessKey}")
    private String accessKey;

    @Value("${user.secretKey}")
    private String secretKey;

    @Value("${user.bucketName}")
    private String bucketName = "bmc.user.documents";

    private AmazonS3 s3Client;

    @Autowired
    private ObjectMetadata metadata;

    @PostConstruct
    public void init() {

        System.out.println("accessKey"+accessKey);
        System.out.println("secretKey"+secretKey);
        System.out.println("bucketName"+bucketName);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.US_EAST_1)
                    .build();

    }

    public void uploadFiles(String userID, MultipartFile file) throws Exception {
        if(!s3Client.doesBucketExistV2(bucketName)) {
            s3Client.createBucket(bucketName);
        }
        String key = userID +"/" +file.getOriginalFilename();
        s3Client.putObject(bucketName,key,file.getInputStream(),metadata);
    }
}
