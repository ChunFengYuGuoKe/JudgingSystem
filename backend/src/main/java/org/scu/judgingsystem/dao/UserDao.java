package org.scu.judgingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.scu.judgingsystem.pojo.Student;
import org.scu.judgingsystem.pojo.User;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("SELECT tbl_users.username AS id, SUM(score) as score " +
            "FROM `tbl_users` JOIN `tbl_records` " +
            "ON tbl_users.username=tbl_records.username " +
            "WHERE class=#{id_class} GROUP BY id")
    List<Student> getStudents(String id_class);
}
