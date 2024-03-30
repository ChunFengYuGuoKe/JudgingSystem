package org.scu.judgingsystem.service_creator.imp;

import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service.imp.JavaJudgeService;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;


public class JavaJudgeServiceCreator extends JudgeServiceCreator {
    @Getter
    private static final JudgeServiceCreator judgeServiceCreator = new JavaJudgeServiceCreator();

    @Override
    public JudgeService getJudgeService() {
        return JavaJudgeService.getJavaJudgeService();
    }
}
