package org.scu.judgingsystem.service_creator.imp;

import lombok.Getter;
import org.scu.JavaJudgeService;
import org.scu.JudgeService;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;


public class JavaJudgeServiceCreator extends JudgeServiceCreator {
    @Getter
    private static final JudgeServiceCreator judgeServiceCreator = new JavaJudgeServiceCreator();

    @Override
    public JudgeService getJudgeService() {
        return JavaJudgeService.getJavaJudgeService();
    }
}
