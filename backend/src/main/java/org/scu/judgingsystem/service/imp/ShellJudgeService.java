package org.scu.judgingsystem.service.imp;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import lombok.Getter;
import org.scu.judgingsystem.reulst.Result;
import org.scu.judgingsystem.service.JudgeService;

import java.io.File;


public class ShellJudgeService extends JudgeService {
    @Getter
    private static final ShellJudgeService shellJudgeService = new ShellJudgeService();

    @Override
    public CompilationUnit parse(File sourceCode) {
        return null;
    }

    @Override
    public boolean analyse(Node node1, Node node2) {
        return false;
    }
}
