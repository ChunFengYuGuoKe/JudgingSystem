package org.scu;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import org.scu.black_box_result.BlackBoxResult;

import java.io.File;

public interface JudgeService {
    // 生成AST，
    CompilationUnit parse(File sourceCode);

    // 打印AST
    boolean analyse(Node node1, Node node2);

    /**
     * 黑盒测试
     * @param sourceCode 源代码文件对象
     */
    BlackBoxResult blackBoxTest(File sourceCode, Integer homeworkId);

    // 是否支持该语言
    boolean isSupportsLanguage(String language);
}
