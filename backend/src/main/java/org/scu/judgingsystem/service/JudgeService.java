package org.scu.judgingsystem.service;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.result.black_box_result.BlackBoxResult;

import java.io.File;
import java.util.Map;

public abstract class JudgeService {
    // 生成AST，
    public abstract CompilationUnit parse(File sourceCode);

    // 打印AST
    public abstract boolean analyse(Node node1, Node node2);

    // 模板方法
    public Result<Map<String, String>> judge(File sourceCode, CompilationUnit indicativeAST) {
        CompilationUnit ast = parse(sourceCode);

        analyse(ast, indicativeAST);
        return null;
    }

    /**
     * 黑盒测试
     * @param sourceCode 源代码文件对象
     */
    public abstract BlackBoxResult blackBoxTest(File sourceCode);
}
