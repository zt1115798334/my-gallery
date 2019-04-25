package com.example.mygallery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mygallery.entity.ImageInfo;
import com.example.mygallery.service.ImageService;
import com.example.mygallery.task.job.CollectionImageJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyGalleryApplicationTests {

    @Autowired
    private CollectionImageJob collectionImageJob;

    @Autowired
    private ImageService imageService;

    @Test
    public void contextLoads() {
        collectionImageJob.execute(null);
    }

    @Test
    public void testFindAllImage() {
        JSONObject jsonObject = JSON.parseObject("{\n" +
                "    \"ad_id\": \"\",\n" +
                "    \"ad_img\": \"\",\n" +
                "    \"ad_pos\": \"\",\n" +
                "    \"ad_url\": \"\",\n" +
                "    \"class_id\": \"36\",\n" +
                "    \"create_time\": \"2019-04-22 20:20:20\",\n" +
                "    \"download_times\": \"0\",\n" +
                "    \"ext_1\": \"\",\n" +
                "    \"ext_2\": \"\",\n" +
                "    \"id\": \"325287\",\n" +
                "    \"img_1024_768\": \"http://p19.qhimg.com/bdm/1024_768_85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"img_1280_1024\": \"http://p19.qhimg.com/bdm/1280_1024_85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"img_1280_800\": \"http://p19.qhimg.com/bdm/1280_800_85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"img_1366_768\": \"http://p19.qhimg.com/bdm/1366_768_85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"img_1440_900\": \"http://p19.qhimg.com/bdm/1440_900_85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"img_1600_900\": \"http://p19.qhimg.com/bdm/1600_900_85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"imgcut\": \"0\",\n" +
                "    \"rdata\": [],\n" +
                "    \"resolution\": \"3840x2160\",\n" +
                "    \"tag\": \"_全部_ _category_韩国_  _category_园林_ _category_春天_ _category_4K专区_\",\n" +
                "    \"tempdata\": \"\",\n" +
                "    \"update_time\": \"2019-04-22 20:20:20\",\n" +
                "    \"url\": \"http://p19.qhimg.com/bdr/__85/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"url_mid\": \"http://p19.qhimg.com/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"url_mobile\": \"http://p19.qhimg.com/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"url_thumb\": \"http://p19.qhimg.com/t01b8a9b68edc92f57d.jpg\",\n" +
                "    \"utag\": \"韩国 园林 春天\"\n" +
                "}");
        ImageInfo imageInfo = JSONObject.toJavaObject(jsonObject, ImageInfo.class);
        imageService.writeImageFile(Collections.singletonList(imageInfo), "");

    }

}
