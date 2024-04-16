package org.scu.judgingsystem.service.imp;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;


@Service
public class JavaJudgeService implements JudgeService {
    @Getter
    private static final JavaJudgeService javaJudgeService = new JavaJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        CompilationUnit cu = null;
        while (cu == null) {
            try {
                // 1.创建javaparser
                JavaParser javaParser = new JavaParser();
                // 2.使用javaparser解析源码得到封装的抽象语法树
                ParseResult<CompilationUnit> ast = javaParser.parse(sourceCode);
                // System.out.println(ast.toString());
                // 3.获取抽象语法树

                // 4.判断是否存在
                if (ast.getResult().isPresent()) {
                    cu = ast.getResult().get();
                    System.out.println(ast.getResult());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return cu;
    }

    @Override
    public boolean judge(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 == node2;
        }
        if (!node1.getClass().equals(node2.getClass())) {
            return false;
        }
        for (Node child1 : node1.getChildNodes()) {
            boolean foundMatch = false;
            for (Node child2 : node2.getChildNodes()) {
                if (judge(child1, child2)) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }
        return true;
    }


}
