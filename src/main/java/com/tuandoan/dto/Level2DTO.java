package com.tuandoan.dto;

public class Level2DTO {
    private Integer id;
    private String name;
    private boolean hasReferenced;

    public Level2DTO(Integer id, String name, boolean hasReferenced) {
        this.id = id;
        this.name = name;
        this.hasReferenced = hasReferenced;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasReferenced() {
        return hasReferenced;
    }
}
