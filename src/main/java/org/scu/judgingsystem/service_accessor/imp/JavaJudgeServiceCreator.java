package org.scu.judgingsystem.service_accessor.imp;

import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service.imp.JavaJudgeService;
import org.scu.judgingsystem.service_accessor.ServiceCreator;


public class JavaJudgeServiceCreator extends ServiceCreator {
    @Override
    public JudgeService getJudgeService() {
        return JavaJudgeService.getJavaJudgeService();
    }
}
