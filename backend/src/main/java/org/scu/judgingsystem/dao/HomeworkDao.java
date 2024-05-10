package org.scu.judgingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.scu.judgingsystem.pojo.Homework;

@Mapper
public interface HomeworkDao extends BaseMapper<Homework> {
}
