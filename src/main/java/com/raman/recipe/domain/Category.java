package com.raman.recipe.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    /**
     * 1. If you don't specify 'mappedBy' attribute, hibernate doesn't know that join table for this relation is created or not, so it will again create a new join table again
     * 2. Specify 'mappedBy = categories' where 'categories' is the field name in Recipe entity, tells hibernate that this relationship is managed by 'categories' field, so you don't need to touch it
     */
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

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

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
