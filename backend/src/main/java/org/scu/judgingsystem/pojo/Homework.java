package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_homework")
public class Homework {
    private Integer id;
    private String title;
    @TableField("id_lang")
    private String language;
    private String description;
    private String solution;

}
