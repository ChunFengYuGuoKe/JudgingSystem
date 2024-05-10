package org.scu.judgingsystem.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.scu.judgingsystem.dao.HomeworkDao;
import org.scu.judgingsystem.pojo.Homework;
import org.scu.judgingsystem.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkDao homeworkDao;

    @Override
    public boolean add(Homework homework) {
        homeworkDao.insert(homework);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        return true;
    }

    @Override
    public Homework getById(Integer id_homework) {
        return homeworkDao.selectById(id_homework);
    }

    @Override
    public List<Homework> getAll(Integer id_lang) {
        QueryWrapper<Homework> qw = new QueryWrapper<>();
        qw.eq("id_lang", id_lang);
        return homeworkDao.selectList(qw);
    }
}
