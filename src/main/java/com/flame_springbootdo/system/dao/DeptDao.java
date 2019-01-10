package com.flame_springbootdo.system.dao;

import com.flame_springbootdo.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 部门管理
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-23 16:53:17
 */
@Mapper
public interface DeptDao {

    DeptDO get(Long deptId);

    List<DeptDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DeptDO dept);

    int update(DeptDO dept);

    int remove(Long dept_id);

    int batchRemove(Long[] deptIds);

    int getDeptUserNumber(Long deptId);
}
