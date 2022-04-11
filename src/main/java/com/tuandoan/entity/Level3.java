package com.tuandoan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "level_3")
public class Level3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_2_id")
    private Level2 level2;

    @OneToMany(mappedBy = "level3")
    private List<Level4> level4s;

    public Level3() {
    }

    public Level3(String name) {
        this.name = name;
    }

    public Level3(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level3(String name, Level2 level2) {
        this.name = name;
        this.level2 = level2;
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

    public Level2 getLevel2() {
        return level2;
    }

    public void setLevel2(Level2 level2) {
        this.level2 = level2;
    }

    public List<Level4> getLevel4s() {
        return level4s;
    }

    public void setLevel4s(List<Level4> level4s) {
        this.level4s = level4s;
    }
}
