package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Clazz;
import org.scu.judgingsystem.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ClassService {
    /**
     * 查询某教师管理的所有班级
     * @param id_teacher 教师id
     * @return Clazz列表
     */
    List<Clazz> getAll(String id_teacher);


    boolean createClass(String class_id, String teacher_id);

    /**
     * 导入班级学生数据
     * @param studentList 学生数据列表
     * @return 成功导入的学生数量
     */
    int importStudents(List<User> studentList);
}
