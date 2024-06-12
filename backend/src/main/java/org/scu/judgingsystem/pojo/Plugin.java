package org.scu.judgingsystem.pojo;

import lombok.Data;

@Data
public class Plugin {
    private Long id;
    private String name;

    public Plugin(String name, Long id) {
        this.id = id;
        this.name = name;
    }
}
