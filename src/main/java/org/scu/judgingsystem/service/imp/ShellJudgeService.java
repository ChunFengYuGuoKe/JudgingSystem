package org.scu.judgingsystem.service.imp;

import com.github.javaparser.ast.CompilationUnit;
import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.stereotype.Service;

import java.io.File;


public class ShellJudgeService implements JudgeService {
    @Getter
    private static final ShellJudgeService shellJudgeService = new ShellJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        return null;
    }

    @Override
    public void printAST() {

    }
}
