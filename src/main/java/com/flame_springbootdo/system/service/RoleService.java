package com.flame_springbootdo.system.service;

import com.flame_springbootdo.system.domain.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-19 10:43:59
 */

@Service
public interface RoleService {

    RoleDO get(Long id);

    List<RoleDO> list();

    int save(RoleDO role);

    int update(RoleDO role);

    int remove(Long id);

    List<RoleDO> list(Long userId);

    int batchremove(Long[] ids);
}

