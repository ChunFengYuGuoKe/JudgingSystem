package org.scu.judgingsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface RecordDao extends BaseMapper<Record> {

    @Select("SELECT tbl_users.username AS id, " +
            "tbl_users.class as clazz, score " +
            "FROM `tbl_users` JOIN `tbl_records` " +
            "ON tbl_users.username=tbl_records.username " +
            "WHERE id_lang=#{id_lang} "+
            "ORDER BY score DESC")
    List<Ranking> getRanking(Integer id_lang);

}
