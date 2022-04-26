package com.tuandoan.dto;

public class Level4DTO {
    private Integer id;
    private String name;
    private boolean hasReferenced;

    public Level4DTO(Integer id, String name, boolean hasReferenced) {
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
