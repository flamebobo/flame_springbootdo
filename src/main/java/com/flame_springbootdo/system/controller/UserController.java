package com.flame_springbootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.flame_springbootdo.common.annotation.Log;
import com.flame_springbootdo.common.config.Constant;
import com.flame_springbootdo.common.controller.BaseController;
import com.flame_springbootdo.common.service.DictService;
import com.flame_springbootdo.common.utils.PageUtils;
import com.flame_springbootdo.common.utils.Query;
import com.flame_springbootdo.common.utils.R;
import com.flame_springbootdo.system.domain.RoleDO;
import com.flame_springbootdo.system.service.RoleService;
import com.flame_springbootdo.system.vo.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flame_springbootdo.system.domain.UserDO;
import com.flame_springbootdo.system.service.UserService;


/**
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-18 16:49:00
 */

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    DictService dictService;

    @GetMapping("")
    @RequiresPermissions("sys:user:user")
    String User(){
        return "/system/user/user";
    }

    /**
    * 查询列表
    * @param params
    * @return
    */
    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String,Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<UserDO> userList = userService.list(query);
        int total = userService.count(query);
        PageUtils pageUtils = new PageUtils(userList, total);
        return pageUtils;
    }

    /**
    * 新增
    * @return
    */
    @GetMapping("/add")
    @Log("添加用户")
    @RequiresPermissions("sys:user:add")
    String add(Model model){
        List<RoleDO> roles = roleService.list();
        model.addAttribute("roles",roles);
        return "system/user/add";
    }

    /**
    * 修改
    * @param model
    * @return
    */
    @GetMapping("/edit/{id}")
    @Log("编辑用户")
    @RequiresPermissions("sys:user:edit")
    String edit(Model model, @PathVariable("id") Long id){
        UserDO user = userService.get(id);
        model.addAttribute("user", user);
        List<RoleDO> roles = roleService.list(id);
        model.addAttribute("roles", roles);
        return "system/user/edit";
    }

    /**
    * 保存
    * @return
    */
    @ResponseBody
    @Log("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:add")
    public R save(UserDO user){
        if (userService.save(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
    * 修改
    * @return
    */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:edit")
    public R update(UserDO user){
        if (userService.update(user) > 0 ) {
            return R.ok();
        }
        return R.error();
    }

    /**
    *  删除
    * @return
    */
    @ResponseBody
    @PostMapping("/romove")
    @RequiresPermissions("sys:user:remove")
    public R remove(@RequestParam Long userId){
        if (userService.remove(userId) > 0 ) {
            return R.ok();
        }
        return R.error();
    }

    /**
    * 批量删除
    * @return
    */
    @ResponseBody
    @PostMapping("/batchRemove")
    @RequiresPermissions("sys:user:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] userIds){
        if (userService.batchremove(userIds) > 0 ) {
            return R.ok();
        }
        return R.error();
    }


    @PostMapping("/exit")
    @ResponseBody
    boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exit(params);
    }

    @RequiresPermissions("sys:user:resetPwd")
    @Log("请求更改用户密码")
    @GetMapping("/resetPwd/{id}")
    String resetPwd(@PathVariable("id") Long userId, Model model) {

        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        model.addAttribute("user", userDO);
        return "system/user/reset_pwd";
    }

    @RequiresPermissions("sys:user:resetPwd")
    @Log("admin提交更改用户密码")
    @PostMapping("/adminResetPwd")
    @ResponseBody
    R adminResetPwd(UserVO userVO) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        try{
            userService.adminResetPwd(userVO);
            return R.ok();
        }catch (Exception e){
            return R.error(1,e.getMessage());
        }

    }

    @GetMapping("/personal")
    String personal(Model model) {
        UserDO userDO  = userService.get(getUserId());
        model.addAttribute("user",userDO);
        model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
        model.addAttribute("sexList",dictService.getSexList());
        return "system/user/personal";
    }

    @PostMapping("/updatePeronal")
    @ResponseBody
    @RequiresPermissions("sys:user:edit")
    @Log("更新用户")
    public R updatePeronal(UserDO user){
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error("演示系统不给修改，请联系管理员Flame");
        }
        if (userService.updatePersonal(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

}