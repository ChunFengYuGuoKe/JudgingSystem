package org.scu.judgingsystem.service.imp;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.DotPrinter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.scu.judgingsystem.result.black_box_result.BlackBoxResult;
import org.scu.judgingsystem.service.JudgeService;
import org.springframework.stereotype.Service;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
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
        BlackBoxResult blackBoxResult = null;
        try {
            // 1. 编译学生代码
            // 获取 JavaCompiler 实例
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            // 创建自定义文件管理器，指定编译输出的目录
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                    null, // DiagnosticListener，这里为null表示不监听编译过程中的诊断信息
                    null, // Locale，这里为null表示使用默认语言环境
                    null  // charset，这里为null表示使用系统默认的字符编码
            );

            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(sourceCode);
            try {
                compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            } finally {
                try {
                    fileManager.close();
                } catch (IOException e) {
                    // 处理文件管理器关闭时可能发生的异常
                    e.printStackTrace();
                }
            }

            // 2. 加载并运行编译后的类文件
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceCode.toURI().toURL()});
            Class<?> studentClass = Class.forName("Solution", true, classLoader);

            // 3. 定义方法参数类型数组
            List<Class<?>> parameterTypesList = new ArrayList<>();

            // todo 4. 从数据库读取题目信息
            
            // todo 5. 从题目信息中获取输入参数类型字符串
            String input = "java.lang.Integer,java.lang.Integer";

            // 5. 分别读取类型，添加到类型数组中
            String[] substrings = input.split(",");
            for (String substring : substrings) {
                try {
                    log.info(substring);
                    Class<?> clazz = Class.forName(substring);
                    parameterTypesList.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Class<?>[] parameterTypes = parameterTypesList.toArray(new Class<?>[0]);

            String methodName = "resolve";
            // 6. 获取方法对象
            Method addMethod = studentClass.getMethod(methodName, parameterTypes);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream oldOut = System.out;
            System.setOut(ps);

            // 7. 调用学生代码中的 resolve 方法
            Object result = addMethod.invoke(null, 10, 40);

            System.out.flush();
            System.setOut(oldOut);

            // todo 5. 从题目信息中获取有无返回值
            int type = 0;
            
            // 8. 生成输出运行结果
            if (1 == type) {
                blackBoxResult = new BlackBoxResult(result);
            } else if (0 == type) {
                // 2. 重定向 System.out
                String printedString = baos.toString();
                System.out.println("捕获的打印输出内容：" + printedString);
                blackBoxResult = new BlackBoxResult(printedString);
            }

            System.out.println("Result: " + result);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return blackBoxResult;
    }

    private static BlackBoxResult getBlackBoxPrintResult() {
        BlackBoxResult blackBoxResult;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        // 4. 恢复 System.out


        // 5. 获取捕获的打印输出内容
        String printedString = baos.toString();
        System.out.println("捕获的打印输出内容：" + printedString);
        blackBoxResult = new BlackBoxResult(printedString);
        return blackBoxResult;
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
