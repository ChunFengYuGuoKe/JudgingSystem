package org.scu.judgingsystem.pojo;


import lombok.Data;

@Data
public class SubmitRequest {
    public static final int JAVA_TYPE = 1;
    public static final int PYTHON_TYPE = 2;
    public static final int SHELL_TYPE = 3;

    private String sourceCode;
    private int type;
}
