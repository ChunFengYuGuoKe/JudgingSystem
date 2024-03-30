package org.scu.judgingsystem.enums;

// 枚举各解析器的全类名
public enum CreatorClassNameEnum {
    JAVA("org.scu.judgingsystem.service_accessor.imp.JavaJudgeServiceCreator"),
    PYTHON("org.scu.judgingsystem.service_accessor.imp.PythonJudgeServiceCreator"),
    SHELL("org.scu.judgingsystem.service_accessor.imp.ShellJudgeServiceCreator");

    private String className;

    private CreatorClassNameEnum(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
