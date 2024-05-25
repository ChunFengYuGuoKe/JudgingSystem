package org.scu.judgingsystem.controller;

import org.scu.judgingsystem.pojo.*;
import org.scu.judgingsystem.pojo.Record;
import org.scu.judgingsystem.result.Result;
import org.scu.judgingsystem.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/submits")
public class SubmitController {
    @Autowired
    private SubmitService submitService;

    /**
     * 4.1 提交作业
     * @param record 提交信息
     * @param file 代码文件
     */
    @PostMapping("")
    public Result submit(Record record,
                         @RequestPart("code") MultipartFile file,
                         HttpSession session) throws Exception {
//        record.setUsername(session.getAttribute("username").toString());

        // 提交时间
        long timeMills = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(timeMills);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setTime(formatter.format(timestamp));

        // 文件操作
        String uploadDir = String.format("/%s/%s/%s/%s/%d",
                session.getAttribute("class"),
                session.getAttribute("username"),
                record.getHomework(),
                timeMills
        );
        String filePath = submitService.uploadFile(file, uploadDir);

        // 数据库操作/
        record.setAnswer(filePath);
        submitService.add(record);

        return Result.success(new HashMap<>(){{put("id", record.getId());}});
    }

    /**
     * 4.2 查询提交记录详情
     * @param id_submit 提交id
     */
    @GetMapping("/details/{id_submit}")
    public Result getDetails(@PathVariable Long id_submit){
        Judgement judgement = submitService.getOne(id_submit);
        return Result.success(judgement);
    }

    /**
     * 4.3 查询某题目提交记录
     * @param id_homework 题目id
     */
    @GetMapping("/records/{id_homework}")
    public Result getRecords(@PathVariable Long id_homework, HttpSession session){
        List<Submit> submits = submitService.getAll(
                session.getAttribute("username").toString(), id_homework);
        return Result.success(submits);
    }

    /**
     * 4.4 查询某语言学生积分排行榜
     * @param id_class 班级号
     */
    @GetMapping("/rank/{id_class}")
    public Result getRanking(@PathVariable Long id_class, HttpSession session){
        if (id_class == 0L) {
            List<Ranking> ranking = submitService.getRanking((Long) session.getAttribute("class"));
            return Result.success(ranking);
        } else {
            List<Ranking> ranking = submitService.getRanking(id_class);
            return Result.success(ranking);
        }
    }
}
