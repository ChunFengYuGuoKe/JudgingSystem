package org.scu.judgingsystem.service.imp;

import org.scu.judgingsystem.dao.UserDao;
import org.scu.judgingsystem.pojo.Student;
import org.scu.judgingsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Student> getAll(String id_class) {
        return userDao.getStudents(id_class);
    }
}
