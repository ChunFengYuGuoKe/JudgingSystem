package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Clazz {
    @TableField("class_id")
    private String classId;
    @TableField("teacher")
    private String teacherId;
    @TableField(exist = false)
    private Integer count;  // 学生人数
}
