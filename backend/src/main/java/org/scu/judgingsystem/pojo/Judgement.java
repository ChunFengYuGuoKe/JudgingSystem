package org.scu.judgingsystem.pojo;

import lombok.Data;

@Data
public class Judgement {
    private Integer id;
    private String title;
    private String black;
    private String white;
    private Integer score;
}
