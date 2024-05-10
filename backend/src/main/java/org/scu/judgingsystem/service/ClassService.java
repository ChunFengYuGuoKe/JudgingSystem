package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Clazz;
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
}
