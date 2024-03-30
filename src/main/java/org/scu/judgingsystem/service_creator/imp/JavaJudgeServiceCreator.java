package org.scu.judgingsystem.service_creator.imp;

import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service.imp.JavaJudgeService;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;


public class JavaJudgeServiceCreator extends JudgeServiceCreator {
    @Override
    public JudgeService getJudgeService() {
        return JavaJudgeService.getJavaJudgeService();
    }
}
