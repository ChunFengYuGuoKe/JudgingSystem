package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Homework;
import org.scu.judgingsystem.reulst.Code;
import org.scu.judgingsystem.reulst.Result;
import org.scu.judgingsystem.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    public boolean add() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    @GetMapping("/{id_lang}/{id_homework}")
    public Result get(@PathVariable Integer id_lang, @PathVariable Integer id_homework) {
        Homework homework = homeworkService.getById(id_homework);
        return Result.success(homework);
    }

    @GetMapping("/{id_lang}")
    public Result getAll(@PathVariable Integer id_lang) {
        List<Homework> homeworkList = homeworkService.getAll(id_lang);
        return Result.success(homeworkList);
    }
}
