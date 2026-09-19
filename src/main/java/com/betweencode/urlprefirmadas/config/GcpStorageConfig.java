package com.betweencode.urlprefirmadas.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class GcpStorageConfig {

    @Bean
    Storage storage(@Value("${gcp.storage.project-id}") String projectId) {
        StorageOptions.Builder builder = StorageOptions.newBuilder();
        if (StringUtils.hasText(projectId)) {
            builder.setProjectId(projectId);
        }
        return builder.build().getService();
    }
}
