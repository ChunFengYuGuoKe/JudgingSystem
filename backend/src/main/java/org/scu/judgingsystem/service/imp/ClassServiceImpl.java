package org.scu.judgingsystem.service.imp;

import org.scu.judgingsystem.dao.ClassDao;
import org.scu.judgingsystem.dao.UserDao;
import org.scu.judgingsystem.pojo.Clazz;
import org.scu.judgingsystem.pojo.User;
import org.scu.judgingsystem.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassDao classDao;
    @Autowired
    UserDao userDao;

    @Override
    public List<Clazz> getAll(String id_teacher) {
        return classDao.getAll(id_teacher);
    }

    @Override
    public boolean createClass(String class_id, String teacher_id) {
        Clazz clazz = new Clazz();
        clazz.setId(class_id);
        clazz.setTeacher(teacher_id);
        classDao.insert(clazz);
        return true;
    }

    @Override
    public int importStudents(List<User> studentList) {
        int count = 0;
        for (User student : studentList) {
            userDao.insert(student);
            ++count;
        }
        return count;
    }
}
