package org.scu.judgingsystem.service.imp;


import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import lombok.Getter;
import org.scu.judgingsystem.result.black_box_result.BlackBoxResult;
import org.scu.judgingsystem.service.JudgeService;

import java.io.File;



public class PythonJudgeService extends JudgeService {
    @Getter
    private static final PythonJudgeService pythonJudgeService = new PythonJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        return null;
    }

    @Override
    public boolean analyse(Node node1, Node node2) {
        return false;
    }

    @Override
    public BlackBoxResult blackBoxTest(File sourceCode) {
        return null;
    }
}
