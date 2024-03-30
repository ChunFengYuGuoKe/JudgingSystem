package org.scu.judgingsystem.service_accessor.imp;

import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service.imp.ShellJudgeService;
import org.scu.judgingsystem.service_accessor.ServiceCreator;

public class ShellJudgeServiceCreator extends ServiceCreator {


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
