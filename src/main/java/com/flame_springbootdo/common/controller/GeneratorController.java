package com.flame_springbootdo.common.controller;

import com.flame_springbootdo.common.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/10/16 10:19
 * @Version 1.0
 */
@Controller
@RequestMapping("/common/generator")
public class GeneratorController {
    String prefix = "common/generator";

    @Autowired
    private GeneratorService generatorService;

    @GetMapping
    String generator(){
        return prefix + "/list";
    }

    @ResponseBody
    @GetMapping("/list")
    List<Map<String, Object>> list(){
        List<Map<String, Object>> list = generatorService.list();
        return list;
    }

    @RequestMapping("/code/{tableName}")
    public void code(HttpServletRequest request, HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        String[] tableNames = new String[]{ tableName };
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"flame.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
