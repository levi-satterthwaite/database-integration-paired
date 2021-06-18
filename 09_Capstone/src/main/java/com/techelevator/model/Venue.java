package com.techelevator.model;

public class Venue {

    private Long id;
    private String name;
    private String city_name;
    private String description;
    private String state_abbreviation;
    private String categories;


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

    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getState_abbreviation() {
        return state_abbreviation;
    }

    public void setState_abbreviation(String state_abbreviation) {
        this.state_abbreviation = state_abbreviation;
    }

    public String getCategories(){
        return categories;
    }

    public void setCategories(String categories){
        this.categories = categories;
    }

    public String toString() {
        return this.name;
    }


}
