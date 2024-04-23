package org.scu.judgingsystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc   // json自动类型转换
@Configuration
@ComponentScan({"org.scu.judgingsystem.controller", "org.scu.judgingsystem.config"})
public class SpringMvcConfig {
}
