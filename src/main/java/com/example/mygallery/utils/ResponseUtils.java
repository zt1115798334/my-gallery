package com.example.mygallery.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.example.mygallery.entity.ImageClass;
import com.example.mygallery.entity.ImageInfo;
import com.example.mygallery.entity.ImagePage;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2019/4/22 14:02
 * description:
 */
public class ResponseUtils {

    public static boolean getResponseState(String string) {
        return Optional.ofNullable(string)
                .map(JSONObject::parseObject)
                .filter(j -> j.containsKey("errno") || Objects.equal(j.getString("errno"), "0")).isPresent();
    }

    public static ImagePage<ImageClass> getResponseImageClassData(String string) {
        JSONObject jsonObject = JSON.parseObject(string);
        List<ImageClass> imageClassList = Lists.newArrayList();
        long total = jsonObject.getLongValue("total");
        if (getResponseState(string)) {
            JSONArray dataJSONArray = jsonObject.getJSONArray("data");
            imageClassList = dataJSONArray.stream().map(obj -> TypeUtils.castToJavaBean(obj, JSONObject.class))
                    .map(jo -> JSONObject.toJavaObject(jo, ImageClass.class))
                    .collect(Collectors.toList());
        }
        return new ImagePage<>(imageClassList, total);
    }

    public static ImagePage<ImageInfo> getResponseImageInfoData(String string) {
        JSONObject jsonObject = JSON.parseObject(string);
        List<ImageInfo> imageInfoList = Lists.newArrayList();
        long total = jsonObject.getLongValue("total");
        if (getResponseState(string)) {
            JSONArray dataJSONArray = jsonObject.getJSONArray("data");
            imageInfoList = dataJSONArray.stream().map(obj -> TypeUtils.castToJavaBean(obj, JSONObject.class))
                    .map(jo -> JSONObject.toJavaObject(jo, ImageInfo.class))
                    .collect(Collectors.toList());
        }
        return new ImagePage<>(imageInfoList, total);
    }

}
