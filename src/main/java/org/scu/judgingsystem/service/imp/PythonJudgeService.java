package org.scu.judgingsystem.service.imp;


import com.github.javaparser.ast.CompilationUnit;
import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.stereotype.Service;

import java.io.File;



public class PythonJudgeService implements JudgeService {
    @Getter
    private static final PythonJudgeService pythonJudgeService = new PythonJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        return null;
    }

    @Override
    public void printAST() {

    }
}
