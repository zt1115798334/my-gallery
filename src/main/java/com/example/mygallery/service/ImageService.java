package com.example.mygallery.service;

import com.example.mygallery.entity.ImageClass;
import com.example.mygallery.entity.ImageInfo;
import com.example.mygallery.entity.ImagePage;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2019/4/22 14:12
 * description:
 */
public interface ImageService {

    ImagePage<ImageClass> findAllImageClass();

    ImagePage<ImageInfo> findAllImageInfo(Long cid, Long pageNumber, Long pageSize);

    void writeImageFile(List<ImageInfo> imageInfos,String folder) throws FileNotFoundException;

}
