package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Clazz;
import org.scu.judgingsystem.pojo.User;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.ClassService;
import org.scu.judgingsystem.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 3.1 根据教师id查询该教师管理的班级
     * @param id_teacher 教师id
     */
    @GetMapping("/{id_teacher}")
    public Result getAll(@PathVariable String id_teacher) {

        List<Clazz> classList = classService.getAll(id_teacher);

        return Result.success(classList);
    }

    /**
     * 3.2 创建新班级
     * @param clazz 班级号
     * @param session Http会话
     */
    @PostMapping("/new")
    public Result newClass(String clazz, HttpSession session) {
        classService.createClass(clazz, (String) session.getAttribute("username"));
        return Result.success();
    }

    /**
     * 3.3导入学生信息
     * @param clazz 班级号
     * @param file 学生信息电子表格
     */
    @PostMapping("/import")
    public Result importClass(String clazz,
                              @RequestPart("file") MultipartFile file) throws IOException {
        List<User> studentList = FileUtils.readStudentExcel(file);
        Integer number = classService.importStudents(studentList);
        return Result.success(number);
    }
}
