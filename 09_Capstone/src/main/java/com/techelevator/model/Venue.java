package com.techelevator.model;

public class Venue {

    private Long id;
    private String name;
    private Long city_id;
    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCity_id() {
        return city_id;
    }
    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return this.name;
    }


}
