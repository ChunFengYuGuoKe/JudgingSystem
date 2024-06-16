package org.scu.black_box_result;


public class BlackBoxResult {
    // 当题目有返回值时，result保存返回值
    // 当题目没有返回值时，result保存输出字符串
    // private Object result;
    private Integer score;
    private String failedUseCase;

    public BlackBoxResult(Integer score, String failedUseCase) {
        this.score = score;
        this.failedUseCase = failedUseCase;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFailedUseCase() {
        return failedUseCase;
    }

    public void setFailedUseCase(String failedUseCase) {
        this.failedUseCase = failedUseCase;
    }
}