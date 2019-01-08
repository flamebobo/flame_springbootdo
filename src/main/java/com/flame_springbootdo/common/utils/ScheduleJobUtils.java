package com.flame_springbootdo.common.utils;

import com.flame_springbootdo.common.domain.ScheduleJob;
import com.flame_springbootdo.common.domain.TaskDO;

/**
 * @Author Flame
 * @Date 2018/12/10 12:12
 * @Version 1.0
 */
public class ScheduleJobUtils {

    public static ScheduleJob entityToData(TaskDO scheduleJobEntity) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
        scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
        scheduleJob.setDescription(scheduleJobEntity.getDescription());
        scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
        scheduleJob.setJobName(scheduleJobEntity.getJobName());
        scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
        scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
        scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
        scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
        return scheduleJob;
    }
}
