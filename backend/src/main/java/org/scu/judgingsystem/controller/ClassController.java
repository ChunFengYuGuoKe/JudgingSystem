package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Clazz;
import org.scu.judgingsystem.reulst.Result;
import org.scu.judgingsystem.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/{id_teacher}")
    public Result getAll(@PathVariable String id_teacher) {

        List<Clazz> classList = classService.getAll(id_teacher);

        return Result.success(classList);
    }
}
