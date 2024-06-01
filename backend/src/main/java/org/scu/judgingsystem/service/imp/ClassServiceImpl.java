package org.scu.judgingsystem.service.imp;

import org.scu.judgingsystem.dao.ClassDao;
import org.scu.judgingsystem.dao.UserDao;
import org.scu.judgingsystem.pojo.Clazz;
import org.scu.judgingsystem.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    public ClassDao classDao;

    @Override
    public List<Clazz> getAll(String id_teacher) {
        return classDao.getAll(id_teacher);
    }
}
