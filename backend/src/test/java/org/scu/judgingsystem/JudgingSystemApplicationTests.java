package org.scu.judgingsystem;

import com.github.javaparser.ast.CompilationUnit;
import org.junit.jupiter.api.Test;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class JudgingSystemApplicationTests {

    private JudgeService judgeService;

    @Autowired
    public void setJudgeService(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @Test
    void testJudge() {
        CompilationUnit helloAST = judgeService.parse(new File("C:\\Users\\minmin\\Desktop\\hello.java"));
        CompilationUnit hiAST = judgeService.parse(new File("C:\\Users\\minmin\\Desktop\\hi.java"));
        System.out.println(judgeService.judge(helloAST, hiAST));
    }

}
