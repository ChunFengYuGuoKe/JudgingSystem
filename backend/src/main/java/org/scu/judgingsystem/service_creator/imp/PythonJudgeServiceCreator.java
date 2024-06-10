package org.scu.judgingsystem.service_creator.imp;

import lombok.Getter;

import org.scu.JudgeService;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;

public class PythonJudgeServiceCreator extends JudgeServiceCreator {
    @Getter
    private static final JudgeServiceCreator judgeServiceCreator = new PythonJudgeServiceCreator();
    @Override
    public JudgeService getJudgeService() {
        return null;
    }
}
