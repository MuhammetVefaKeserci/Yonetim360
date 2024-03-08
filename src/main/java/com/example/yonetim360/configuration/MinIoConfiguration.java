package com.example.yonetim360.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIoConfiguration {


    public static MinioClient demo(){
        return MinioClient.builder()
                .endpoint("https://play.min.io")
                .credentials("Ji0dQdnGPJhUGm1zgVAl", "N8IosOEdNM3GIx4UmTPGC90ZQA773Hw3TG8DTk3m")
                .build();

    }
}
