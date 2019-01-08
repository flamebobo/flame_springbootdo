package com.flame_springbootdo.common.service;

import com.flame_springbootdo.common.domain.TaskDO;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/12/8 12:23
 * @Version 1.0
 */
public interface JobService {
    TaskDO get(Long id);

    List<TaskDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TaskDO task);

    int update(TaskDO task);

    int remove(Long id);

    int batchRemove(Long[] ids);

    void changeStatus(Long jobId, String cmd) throws SchedulerException;

    void initSchedule() throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;
}
