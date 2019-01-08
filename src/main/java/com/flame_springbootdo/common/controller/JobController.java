package com.flame_springbootdo.common.controller;

import com.flame_springbootdo.common.domain.TaskDO;
import com.flame_springbootdo.common.service.JobService;
import com.flame_springbootdo.common.utils.PageUtils;
import com.flame_springbootdo.common.utils.Query;
import com.flame_springbootdo.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * @Author Flame
 * @Date 2018/12/8 12:21
 * @Version 1.0
 */
@Controller()
@RequestMapping("/common/job")
public class JobController extends BaseController {

    @Autowired
    private JobService jobScheduleService;

    @GetMapping()
    @RequiresPermissions("common:taskScheduleJob")
    String Task(){
        return "/common/job/job";
    }

    /**
     * 查询列表
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("system:task:task")
    public PageUtils list(@RequestParam Map<String,Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<TaskDO> taskList = jobScheduleService.list(query);
        int total = jobScheduleService.count(query);
        PageUtils pageUtils = new PageUtils(taskList, total);
        return pageUtils;
    }

    /**
     * 新增
     * @return
     */
    @GetMapping("/add")
    //@RequiresPermissions("system:task:add")
    String add(){
        return "/common/job/add";
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    //@RequiresPermissions("system:task:edit")
    String edit(Model model, @PathVariable("id") Long id){
        TaskDO task = jobScheduleService.get(id);
        model.addAttribute("task", task);
        return "/common/job/edit";
    }

    /**
     * 保存
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("system:task:add")
    public R save(TaskDO task){
        if (jobScheduleService.save(task) > 0) {
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
    //@RequiresPermissions("system:task:edit")
    public R update(TaskDO task){
        if (jobScheduleService.update(task) > 0 ) {
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
    //@RequiresPermissions("system:task:remove")
    public R remove(@RequestParam Long id){
        if (jobScheduleService.remove(id) > 0 ) {
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
    //@RequiresPermissions("system:task:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids){
        if (jobScheduleService.batchRemove(ids) > 0 ) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/changeJobStatus")
    public R changeJobStatus(Long id, String cmd){
        String label = "停止";
        if ("start".equals(cmd)) {
            label = "启动";
        } else {
            label = "停止";
        }
        try {
            jobScheduleService.changeStatus(id, cmd);
            return R.ok("任务" + label + "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("任务" + label + "失败");
    }
}


