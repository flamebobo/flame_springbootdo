package com.flame_springbootdo.common.service.impl;

import com.flame_springbootdo.common.config.Constant;
import com.flame_springbootdo.common.dao.TaskDao;
import com.flame_springbootdo.common.domain.ScheduleJob;
import com.flame_springbootdo.common.domain.TaskDO;
import com.flame_springbootdo.common.quartz.utils.QuartzManager;
import com.flame_springbootdo.common.service.JobService;
import com.flame_springbootdo.common.utils.ScheduleJobUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/12/8 13:52
 * @Version 1.0
 */
@Service
public class TaskServiceImpl implements JobService {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskDao taskScheduleJobMapper;

    @Autowired
    QuartzManager quartzManager;


    @Override
    public TaskDO get(Long id){
        return taskDao.get(id);
    }

    @Override
    public List<TaskDO> list(Map<String, Object> map){
        return taskDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return taskDao.count(map);
    }

    @Override
    public int save(TaskDO task){
        return taskDao.save(task);
    }

    @Override
    public int update(TaskDO task){
        return taskDao.update(task);
    }

    @Override
    public int remove(Long id){
        return taskDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids){
        return taskDao.batchRemove(ids);
    }

    @Override
    public void changeStatus(Long jobId, String cmd){
       // return taskDao.changeStatus(jobId, cmd);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TaskDO> jobList = taskScheduleJobMapper.list(new HashMap<String, Object>(16));
        for (TaskDO scheduleJob : jobList) {
            if ("1".equals(scheduleJob.getJobStatus())) {
                ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
                quartzManager.addJob(job);
            }

        }
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {

    }

}
