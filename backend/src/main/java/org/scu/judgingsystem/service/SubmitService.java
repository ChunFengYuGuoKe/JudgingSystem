package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Judgement;
import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import org.scu.judgingsystem.pojo.Submit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional
public interface SubmitService {
    /**
     * 新增提交记录
     * @param record 提交记录对象
     * @return 提交记录id
     */
    Long add(Record record);

    /**
     * 查询单条提交记录
     * @param id_submit 提交记录id
     * @return 提交记录
     */
    Judgement getOne(Long id_submit);

    /**
     * 查询某学生的某道题的所有提交记录
     * @param username 学生学号（用户名）
     * @param id_homework 题目id
     * @return 提交记录列表
     */
    List<Submit> getAll(String username, Long id_homework);

    /**
     * 按语言查询排行榜
     * @param id_class 班级号
     * @return 排行榜
     */
    List<Ranking> getRanking(Long id_class);

    /**
     * 保存提交的代码
     * @param file 代码文件对象
     * @param path 代码存储相对路径
     * @return 代码存储路径
     */
    String uploadFile(MultipartFile file, String path) throws IOException;
}
