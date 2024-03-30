package org.scu.judgingsystem.service.imp;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;



public class JavaJudgeService implements JudgeService {
    @Getter
    private static final JavaJudgeService javaJudgeService = new JavaJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        try {
            // 创建javaparser
            JavaParser javaParser = new JavaParser();
            // 使用javaparser解析源码得到封装的抽象语法树
            ParseResult<CompilationUnit> ast = javaParser.parse(sourceCode);
            // 获取抽象语法树
            CompilationUnit cu;
            // 判断是否存在
            if (ast.getResult().isPresent()) {
                cu = ast.getResult().get();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void printAST() {

    }


}
