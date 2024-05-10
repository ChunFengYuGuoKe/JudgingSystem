package org.scu.judgingsystem.service.imp;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.DotPrinter;
import lombok.Getter;
import org.scu.judgingsystem.result.black_box_result.BlackBoxResult;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


@Service
public class JavaJudgeService extends JudgeService {
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
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return cu;
    }

    @Override
    public boolean analyse(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 == node2;
        }
        if (!node1.getClass().equals(node2.getClass())) {
            return false;
        }
        for (Node child1 : node1.getChildNodes()) {
            boolean foundMatch = false;
            for (Node child2 : node2.getChildNodes()) {
                if (analyse(child1, child2)) {
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

    /**
     * 黑盒测试
     * @param sourceCode 源代码文件对象
     * @return 执行结果
     */
    @Override
    public BlackBoxResult blackBoxTest(File sourceCode) {
        try {
            // 编译学生代码
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(sourceCode);
            compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            fileManager.close();

            // 加载并运行编译后的类文件
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceCode.toURI().toURL()});
            Class<?> studentClass = Class.forName("Solution", true, classLoader);

            // 调用学生代码中的 add 方法
            Method addMethod = studentClass.getMethod("resolve", int.class, int.class);
            Object result = addMethod.invoke(null, 10, 20);

            // 输出运行结果
            System.out.println("Result: " + result);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String getPath() {
        // 获取项目根目录
        File projectRoot = new File(".");

        // 获取 resources 文件夹
        File resourcesDir = new File(projectRoot, "src/main/resources");

        // 获取 resources 文件夹路径
        String resourcesPath = resourcesDir.getAbsolutePath();

        return resourcesPath + "/" + "Solution.java";

    }

    /**
     * 测试方法，用于实验观察AST
     * @param cu AST根节点
     */
    public static void printAST(CompilationUnit cu) {
        // 使用DotPrinter打印AST为DOT格式字符串
        DotPrinter dotPrinter = new DotPrinter(true);
        String dot = dotPrinter.output(cu);

        // 打印DOT格式字符串
        System.out.println(dot);
    }
}
