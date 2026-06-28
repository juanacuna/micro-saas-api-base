package com.tuservicio.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import jakarta.annotation.PostConstruct;
import java.net.URI;

@Component
public class S3StorageAdapter {

    @Value("${aws.s3.endpoint}")
    private String endpoint;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    private S3Client s3Client;

    @PostConstruct
    public void init() {
        this.s3Client = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .build();
    }

    public void uploadFile(String key, byte[] content) {
        // Basic placeholder logic for S3 upload
        System.out.println("Uploading file to bucket " + bucketName + " with key: " + key);
    }

    public byte[] downloadFile(String key) {
        // Basic placeholder logic for S3 download
        System.out.println("Downloading file from bucket " + bucketName + " with key: " + key);
        return new byte[0];
    }
}
