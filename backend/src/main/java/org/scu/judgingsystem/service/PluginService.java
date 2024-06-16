package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Plugin;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PluginService {
    /**
     * 获取已有插件信息
     * @return 插件列表
     */
    List<Plugin> getAll();
}
