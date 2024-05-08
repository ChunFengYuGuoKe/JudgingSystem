package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SubmitService {
    /**
     * 新增提交记录
     * @param id_stu 学生id
     * @param id_homework 作业id
     * @param id_lang 语言id
     * @return 提交成功/失败
     */
    boolean add(Integer id_stu, Integer id_homework, Integer id_lang);

    /**
     * 查询单条提交记录
     * @param id_submit 提交记录id
     * @return 提交记录
     */
    Record getOne(Integer id_submit);

    /**
     * 查询单个学生的所有提交记录
     * @param username 学生学号（用户名）
     * @param id_lang 语言id
     * @return 提交记录列表
     */
    List<Record> getAll(String username, Integer id_lang);

    /**
     * 按语言查询排行榜
     * @param id_lang 语言id
     * @return 排行榜
     */
    List<Ranking> getRanking(Integer id_lang);
}
