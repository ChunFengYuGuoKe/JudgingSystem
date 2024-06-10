package org.scu.judgingsystem.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_users")
public class User {
    private String id;
    private String username;
    private String password;
    private Integer identity;
    private String name;
    @TableField("class")
    private String clazz;
    private String email;
}
