package com.raman.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "recipes") // if you don't specify exclude, it will become circular dependency and StackOverFlow error
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
    private Set<Recipe> recipes = new HashSet<>();

    public Category() {
    }

}
