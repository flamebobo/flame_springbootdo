package com.flame_springbootdo.common.controller;

import com.flame_springbootdo.common.annotation.Log;
import com.flame_springbootdo.common.config.Constant;
import com.flame_springbootdo.common.domain.DictDO;
import com.flame_springbootdo.common.service.DictService;
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

/**
 * @Author Flame
 * @Date 2018/10/11 16:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/common/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("common:dict:dict")
    String dict() {
        return "common/dict/dict";
    }

    @Log("获取数据字典列表")
    @GetMapping("/list")
    @ResponseBody
    @RequiresPermissions("common:dict:dict")
    //在SpringMVC后台控制层获取参数的方式主要有两种:
    //
    //一种是request.getParameter("name")，另外一种是用注解@RequestParam直接获取
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<DictDO> dictList = dictService.list(query);
        int total = dictService.count(query);
        PageUtils pageUtils = new PageUtils(dictList,total);
        return pageUtils;
    }

    @GetMapping("/type")
    @ResponseBody
    public List<DictDO> listType(){
        return dictService.listType();
    }

    @GetMapping("/add")
    @RequiresPermissions("common:dict:add")
    String add(){
        return "common/dict/add";
    }

    @Log("添加数据字典")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:dict:add")
    public R save(DictDO dict) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1,"演示系统不允许修改，请打$10000到支付宝：13192294607，帮你修改");
        }
        if (dictService.save(dict) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/add/{type}/{description}")
    @RequiresPermissions("common:dict:add")
    //@PathVariable是用来获得请求url中的动态参数的
    String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description){
        model.addAttribute("type",type);
        model.addAttribute("description",description);
        return "common/dict/add";
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("common:dict:edit")
    public R update(DictDO dict){
        dictService.update(dict);
        return R.ok();
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("common:dict:edit")
    String edit(Model model, @PathVariable("id") Long id){
        DictDO dict = dictService.get(id);
        model.addAttribute("dict",dict);
        return "common/dict/edit";
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("common:dict:remove")
    public R remove(@RequestParam Long id){
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1,"演示系统不允许修改，请打$10000到支付宝：13192294607，帮你修改");
        }
        if (dictService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:dict:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids){
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1,"演示系统不允许修改，请打$10000到支付宝：13192294607，帮你修改");
        }
        if (dictService.batchRemove(ids) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
