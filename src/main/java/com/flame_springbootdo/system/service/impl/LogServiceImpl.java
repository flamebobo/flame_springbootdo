package com.flame_springbootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.flame_springbootdo.system.dao.LogDao;
import com.flame_springbootdo.system.domain.LogDO;
import com.flame_springbootdo.system.service.LogService;



@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public LogDO get(Long id){
        return logDao.get(id);
    }

    @Override
    public List<LogDO> list(Map<String, Object> map){
        return logDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return logDao.count(map);
    }

    @Override
    public int save(LogDO log){
        return logDao.save(log);
    }

    @Override
    public int update(LogDO log){
        return logDao.update(log);
    }

    @Override
    public int remove(Long id){
        return logDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids){
        return logDao.batchRemove(ids);
    }

    @Override
    public List<LogDO> search(String userName) {
        return logDao.search(userName);
    }


}
