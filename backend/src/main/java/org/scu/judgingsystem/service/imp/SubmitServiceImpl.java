package org.scu.judgingsystem.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.scu.judgingsystem.dao.RecordDao;
import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import org.scu.judgingsystem.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitServiceImpl implements SubmitService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public boolean add(Integer id_stu, Integer id_homework, Integer id_lang) {
        return false;
    }

    @Override
    public Record getOne(Integer id_submit) {
        return recordDao.selectById(id_submit);
    }

    @Override
    public List<Record> getAll(String username, Integer id_lang) {
        QueryWrapper<Record> qw = new QueryWrapper<>();
        qw.eq("username", username)
                .eq("id_lang", id_lang);
        return recordDao.selectList(qw);
    }

    @Override
    public List<Ranking> getRanking(Integer id_lang) {
        return recordDao.getRanking(id_lang);
    }
}
