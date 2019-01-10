package com.flame_springbootdo.system.service;

import com.flame_springbootdo.common.domain.Tree;
import com.flame_springbootdo.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-23 16:53:17
 */
public interface DeptService {

    DeptDO get(Long deptId);

    List<DeptDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DeptDO dept);

    int update(DeptDO dept);

    int remove(Long deptId);

    int batchRemove(Long[] deptIds);

    Tree<DeptDO> getTree();

    boolean checkDeptHasUser(Long deptId);
}
