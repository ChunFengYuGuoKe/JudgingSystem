package org.scu.judgingsystem.controller.student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.scu.judgingsystem.pojo.SubmitRequest;
import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 作为测率模式中的环境类
@RestController
@RequestMapping("/student/judge")
@Api(tags = "判题相关接口")
@Slf4j
public class JudgeController {

    @PostMapping
    @ApiOperation("提交解答")
    public void judge(@RequestBody SubmitRequest submitRequest) {
        // astService = astProperties.getASTFullName();
        getJudgeService(submitRequest);
    }

    /**
     * 用工厂创建JudgeService
     * @param submitRequest 用户提交的请求
     */
    private static void getJudgeService(SubmitRequest submitRequest) {
        // 1.首先获取参数里的类型值
        int typeValue = submitRequest.getType();

        // 2.通过JudgeServiceCreator创建creator对象
        JudgeServiceCreator judgeServiceCreator = JudgeServiceCreator.getJudgeServiceCreator(typeValue);

        // 3.用creator对象创建JudgeService对象
        // 抽象策略类
        JudgeService judgeService = judgeServiceCreator.getJudgeService();
    }
}
