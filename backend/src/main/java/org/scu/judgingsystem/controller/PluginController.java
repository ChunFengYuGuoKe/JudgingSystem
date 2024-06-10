package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Plugin;
import org.scu.judgingsystem.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PluginController {

    /**
     * 5.2 获取已有插件信息
     */
    @GetMapping("/plugin")
    public Result availablePlugins() {
        List<Plugin> plugins = new ArrayList<>();
        plugins.add(new Plugin("Java", 1));
        plugins.add(new Plugin("Python", 2));
        plugins.add(new Plugin("Shell", 3));

        return Result.success(plugins);
    }
}
