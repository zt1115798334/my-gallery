package com.example.mygallery.task.job;

import com.example.mygallery.entity.ImageClass;
import com.example.mygallery.entity.ImageInfo;
import com.example.mygallery.entity.ImagePage;
import com.example.mygallery.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/10/11 9:30
 * description: 自定义报告定时器
 */
@Slf4j
@Component
public class CollectionImageJob implements Job {

    @Autowired
    private ImageService imageService;

    private final Long count = 30L;


    /**
     * 自定义报告定时器
     *
     * @param jobExecutionContext jobExecutionContext
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        ImagePage<ImageClass> all = imageService.findAllImageClass();
        all.getList().parallelStream()
                .forEach(imageClass -> {
                    Long classId = imageClass.getId();
                    String className = imageClass.getName();

                    List<ImageInfo> imageInfos = findAllImage(classId);
                    imageService.writeImageFile(imageInfos, className);

                });
    }

    private List<ImageInfo> findAllImage(Long id) {
        ImagePage<ImageInfo> allImage = imageService.findAllImageInfo(id, 1L, 1L);
        Long totalElements = allImage.getTotalElements();
        long page = BigDecimal.valueOf(totalElements).divide(BigDecimal.valueOf(count), 0, BigDecimal.ROUND_HALF_UP).longValue();

        return LongStream.rangeClosed(1L, page)
                .boxed()
                .map(pageNumber -> imageService.findAllImageInfo(id, pageNumber, count).getList())
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toList());
    }
}
