package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_records")
public class Record {
    private Integer id;
    @TableField("id_homework")
    private Integer homework;
    @TableField("id_lang")
    private Integer language;
    private String username;
    private String answer;
    private Integer score;
}
