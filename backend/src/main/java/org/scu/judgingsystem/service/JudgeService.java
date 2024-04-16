package org.scu.judgingsystem.service;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.io.File;

public interface JudgeService {
    // 生成AST
    public CompilationUnit parse(File sourceCode);

    // 打印AST
    public boolean judge(Node node1, Node node2);
}
