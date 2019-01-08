package com.flame_springbootdo.common.service.impl;

import com.flame_springbootdo.common.controller.BaseController;
import com.flame_springbootdo.common.dao.DictDao;
import com.flame_springbootdo.common.domain.DictDO;
import com.flame_springbootdo.common.service.DictService;
import com.flame_springbootdo.system.dao.UserDao;
import com.flame_springbootdo.system.domain.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Flame
 * @Date 2018/10/11 16:40
 * @Version 1.0
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<DictDO> list(Map<String, Object> map) {
        return dictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public List<DictDO> listType() {
        return dictDao.listType();
    }

    @Override
    public int save(DictDO dict) {
        return dictDao.save(dict);

    }

    public int update(DictDO dict){
        return dictDao.update(dict);
    }

    @Override
    public DictDO get(Long id) {
        return dictDao.get(id);
    }

    @Override
    public int remove(Long id) {
        return dictDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return dictDao.batchRemove(ids);
    }

    @Override
    public List<DictDO> getHobbyList(UserDO userDO) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "hobby");
        List<DictDO> hobbyList = dictDao.list(param);

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictDO hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<DictDO> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return dictDao.list(param);
    }


}
