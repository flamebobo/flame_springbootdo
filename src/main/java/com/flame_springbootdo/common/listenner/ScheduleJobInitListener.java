package com.flame_springbootdo.common.listenner;

import com.flame_springbootdo.common.quartz.utils.QuartzManager;
import com.flame_springbootdo.common.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Flame
 * @Date 2018/12/10 11:58
 * @Version 1.0
 */
@Component
public class ScheduleJobInitListener implements CommandLineRunner {
    @Autowired
    JobService scheduleJobService;

    @Autowired
    QuartzManager quartzManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            scheduleJobService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
