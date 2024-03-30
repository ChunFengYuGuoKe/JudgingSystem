package org.scu.judgingsystem.controller.student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.scu.judgingsystem.enums.CreatorClassNameEnum;
import org.scu.judgingsystem.pojo.SubmitRequest;
import org.scu.judgingsystem.properties.ASTProperties;
import org.scu.judgingsystem.service.JudgeService;
import org.scu.judgingsystem.service_accessor.JudgeServiceCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 作为测率模式中的环境类
@RestController
@RequestMapping("/student/judge")
@Api(tags = "判题相关接口")
@Slf4j
public class JudgeController {
    // 抽象策略类
    private JudgeService judgeService;

    private ASTProperties astProperties;


    @PostMapping
    @ApiOperation("提交解答")
    public void judge(@RequestBody SubmitRequest submitRequest) {
        // astService = astProperties.getASTFullName();
        getJudgeService(submitRequest);
    }

    /**
     * 用工厂创建JudgeService
     * @param submitRequest 用户提交的请求
     */
    private static void getJudgeService(SubmitRequest submitRequest) {
        // 1.首先获取参数里的类型值
        int typeValue = submitRequest.getType();

        // 2.通过JudgeServiceCreator创建creator对象
        JudgeServiceCreator judgeServiceCreator = JudgeServiceCreator.getJudgeServiceCreator(typeValue);

        // 3.用creator对象创建JudgeService对象
        JudgeService judgeService = judgeServiceCreator.getJudgeService();
    }


    /*@Autowired
    private void setAstProperties(ASTProperties astProperties) {
        this.astProperties = astProperties;
    }

    public void loadASTService(String className) {
        try {
            judgeService = getObject(className);
            // 打印类信息
            System.out.println("Dynamic class created: " + judgeService.getClass().getName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private JudgeService getObject(String className) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 获取类加载器
        ClassLoader classLoader = JudgeService.class.getClassLoader();

        // 前类名
        // String className = astProperties.getServiceFullName();

        // 加载类
        Class<?> clazz = classLoader.loadClass(className);

        // 获取默认构造函数
        Constructor<?> constructor = clazz.getDeclaredConstructor();

        // 设置访问权限（如果构造函数是私有的）
        constructor.setAccessible(true);

        // 创建类实例
        Object instance = constructor.newInstance();
        return (JudgeService) instance;
    }*/
}
