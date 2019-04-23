package com.example.mygallery.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 1/7/2019 9:58 AM
 * description: 用户配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "custom.gallery")
public class GalleryProperties {

    private String localPath;
    private String imageClassUrl;
    private String imageInfoUrl;
    private String imageDownUrl;
}
