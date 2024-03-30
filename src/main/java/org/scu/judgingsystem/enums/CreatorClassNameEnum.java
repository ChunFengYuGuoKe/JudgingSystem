package org.scu.judgingsystem.enums;

import lombok.Getter;

// 枚举各解析器的全类名
@Getter
public enum CreatorClassNameEnum {
    JAVA("org.scu.judgingsystem.service_creator.imp.JavaJudgeServiceCreator"),
    PYTHON("org.scu.judgingsystem.service_creator.imp.PythonJudgeServiceCreator"),
    SHELL("org.scu.judgingsystem.service_creator.imp.ShellJudgeServiceCreator");

    private final String className;

    CreatorClassNameEnum(String className) {
        this.className = className;
    }

}
