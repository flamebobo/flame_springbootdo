package com.flame_springbootdo.system.service.impl;

import com.flame_springbootdo.common.domain.Tree;
import com.flame_springbootdo.common.utils.MD5Utils;
import com.flame_springbootdo.system.dao.DeptDao;
import com.flame_springbootdo.system.dao.UserRoleDao;
import com.flame_springbootdo.system.domain.DeptDO;
import com.flame_springbootdo.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flame_springbootdo.system.dao.UserDao;
import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userMapper;

    @Autowired
    private UserRoleDao userRoleMapper;

    @Autowired
    private DeptDao deptMapper;

    @Override
    public UserDO get(Long userId){
        UserDO user = userMapper.get(userId);
        if(user.getDeptId() != null){
            user.setDeptName(deptMapper.get(user.getDeptId()).getName());
        }
        List<Long> roleIds =userRoleMapper.listRoleId(userId);
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public List<UserDO> list(Map<String, Object> map){
        return userMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return userMapper.count(map);
    }

    @Override
    public int save(UserDO user){
        return userMapper.save(user);
    }

    @Override
    public int update(UserDO user){
        return userMapper.update(user);
    }

    @Override
    public int remove(Long userId){
        return userMapper.remove(userId);
    }

    @Transactional
    @Override
    public int batchremove(Long[] userIds){
        int count = userMapper.batchRemove(userIds);
        userRoleMapper.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
        return 0;
    }

    @Override
    public int adminResetPwd(UserVO userVO) throws Exception {
        UserDO userDO = get(userVO.getUserDO().getUserId());
        if ("admin".equals(userDO.getUsername())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
        return userMapper.update(userDO);
    }


    @Override
    public Tree<DeptDO> getTree() {
        return null;
    }

    @Override
    public int updatePersonal(UserDO userDO) {
        return userMapper.update(userDO);
    }

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
        return null;
    }

}
