package com.flame_springbootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.flame_springbootdo.common.utils.PageUtils;
import com.flame_springbootdo.common.utils.Query;
import com.flame_springbootdo.common.utils.R;
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

import com.flame_springbootdo.system.domain.LogDO;
import com.flame_springbootdo.system.service.LogService;


/**
 * 系统日志
 *
 * @author Flame
 * @email 994810552@qq.com
 * @date 2018-10-18 11:03:41
 */

@Controller
@RequestMapping("/common/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping()
    @RequiresPermissions("common:log:log")
    String Log(){
        return "/system/log/log";
    }

    /**
    * 查询列表
    * @param params
    * @return
    */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("common:log:log")
    public PageUtils list(@RequestParam Map<String,Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<LogDO> logList = logService.list(query);
        int total = logService.count(query);
        PageUtils pageUtils = new PageUtils(logList, total);
        return pageUtils;
    }

    /**
    * 新增
    * @return
    */
    @GetMapping("/add")
    @RequiresPermissions("common:log:add")
    String add(){
        return "system/log/add";
    }

    /**
    * 修改
    * @param model
    * @return
    */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("common:log:edit")
    String edit(Model model, @PathVariable("id") Long id){
        LogDO log = logService.get(id);
        model.addAttribute("log", log);
        return "system/log/edit";
    }


    /**
    * 保存
    * @return
    */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:log:add")
    public R save(LogDO log){
        if (logService.save(log) > 0) {
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
    @RequiresPermissions("common:log:edit")
    public R update(LogDO log){
        if (logService.update(log) > 0 ) {
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
    @RequiresPermissions("common:log:remove")
    public R remove(@RequestParam Long id){
        if (logService.remove(id) > 0 ) {
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
    @RequiresPermissions("common:log:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids){
        if (logService.batchRemove(ids) > 0 ) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @PostMapping("/search")
    public R search(@RequestParam("userName") String userName){
        if(userName != null && userName != ""){
            logService.search(userName);
            return R.ok();
        }
       return R.error("参数不能为空");
    }

}