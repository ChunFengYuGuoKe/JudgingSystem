package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StudentService {

    /**
     * 查询某个班级的所有学生
     * @param id_class 班级id
     * @return 学生列表
     */
    public List<Student> getAll(String id_class);
}
