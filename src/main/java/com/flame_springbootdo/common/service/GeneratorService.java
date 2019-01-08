package com.flame_springbootdo.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Author Flame
 * @Date 2018/10/16 10:29
 * @Version 1.0
 */
public interface GeneratorService {

    List<Map<String, Object>> list();

    byte[] generatorCode(String[] tableNames);
}
