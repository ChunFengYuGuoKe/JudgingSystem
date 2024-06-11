package org.scu.judgingsystem.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.scu.judgingsystem.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {

    /**
     * 解压缩文件到指定路径
     * @param zipFile 解压缩文件对象
     * @param path 目标路径
     */
    public static void unzip(File zipFile, String path) throws IOException {
        // 创建解压缩输入流
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            // 循环遍历压缩包中的文件
            while ((entry = zipIn.getNextEntry()) != null) {
                String fileName = entry.getName();
                // 创建解压缩后的文件
                File unzippedFile = new File(path + fileName);
                // 创建解压缩后文件的父目录
                // unzippedFile.getParentFile().mkdirs();
                // 写入文件内容
                try (FileOutputStream fout = new FileOutputStream(unzippedFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipIn.read(buffer)) > 0) {
                        fout.write(buffer, 0, length);
                    }
                }
                zipIn.closeEntry();
            }
        }
    }

    /**
     * 删除文件
     * @param file 待删除文件对象
     * @return 删除成功/失败
     */
    public static boolean deleteFile(File file) throws IOException {
//        Path targetPath = Paths.get(path);
//
//        if (Files.notExists(targetPath)) return true;
//
//        if (Files.isDirectory(targetPath)) {
//            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(targetPath)) {
//                for (Path filePath : directoryStream) {
//                    Files.delete(filePath);
//                }
//                return true;
//            } catch (IOException e) {
//                throw new IOException("Failed to delete files in directory: " + path, e);
//            }
//        } else {
//            try {
//                Files.delete(targetPath);
//                return true;
//            } catch (IOException e) {
//                throw new IOException("Failed to delete file: " + path, e);
//            }
//        }
        // 不存在的路径
        if (!file.exists()) {
            return true;
        }
        // 删除目录
        if (file.isDirectory()) {
            // 如果是目录，则递归删除目录下的内容
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    deleteFile(f);
                }
            }
        }
        // 删除文件
        return file.delete();
    }

    /**
     * 读取学生电子表格
     * @param excel 表格文件
     */
    public static List<User> readStudentExcel(MultipartFile excel) throws IOException {
        List<User> studentList = new ArrayList<>();

        try (InputStream fis = excel.getInputStream();
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                User student = new User();
                student.setName(row.getCell(0).getStringCellValue());
                student.setPassword(row.getCell(0).getStringCellValue().substring(7, 12));
                student.setIdentity(1);
                student.setName(row.getCell(1).getStringCellValue());
                student.setClazz(row.getCell(2).getStringCellValue());
                student.setEmail(row.getCell(3).getStringCellValue());
                studentList.add(student);
            }
        }
        return studentList;
    }
}
