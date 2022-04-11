package com.tuandoan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "level_2")
public class Level2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_1_id")
    private Level1 level1;

    @OneToMany(mappedBy = "level2")
    private List<Level3> level3s;

    public Level2() {
    }

    public Level2(String name) {
        this.name = name;
    }

    public Level2(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level2(String name, Level1 level1) {
        this.name = name;
        this.level1 = level1;
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

    public Level1 getLevel1() {
        return level1;
    }

    public void setLevel1(Level1 level1) {
        this.level1 = level1;
    }

    public List<Level3> getLevel3s() {
        return level3s;
    }

    public void setLevel3s(List<Level3> level3s) {
        this.level3s = level3s;
    }
}
