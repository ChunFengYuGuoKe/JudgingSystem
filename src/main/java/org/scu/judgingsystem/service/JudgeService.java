package org.scu.judgingsystem.service;

import com.github.javaparser.ast.CompilationUnit;

import java.io.File;

public interface JudgeService {
    // 生成AST
    public CompilationUnit parse(File sourceCode);

    // 打印AST
    public void judge();
}
