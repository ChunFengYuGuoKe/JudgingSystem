package org.scu.judgingsystem.service.imp;


import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import lombok.Getter;
import org.scu.judgingsystem.service.JudgeService;

import java.io.File;



public class PythonJudgeService implements JudgeService {
    @Getter
    private static final PythonJudgeService pythonJudgeService = new PythonJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        return null;
    }

    @Override
    public boolean judge(Node node1, Node node2) {
        return false;
    }
}
