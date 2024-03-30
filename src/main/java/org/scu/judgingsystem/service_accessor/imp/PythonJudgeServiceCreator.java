package org.scu.judgingsystem.service_accessor.imp;

import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service.imp.PythonJudgeService;
import org.scu.judgingsystem.service_accessor.ServiceCreator;

public class PythonJudgeServiceCreator extends ServiceCreator {
    @Override
    public JudgeService getJudgeService() {
        return PythonJudgeService.getPythonJudgeService();
    }
}
