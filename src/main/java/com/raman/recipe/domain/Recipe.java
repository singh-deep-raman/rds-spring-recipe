package com.raman.recipe.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo learn different GenerationType
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    /**
     * It will be created as BLOB in JPA because it is of type Byte
     */
    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // it makes Recipe owner of Notes
    private Notes notes;

    /**
     * 1. One recipe can have multiple ingredients so Set<Ingredients> and @OneToMany relationship
     * 2. In this case, Recipe is owner of ingredient, so cascade is specified
     * 3. mappedBy specifies column of Child class with which it will be mapped, basically FK
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    /**
     * 1. @Enumerated annotation is used when you try to save some enum value in DB
     * 2. You can specify EnumType.STRING or EnumType.ORDINAL, default one is ORDINAL
     * 3. STRING will save string content of enum, while ORDINAL will save 0,1,2.. etc
     * 4. It is best you use STRING, because if you use ORDINAL and in future you change order or introduce new values in between
     *    your saved data will be inconsistent
     */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
