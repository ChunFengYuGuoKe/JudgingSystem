package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.Homework;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    /**
     * 1.1 题目列表查询
     * @param id_lang 语言id
     */
    @GetMapping("/{id_lang}")
    public Result getAll(@PathVariable Integer id_lang) {
        List<Homework> homeworkList = homeworkService.getAll(id_lang);
        return Result.success(homeworkList);
    }

    /**
     * 1.2 题目查询
     * @param id_lang 语言id（用于区别1.1，不作实际用途）
     * @param id_homework 题目id
     */
    @GetMapping("/{id_lang}/{id_homework}")
    public Result get(@PathVariable Integer id_lang, @PathVariable Long id_homework) {
        Homework homework = homeworkService.getById(id_homework);
        return Result.success(homework);
    }

    /**
     * 1.3 新增题目
     * @param homework pojo
     * @param sf 题解代码文件
     * @param uf 测试用例压缩文件
     */
    @PostMapping("")
    public Result add(Homework homework,
                      @RequestPart("solutionFile") MultipartFile sf,
                      @RequestPart("usecaseFile") MultipartFile uf
    ) throws IOException {
        String uploadDir = String.format("/%d/%s", homework.getLanguage(), homework.getTitle());

        // 题解
        String solutionPath = homeworkService.uploadFile(sf, uploadDir);
        homework.setSolution(solutionPath);
        // 测试用例
        String usecasePath = homeworkService.uploadFile(uf, uploadDir);
        homework.setUsecases(usecasePath);

        homeworkService.add(homework);

        return Result.success();
    }

    /**
     * 1.4 删除题目
     * @param id_homework 待删除题目id
     */
    @DeleteMapping("/{id_homework}")
    public Result delete(@PathVariable Long id_homework) throws IOException {
        homeworkService.delete(id_homework);
        return Result.success();
    }
}
