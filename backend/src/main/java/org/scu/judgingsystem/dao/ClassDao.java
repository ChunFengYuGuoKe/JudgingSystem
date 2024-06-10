package org.scu.judgingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.scu.judgingsystem.pojo.Clazz;

import java.util.List;

@Mapper
public interface ClassDao extends BaseMapper<Clazz> {
    @Select("SELECT class_id as id, COUNT(*) as count " +
            "FROM tbl_users JOIN tbl_class " +
            "ON tbl_users.class=tbl_class.class_id " +
            "where teacher=#{id_teacher} GROUP BY class_id")
    List<Clazz> getAll(String id_teacher);
}
