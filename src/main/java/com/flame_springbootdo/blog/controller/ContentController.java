package com.flame_springbootdo.blog.controller;

import com.flame_springbootdo.blog.domain.ContentDO;
import com.flame_springbootdo.blog.service.ContentService;
import com.flame_springbootdo.common.config.Constant;
import com.flame_springbootdo.common.controller.BaseController;
import com.flame_springbootdo.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author Flame
 * @Date 2018/10/31 15:29
 * @Version 1.0
 */
@Controller
@RequestMapping("/blog/bContent")
public class ContentController extends BaseController {

    @Autowired
    private ContentService contentService;

    @GetMapping()
    @RequiresPermissions("blog:bContent:bContent")
    String bContent() {
        return "blog/bContent/bContent";
    }

    @GetMapping("/add")
    @RequiresPermissions("blog:bContent:add")
    String add() {
        return "blog/bContent/add";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("blog:bContent:add")
    public R save(ContentDO contentDo){
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1,"系统不允许，要用请打钱到支付宝999999999");
        }
        //开启评论
        if (contentDo.getAllowComment() == null) {
            contentDo.setAllowComment(0);
        }
        //允许反馈
        if (contentDo.getAllowFeed() == null) {
            contentDo.setAllowFeed(0);
        }
        if (contentDo.getType() == null) {
            contentDo.setType("article");
        }
        contentDo.setGtmCreate(new Date());
        contentDo.setGtmModified(new Date());
        int count;
        if (contentDo.getCid() == null || "".equals(contentDo.getCid())) {
            count = contentService.save(contentDo);
        } else {
            count = contentService.update(contentDo);
        }
        if (count > 0) {
            return R.ok().put("cid",contentDo.getCid());
        }
        return R.error();
    }
}
