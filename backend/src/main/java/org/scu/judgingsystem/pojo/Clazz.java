package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Clazz {
    @TableField("class_id")
    private String id;
    private String teacher;
    @TableField(exist = false)
    private Integer count;
}
