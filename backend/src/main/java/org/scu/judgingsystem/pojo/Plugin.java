package org.scu.judgingsystem.pojo;

import lombok.Data;

@Data
public class Plugin {
    private String name;
    private Integer id;

    public Plugin(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
