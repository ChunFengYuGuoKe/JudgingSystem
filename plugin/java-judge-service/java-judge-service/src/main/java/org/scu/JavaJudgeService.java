package org.scu;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.DotPrinter;
import org.scu.black_box_result.BlackBoxResult;


import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;


public class JavaJudgeService implements JudgeService {
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

    @Override
    public boolean isSupportsLanguage(String language) {
        return language.equalsIgnoreCase("java");
    }

    public static JudgeService getJavaJudgeService() {
        return javaJudgeService;
    }

    /**
     * 黑盒测试
     * @param sourceCode 源代码文件对象
     * @return 执行结果
     */
    @Override
    public BlackBoxResult blackBoxTest(File sourceCode, Integer homeworkId) {
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

            Thread.sleep(2000);

            // 2. 加载并运行编译后的类文件
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceCode.toURI().toURL()});
            Class<?> studentClass = Class.forName("Solution", true, classLoader);

            // 4. 读取题目信息，包括语言类型、题目编号、有无返回值等
            ProblemInfo problemInfo = ProblemInfo.getProblemInfo();

            // 5. 从题目信息中获取输入参数类型字符串
            String input = problemInfo.getInputPramTypes();

            // 6. 根据字符串提取参数类型，以便调用方法
            List<Class<?>> parameterTypesList = new ArrayList<>();
            String[] typeNames = input.split(",");
            for (String typeName : typeNames) {
                try {
                    extractParameters(typeName, parameterTypesList);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Class<?>[] parameterTypes = parameterTypesList.toArray(new Class<?>[0]);

            // 7. 获取方法对象
            String methodName = "resolve";
            Method addMethod = studentClass.getMethod(methodName, parameterTypes);

            // 8. 获取项目根目录
            File projectRoot = new File(".");

            // 9. 获取输入用例
            File inputDir = new File(projectRoot, "files/homework/" + homeworkId + "/input.txt");
            String inputPath = inputDir.getAbsolutePath();
            Object[][] ins = TextFileReader.getInputGroups(parameterTypes, inputPath);

            // 10. 从题目信息中获取有无返回值
            boolean isHasReturnValue = problemInfo.isHasReturnValue();

            // 11. 获取输出用例
            File outputDir = new File(projectRoot, "files/homework/" + homeworkId + "/output.txt");
            String outputPath = outputDir.getAbsolutePath();
            Object[] outs = null;
            if (isHasReturnValue) {
                // 从题目信息中获取返回类型信息
                List<Class<?>> outputParamType = new ArrayList<>();
                extractParameters(problemInfo.getOutputParamType(), outputParamType);
                outs = TextFileReader.getOutputGroups(outputParamType.get(0), outputPath);
            } else {
                outs = TextFileReader.getOutputGroups(String.class, outputPath);
            }


            Integer score = 100;
            // 12. 定义未通过用例集合
            List<String> failedUseCases = new ArrayList<>();
            // 13. 循环测试
            for (int i = 0; i < ins.length; i++) {
                // 14. 读取测试用例
                List<Object> inputParams = new ArrayList<>();
                for (int j = 0; j < ins[0].length; j++) {
                    inputParams.add(ins[i][j]);
                }

                Object[] inputParamsArray = inputParams.toArray();

                // 16. 重定向输出流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream oldOut = System.out;
                System.setOut(ps);

                // 17. 调用学生代码中的resolve方法，动态传入参数数组
                Object result = addMethod.invoke(null, inputParamsArray);

                // 18. 输出流改回标准输出
                System.out.flush();
                System.setOut(oldOut);

                // 19. 根据有无返回值对题目分数进行判断
                // type == 1，表示有返回值
                // type == 0，表示无返回值，只有打印输出
                if (isHasReturnValue) {
                    System.out.println("result:" + result + "   返回值类型：" + result.getClass());
                    System.out.println("正确结果:" + outs[i] + "正确结果类型：" + outs[i].getClass());
                    System.out.println(!result.equals(outs[i]));
                    if (!result.equals(outs[i])) {
                        score = score - 100 / ins.length;
                        addFailedUseCase(ins, i, outs, failedUseCases, result.toString());
                        System.out.println("中间分数:" + score);
                    }
                } else {
                    // 2. 重定向 System.out
                    String printedString = baos.toString();
                    String out = outs[i] + "\r\n";
                    System.out.println(!printedString.equals(out));
                    if (!printedString.equals(out)) {
                        score = score - 100 / ins.length;
                        addFailedUseCase(ins, i, outs, failedUseCases, printedString);
                        System.out.println("中间分数:" + score);
                    }
                    System.out.println("捕获的打印输出内容：" + printedString);
                }

                System.out.println("Result: " + result);
            }
            blackBoxResult = new BlackBoxResult(score, failedUseCases.toString());
            System.out.println("score:" + score);

        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return blackBoxResult;
    }

    private static void addFailedUseCase(Object[][] ins, int i, Object[] outs, List<String> failedUseCases, String result) {
        StringBuilder useCase = new StringBuilder("输入: ");
        for (int k = 0; k < ins[i].length; k++) {
            useCase.append(ins[i][k]);
            useCase.append(" ");
        }
        useCase.append("预期输出: ");
        useCase.append(outs[i]);
        useCase.append(" 你的输出: ").append(result).append("\n");
        System.out.println(useCase);
        failedUseCases.add(useCase.toString());
    }


    private static void extractParameters(String typeName, List<Class<?>> parameterTypesList) throws ClassNotFoundException {
        if (typeName.equals("int")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("byte")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("double")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("long")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("short")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("float")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("boolean")) {
            parameterTypesList.add(int.class);
        } else if (typeName.equals("char")) {
            parameterTypesList.add(int.class);
        } else {
            Class<?> clazz = Class.forName(typeName);
            parameterTypesList.add(clazz);
        }
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
