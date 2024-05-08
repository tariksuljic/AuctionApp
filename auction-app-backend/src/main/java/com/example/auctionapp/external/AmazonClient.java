package com.example.auctionapp.external;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AmazonClient {
    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        final AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    private File convertMultiPartToFile(final MultipartFile file) throws IOException {
        final File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }

    private String generateFileName(final MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileToS3Bucket(final String fileName, final File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String uploadFile(final MultipartFile multipartFile) throws Exception {
        String fileUrl = "";

        try {
            final File file = convertMultiPartToFile(multipartFile);
            final String fileName = generateFileName(multipartFile);

            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;

            uploadFileToS3Bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            throw new Exception(e);
        }

        return fileUrl;
    }

    public String deleteFileFromS3Bucket(final String fileUrl) {
        final String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));

        return "Successfully deleted";
    }
}
