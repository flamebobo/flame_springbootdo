package com.flame_springbootdo.system.service;

import com.flame_springbootdo.system.domain.LogDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-18 11:03:41
 */
public interface LogService {

    LogDO get(Long id);

    List<LogDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogDO log);

    int update(LogDO log);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<LogDO> search(String userName);
}
