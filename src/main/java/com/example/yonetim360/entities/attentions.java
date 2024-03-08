package com.example.yonetim360.entities;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Data
@Entity
public class attentions {

    @Id
    private Long id;

   /* public static void main(String[] args) {
        MinioClient minioClient = demo();
        try{
            List<Bucket> bucketList = minioClient.listBuckets();
            System.out.println("connection success, total buckets:" + bucketList.size());
        }
         catch (MinioException e){
            System.out.println("connection failed" + e.getMessage());
         }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static MinioClient demo() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("https://play.min.io")
                .credentials("5","lfdnlck")
                .build();

        return minioClient;
    }
*/

}
