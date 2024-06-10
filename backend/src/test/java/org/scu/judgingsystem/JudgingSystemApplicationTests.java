package org.scu.judgingsystem;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.scu.judgingsystem.result.black_box_result.BlackBoxResult;
import org.scu.judgingsystem.service_creator.JudgeServiceCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@Slf4j
class JudgingSystemApplicationTests {

//    private JudgeService judgeService;
//
//    @Autowired
//    public void setJudgeService(JudgeService judgeService) {
//        this.judgeService = judgeService;
//    }
//
//    @Test
//    void testJudge() {
//         CompilationUnit helloAST = judgeService.parse(new File("C:\\Users\\minmin\\Desktop\\hello.java"));
//         JavaJudgeService.printAST(helloAST);
//         helloAST.accept(new VoidVisitorAdapter<>() {
//
//         }, new Object());
//        // CompilationUnit hiAST = judgeService.parse(new File("C:\\Users\\minmin\\Desktop\\hi.java"));
//        // System.out.println(judgeService.judge(helloAST, hiAST));
//    }
//
//    /**
//     * 测试黑盒测试方法
//     */
//    @Test
//    void testBlackBox() {
//        String path = JavaJudgeService.getPath();
//        File file = new File(path);
//        BlackBoxResult blackBoxResult = judgeService.blackBoxTest(file);
//        log.info(blackBoxResult.getReturnVal().toString());
//    }
//
//    @Test
//    void testJudgeCreator(int type) {
//        // 1. 根据类型创建具体工厂类
//        JudgeServiceCreator judgeServiceCreator = JudgeServiceCreator.getJudgeServiceCreator(type);
//
//        // 2. 调用工厂类的工厂方法创建服务对象
//        JudgeService judgeService1 = judgeServiceCreator.getJudgeService();
//
//
//        File sourceFile = new File("C:\\Users\\minmin\\Desktop\\hello.java");
//        CompilationUnit helloAST = judgeService.parse(sourceFile);
//
//        // 3. 调用判题服务类的判题方法
//        judgeService1.judge(sourceFile, helloAST);
//    }
}
