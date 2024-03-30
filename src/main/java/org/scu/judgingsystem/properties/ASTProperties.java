package org.scu.judgingsystem.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 读取AST配置的类
@Component
@ConfigurationProperties(prefix = "judging-system.ast-service")
@Data
public class ASTProperties {
    public String ServiceFullName;
}
