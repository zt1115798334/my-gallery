package com.example.mygallery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2019/4/22 13:21
 * description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfo {
    private Long id;

    private String img_1024_768;
    private String img_1280_1024;
    private String img_1280_800;
    private String img_1366_768;
    private String img_1440_900;
    private String img_1600_900;

    private String url;
    private String url_mid;
    private String url_mobile;
    private String url_thumb;
}
