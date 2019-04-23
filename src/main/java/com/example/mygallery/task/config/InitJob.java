package com.example.mygallery.task.config;

import com.example.mygallery.quartz.entity.QuartzEntity;
import com.example.mygallery.quartz.service.JobService;
import com.example.mygallery.task.job.CollectionImageJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/12/25 11:31
 * description: 初始化 job
 */
@Slf4j
@AllArgsConstructor
@Component
public class InitJob implements ApplicationRunner {

    private final JobService jobService;

    private static final String zeroPerDay = "0 0 0 * * ?";    //每天零点
//private static final String perFiveMinutes = "0/2 * * * * ?";    //每5分钟

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<QuartzEntity> quartzEntityList = jobService.list();
        if (quartzEntityList.size() == 0) {
            log.info("初始化任务...");
            QuartzEntity infoBriefingCurrencyDayJob = new QuartzEntity(
                    "普通版日报定时器",
                    "group1",
                    "普通版日报定时器",
                    CollectionImageJob.class.getCanonicalName(),
                    zeroPerDay);//每天零点


            jobService.addJob(infoBriefingCurrencyDayJob);

            log.info("初始化完成...");
        }

    }
}
