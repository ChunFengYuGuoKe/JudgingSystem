package org.scu.judgingsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.scu.judgingsystem.pojo.Judgement;
import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.scu.judgingsystem.pojo.Submit;

import java.util.List;

@Mapper
public interface RecordDao extends BaseMapper<Record> {

    @Select("SELECT tbl_records.id, tbl_homework.title, tbl_records.black, tbl_records.white, tbl_records.score " +
            "FROM `tbl_records` JOIN `tbl_homework` ON tbl_records.id_homework=tbl_homework.id " +
            "WHERE tbl_records.id=#{id_submit}")
    Judgement getOneById(Long id_submit);

    @Select("SELECT tbl_records.id, tbl_homework.title, tbl_records.score, tbl_records.time " +
            "FROM `tbl_records` JOIN `tbl_homework` ON tbl_records.id_homework=tbl_homework.id " +
            "WHERE tbl_records.username=#{id_stu} AND tbl_records.id_homework=#{id_homework}")
    List<Submit> getAll(String id_stu, Long id_homework);

    @Select("WITH max_scores AS (" +
            "    SELECT tbl_users.username, id_homework, MAX(score) AS max_score " +
            "    FROM tbl_users JOIN tbl_records ON tbl_users.username=tbl_records.username JOIN tbl_class ON tbl_users.class = tbl_class.class_id " +
            "    WHERE tbl_class.id = ${id_class} " +
            "    GROUP BY username, id_homework " +
            ")," +
            "user_total_max_scores AS (" +
            "    SELECT username, SUM(max_score) AS total_score " +
            "    FROM max_scores " +
            "    GROUP BY username " +
            ")" +
            "SELECT username AS id, total_score AS score " +
            "FROM user_total_max_scores " +
            "ORDER BY score DESC")
    List<Ranking> getRanking(Long id_class);
}
