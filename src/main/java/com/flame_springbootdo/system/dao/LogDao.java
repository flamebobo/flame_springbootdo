package com.flame_springbootdo.system.dao;

import com.flame_springbootdo.system.domain.LogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-18 11:03:41
 */
@Mapper
public interface LogDao {

LogDO get(Long id);

    List<LogDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogDO log);

    int update(LogDO log);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<LogDO> search(String userName);
}
