package org.scu.judgingsystem.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        record.setUsername((String) session.getAttribute("username"));

        // 提交时间
        long timeMills = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(timeMills);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        record.setTime(formatter.format(timestamp));

        // 文件操作
        // 存储目录 ./语言-班级/学号/题目/时间戳
        String uploadDir = String.format("/%s-%s/%s/%s/%d",
                record.getLanguage(),
                session.getAttribute("class"),
                session.getAttribute("username"),
                record.getHomework(),
                timeMills
        );
        String filePath = submitService.uploadFile(file, uploadDir);

        // 数据库操作/
        record.setAnswer(filePath);
        submitService.add(record, filePath);

        return Result.success(new HashMap<>(){{put("id", record.getId());}});
    }

    /**
     * 4.2 查询提交记录详情
     * @param id_submit 提交id
     */
    @GetMapping("/{id_submit}")
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
                (String) session.getAttribute("username"),
                id_homework);
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
            log.info("班级序号：" + id_class);
            List<Ranking> ranking = submitService.getRanking(id_class);
            log.info("排行信息：" + ranking);
            for (Ranking ranking1 : ranking) {
                ranking1.setClazz(id_class);
            }
            return Result.success(ranking);
        }
    }

    /**
     *
     * @param username
     * @param id_homework
     * @return
     */
    @GetMapping("/{username}/{id_homework}")
    public Result getRecord(@PathVariable String username, @PathVariable Long id_homework ){
        List<Submit> judgement = submitService.getAll(username, id_homework);
        return Result.success(judgement);
    }
}
