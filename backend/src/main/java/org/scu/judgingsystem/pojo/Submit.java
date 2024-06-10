package org.scu.judgingsystem.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Submit {
    private Integer id;
    private String title;
    private Integer score;
    private String time;
}
