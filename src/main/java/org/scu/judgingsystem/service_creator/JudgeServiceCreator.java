package org.scu.judgingsystem.service_creator;

import lombok.extern.slf4j.Slf4j;
import org.scu.judgingsystem.enums.CreatorClassNameEnum;
import org.scu.judgingsystem.service.JudgeService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public abstract class JudgeServiceCreator {

    public abstract JudgeService getJudgeService();

    private static final String SINGLETON_METHOD_NAME = "getJudgeServiceCreator";


    /**
     * 获取具体creator
     * @param type creator类型值
     * @return creator
     */
    public static JudgeServiceCreator getJudgeServiceCreator(int type) {
        // 1.由枚举类获取对应的全类名
        CreatorClassNameEnum[] creatorClassNameEnums = CreatorClassNameEnum.values();
        CreatorClassNameEnum typeClassName = creatorClassNameEnums[type];

        // 2.调用加载方法，创建JudgeServiceCreator
        JudgeServiceCreator judgeServiceCreator;
        try {
            judgeServiceCreator = getCreatorObject(typeClassName.getClassName());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 3.如果初始化失败，则……还没想好
        return  judgeServiceCreator;
    }


    /**
     * 通过反射创建creator对象
     * @param className 具体Creator全类名
     * @return creator
     */
    private static JudgeServiceCreator getCreatorObject(String className) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 获取JudgeServiceCreator类加载器
        ClassLoader classLoader = JudgeServiceCreator.class.getClassLoader();

        // 前类名
        // String className = astProperties.getServiceFullName();

        // 加载类
        Class<?> clazz = classLoader.loadClass(className);

        // 获取单例方法
        Method singletonMethod = clazz.getMethod(SINGLETON_METHOD_NAME);

        // 创建类实例
        Object instance = singletonMethod.invoke(null);
        log.info("JudgeServiceCreator————{}", instance.toString());
        return (JudgeServiceCreator) instance;
    }
}
