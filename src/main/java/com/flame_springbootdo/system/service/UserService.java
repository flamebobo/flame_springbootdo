package com.flame_springbootdo.system.service;

import com.flame_springbootdo.common.domain.Tree;
import com.flame_springbootdo.system.domain.DeptDO;
import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 *
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-18 16:49:00
 */
@Service
public interface UserService {
    UserDO get(Long id);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long userId);

    int batchremove(Long[] userIds);

    boolean exit(Map<String, Object> params);

    Set<String> listRoles(Long userId);

    int resetPwd(UserVO userVO,UserDO userDO) throws Exception;
    int adminResetPwd(UserVO userVO) throws Exception;
    Tree<DeptDO> getTree();

    /**
     * 更新个人信息
     * @param userDO
     * @return
     */
    int updatePersonal(UserDO userDO);

    /**
     * 更新个人图片
     * @param file 图片
     * @param avatar_data 裁剪信息
     * @param userId 用户ID
     * @throws Exception
     */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
