package com.example.mygallery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/8/30 15:58
 * description: es分页类
 */
@Data
@AllArgsConstructor
public class ImagePage<T> {

    private int pageNumber;

    private int pageSize;

    private List<T> list;

    private Long totalElements;


    public ImagePage(List<T> list, Long totalElements) {
        this.list = list;
        this.totalElements = totalElements;
    }
}
