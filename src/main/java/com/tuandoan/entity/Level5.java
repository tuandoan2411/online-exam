package com.tuandoan.entity;

import javax.persistence.*;

@Entity
@Table(name = "level_5")
public class Level5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_4_id")
    private Level4 level4;

    public Level5() {
    }

    public Level5(String name) {
        this.name = name;
    }

    public Level5(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level5(String name, Level4 level4) {
        this.name = name;
        this.level4 = level4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level4 getLevel4() {
        return level4;
    }

    public void setLevel4(Level4 level4) {
        this.level4 = level4;
    }
}
