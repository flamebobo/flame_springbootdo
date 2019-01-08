package com.flame_springbootdo.common.dao;

import com.flame_springbootdo.common.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/12/8 13:50
 * @Version 1.0
 */
@Mapper
public interface TaskDao {
    TaskDO get(Long id);

    List<TaskDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(TaskDO task);

    int update(TaskDO task);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
