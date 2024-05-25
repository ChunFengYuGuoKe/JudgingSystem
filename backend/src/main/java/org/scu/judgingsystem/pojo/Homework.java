package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_homework")
public class Homework {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    @TableField("id_lang")
    private Long language;
    private String description;
    private String solution;
    private String usecases;
}
