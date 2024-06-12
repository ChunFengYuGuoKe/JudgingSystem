package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Plugin;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PluginController {
    @Autowired
    private PluginService pluginService;

    /**
     * 5.2 获取已有插件信息
     */
    @GetMapping("/plugin")
    public Result getAvailablePlugins() {
        List<Plugin> plugins = pluginService.getAll();
        return Result.success(plugins);
    }
}
