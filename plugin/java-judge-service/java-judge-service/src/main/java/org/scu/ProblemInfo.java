package org.scu;

public class ProblemInfo {
    private Integer id;
    private String title;
    private String language;
    // 标识是否有返回值
    private boolean hasReturnValue;
    private String inputPramTypes;
    private String outputParamType;

    public static ProblemInfo getProblemInfo() {
        ProblemInfo problemInfo = new ProblemInfo();
        problemInfo.id = 10;
        problemInfo.title = "sb";
        problemInfo.language = "java";
        problemInfo.hasReturnValue = true;
        problemInfo.inputPramTypes = "java.lang.Integer,java.lang.Integer";
        problemInfo.outputParamType = "int";
        return problemInfo;
    }

    public String getOutputParamType() {
        return outputParamType;
    }

    public void setOutputParamType(String outputParamType) {
        this.outputParamType = outputParamType;
    }

    public String getInputPramTypes() {
        return inputPramTypes;
    }

    public void setInputPramTypes(String inputPramTypes) {
        this.inputPramTypes = inputPramTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHasReturnValue() {
        return hasReturnValue;
    }

    public void setHasReturnValue(boolean hasReturnValue) {
        this.hasReturnValue = hasReturnValue;
    }
}
