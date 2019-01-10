package com.flame_springbootdo.system.dao;

import com.flame_springbootdo.system.domain.RoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-19 10:43:59
 */
@Mapper
public interface RoleDao {

    RoleDO get(Long roleId);

    List<RoleDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(RoleDO role);

    int update(RoleDO role);

    int remove(Long roleId);

    int batchRemove(Long[] roleIds);
}

