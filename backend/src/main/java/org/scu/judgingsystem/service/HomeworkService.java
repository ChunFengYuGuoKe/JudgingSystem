package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Homework;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HomeworkService {

    /**
     * 新增题目
     * @param homework
     * @return
     */
    public boolean add(Homework homework);

    /**
     * 删除题目
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据某语言某查询题目
     * @param id_homework
     * @return
     */
    public Homework getById(Integer id_homework);

    /**
     * 查询某语言全部题目
     * @return
     */
    public List<Homework> getAll(Integer id_lang);
}
