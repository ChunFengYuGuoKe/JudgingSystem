package org.scu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileReader {
//    public static void main(String[] args) {
//        Object[][] resultArray = getInputGroups();
//
//        // 输出结果进行验证
//        for (Object[] array : resultArray) {
//            for (Object num : array) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
//    }

    public static Object[][] getInputGroups(Class<?>[] parameterTypes, String path) {
        // 存储结果的ArrayList
        ArrayList<Object[]> resultList = new ArrayList<>();

        // 获取 resources 文件夹
        File ioDir = new File(path);

        // 获取 resources 文件夹路径
        String ioPath = ioDir.getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(ioPath))) {
            String line;
            // 逐行读取文件
            while ((line = br.readLine()) != null) {
                // 去掉末尾的分号并按分号分割行
                String[] groups = line.split(";");
                for (String group : groups) {
                    if (!group.trim().isEmpty()) {
                        // 按逗号分割每组元素
                        String[] elements = group.split(",");
                        Object[] ios = new Object[elements.length];
                        for (int i = 0; i < elements.length; i++) {
                            ios[i] = convertToType(elements[i].trim(), parameterTypes[i]);
                        }

                        // 添加到结果列表
                        resultList.add(ios);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将ArrayList转换为数组
        Object[][] resultArray = new Object[resultList.size()][];
        resultList.toArray(resultArray);
        return resultArray;
    }

    public static Object[] getOutputGroups(Class<?> parameterType, String path) {
        // 存储结果的ArrayList
        ArrayList<Object> resultList = new ArrayList<>();

        // 获取 resources 文件夹
        File ioDir = new File(path);

        // 获取 resources 文件夹路径
        String ioPath = ioDir.getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(ioPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 去除分号和空白字符
                line = line.trim().replace(";", "");
                if (!line.isEmpty()) {
                    // 解析为整数并添加到列表中
                    resultList.add(convertToType(line, parameterType));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将列表转换为整数数组
        return resultList.toArray();

//        try (BufferedReader br = new BufferedReader(new FileReader(ioPath))) {
//            String line;
//            // 逐行读取文件
//            while ((line = br.readLine()) != null) {
//                // 去掉末尾的分号并按分号分割行
//                String[] groups = line.split(";");
//                for (String group : groups) {
//                    if (!group.trim().isEmpty()) {
//                        // 按逗号分割每组元素
//                        // String[] elements = group.split(",");
////                        Object[] ios = new Object[elements.length];
////                        for (int i = 0; i < elements.length; i++) {
////                            ios[i] = convertToType(elements[i].trim(), parameterType);
////                        }
//                        Object ios;
//                        ios = convertToType(group.trim(), parameterType);
//
//                        // 添加到结果列表
//                        resultList.add(ios);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 将ArrayList转换为数组
//        Object[] resultArray = new Object[resultList.size()][];
//        resultList.toArray(resultArray);
//        return resultArray;
    }

    private static Object convertToType(String value, Class<?> targetType) {
        if (targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (targetType == String.class) {
            return value;
        } else if (targetType == int.class) {
            return Integer.parseInt(value);
        } else if (targetType == byte.class) {
            return Byte.parseByte(value);
        } else if (targetType == double.class) {
            return Double.parseDouble(value);
        } else if (targetType == long.class) {
            return Long.parseLong(value);
        } else if (targetType == short.class) {
            return Short.parseShort(value);
        } else if (targetType == float.class) {
            return Float.parseFloat(value);
        } else if (targetType == boolean.class) {
            return Float.parseFloat(value);
        } else if (targetType == char.class) {
            return value;
        }

//        if (typeName.equals("int")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("byte")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("double")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("long")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("short")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("float")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("boolean")) {
//            parameterTypesList.add(int.class);
//        } else if (typeName.equals("char")) {
//            parameterTypesList.add(int.class);
//        } else {
//            Class<?> clazz = Class.forName(typeName);
//            parameterTypesList.add(clazz);
//        }

        // Add more types as needed
        throw new IllegalArgumentException("Unsupported type: " + targetType);
    }
}
