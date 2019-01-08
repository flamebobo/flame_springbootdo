package com.flame_springbootdo.common.service;


import com.flame_springbootdo.common.domain.FileDO;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/9/29 11:24
 * @Version 1.0
 */
public interface FileService {
	
	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDO sysFile);
	
	int update(FileDO sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
    Boolean isExist(String url);
}
