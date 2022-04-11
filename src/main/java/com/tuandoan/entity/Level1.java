package com.tuandoan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "level_1")
public class Level1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "level1")
    private List<Level2> level2s;

    public Level1() {
    }

    public Level1(String name) {
        this.name = name;
    }

    public Level1(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Level2> getLevel2s() {
        return level2s;
    }

    public void setLevel2s(List<Level2> level2s) {
        this.level2s = level2s;
    }
}
