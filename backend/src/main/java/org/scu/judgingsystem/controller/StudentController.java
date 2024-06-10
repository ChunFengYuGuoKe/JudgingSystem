package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Student;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 2.1 根据班级号查询学生列表
     * @param id_class 班级号
     */
    @GetMapping("/{id_class}")
    public Result getAll(@PathVariable String id_class) {
        List<Student> students = studentService.getAll(id_class);
        return Result.success(students);
    }
}
