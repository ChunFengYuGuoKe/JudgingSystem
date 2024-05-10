package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/submits")
public class SubmitController {

    @Autowired
    private SubmitService submitService;

    public boolean submit(){
        return false;
    }

    @GetMapping("/{id_submit}")
    public Result querySingle(@PathVariable Integer id_submit){
        Record record = submitService.getOne(id_submit);
        return Result.success(record);
    }

    @GetMapping("/{id_stu}/{id_lang}")
    public Result queryAll(@PathVariable String id_stu, @PathVariable Integer id_lang){
        List<Record> records = submitService.getAll(id_stu, id_lang);
        return Result.success(records);
    }

    @GetMapping("/rank/{id_lang}")
    public Result getRanking(@PathVariable Integer id_lang){
        List<Ranking> ranking = submitService.getRanking(id_lang);
        return Result.success(ranking);
    }
}
