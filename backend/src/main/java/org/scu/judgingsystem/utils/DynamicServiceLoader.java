package org.scu.judgingsystem.utils;

import org.scu.JudgeService;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ServiceLoader;

// 加载类，获取服务对象
public class DynamicServiceLoader {
    private ServiceLoader<JudgeService> loader;

    public DynamicServiceLoader() {
        loader = ServiceLoader.load(JudgeService.class);
    }

    // 加载jar插件中的class
    public void loadNewService(String jarPath) throws Exception {
        Path path = Paths.get(jarPath);
        URL[] urls = {path.toUri().toURL()};
        URLClassLoader urlClassLoader = new URLClassLoader(urls, this.getClass().getClassLoader());
        ServiceLoader<JudgeService> newLoader = ServiceLoader.load(JudgeService.class, urlClassLoader);
        for (JudgeService service : newLoader) {
            loader.reload(); // Reload the service loader to include the new services
            for (JudgeService s : loader) {
                // This will now include services from the newly loaded JAR
                System.out.println("Loaded service: " + s.getClass().getName());
            }
        }
    }

    public JudgeService getJudgeService(String language) {
        for (JudgeService service : loader) {
            if (service.isSupportsLanguage(language)) {
                return service;
            }
        }
        throw new IllegalArgumentException("No judge service found for language: " + language);
    }
}

