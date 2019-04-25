package com.example.mygallery.service.impl;

import com.example.mygallery.entity.ImageClass;
import com.example.mygallery.entity.ImageInfo;
import com.example.mygallery.entity.ImagePage;
import com.example.mygallery.properties.GalleryProperties;
import com.example.mygallery.service.ImageService;
import com.example.mygallery.utils.HttpClientUtils;
import com.example.mygallery.utils.ResponseUtils;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2019/4/22 14:12
 * description:
 */
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final GalleryProperties galleryProperties;

    @Override
    public ImagePage<ImageClass> findAllImageClass() {
        Map<String, Object> params = defaultImageClassParams();
        String str = HttpClientUtils.getInstance().httpGet(galleryProperties.getImageClassUrl(), params);
        return ResponseUtils.getResponseImageClassData(str);
    }

    @Override
    public ImagePage<ImageInfo> findAllImageInfo(Long cid, Long pageNumber, Long pageSize) {
        Map<String, Object> params = defaultImageInfoParams();
        params.put("cid", cid);
        params.put("start", pageNumber * pageSize);
        params.put("count", pageSize);
        String str = HttpClientUtils.getInstance().httpGet(galleryProperties.getImageInfoUrl(), params);
        return ResponseUtils.getResponseImageInfoData(str);
    }

    @Override
    public void writeImageFile(List<ImageInfo> imageInfos, String folder) {
        String localPath = galleryProperties.getLocalPath() + File.separator + folder + File.separator;
        File file = new File(localPath);
        if (!file.exists()) {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        String imageDownUrl = galleryProperties.getImageDownUrl();
        Map<String, Object> params = defaultImageDownParams();

        imageInfos.parallelStream().forEach(imageInfo -> {
            String url = imageInfo.getUrl().replace("r/__85", "m/" + 2560 + "_" + 1600 + "_" + 100);
            params.put("url", url);
            DataOutputStream out = null;
            try {
                out = new DataOutputStream(new FileOutputStream(localPath + System.currentTimeMillis() + ".jpg"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            HttpClientUtils.getInstance().httpGetDownFile(imageDownUrl, params, out);
        });


    }

    private Map<String, Object> defaultImageClassParams() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("c", "WallPaper");
        params.put("a", "getAllCategoriesV2");
        params.put("from", "360chrome");
        return params;
    }

    private Map<String, Object> defaultImageInfoParams() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("c", "WallPaper");
        params.put("a", "getAppsByCategory");
        params.put("from", "360chrome");
        return params;
    }

    private Map<String, Object> defaultImageDownParams() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("tn", "download");
        params.put("word", "download");
        params.put("ie", "utf8");
        params.put("fr", "detail");
        return params;
    }
}
