package org.scu.judgingsystem.service_creator.imp;

import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service.imp.ShellJudgeService;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;

public class ShellJudgeServiceCreator extends JudgeServiceCreator {

    @Getter
    private static final JudgeServiceCreator judgeServiceCreator = new ShellJudgeServiceCreator();
    // 用autowired自动装配不行，因为要Accessor是component才行
    // 所以还是老老实实用单例模式吧
    // @Autowired
    // private void setJudgeService(JudgeService judgeService) {
    //     this.judgeService = judgeService;
    // }

    @Override
    public JudgeService getJudgeService() {
        return ShellJudgeService.getShellJudgeService();
    }
}
