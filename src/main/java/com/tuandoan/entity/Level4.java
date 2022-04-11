package com.tuandoan.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "level_4")
public class Level4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "level_3_id")
    private Level3 level3;

    @OneToMany(mappedBy = "level4")
    private List<Level5> level5s;

    public Level4() {
    }

    public Level4(String name) {
        this.name = name;
    }

    public Level4(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level4(String name, Level3 level3) {
        this.name = name;
        this.level3 = level3;
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

    public Level3 getLevel3() {
        return level3;
    }

    public void setLevel3(Level3 level3) {
        this.level3 = level3;
    }

    public List<Level5> getLevel5s() {
        return level5s;
    }

    public void setLevel5s(List<Level5> level5s) {
        this.level5s = level5s;
    }
}
