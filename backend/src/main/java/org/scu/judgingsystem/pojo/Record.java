package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_records")
public class Record {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("id_homework")
    private Integer homework;
    private String language;
    private String username;
    private String answer;
    private Integer score;
    private String black;
    private String white;
    private String time;
}
