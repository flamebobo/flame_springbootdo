package com.flame_springbootdo.system.service.impl;

import com.flame_springbootdo.common.domain.Tree;
import com.flame_springbootdo.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flame_springbootdo.system.dao.DeptDao;
import com.flame_springbootdo.system.domain.DeptDO;
import com.flame_springbootdo.system.service.DeptService;



@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public DeptDO get(Long deptId){
        return deptDao.get(deptId);
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map){
        return deptDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return deptDao.count(map);
    }

    @Override
    public int save(DeptDO dept){
        return deptDao.save(dept);
    }

    @Override
    public int update(DeptDO dept){
        return deptDao.update(dept);
    }

    @Override
    public int remove(Long deptId){
        return deptDao.remove(deptId);
    }

    @Override
    public int batchRemove(Long[] deptIds){
        return deptDao.batchRemove(deptIds);
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> sysDepts = deptDao.list(new HashMap<String,Object>(16));
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public boolean checkDeptHasUser(Long deptId) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = deptDao.getDeptUserNumber(deptId);
        return result==0?true:false;
    }
}
