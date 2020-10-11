package com.raman.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = "recipe") // if you don't specify exclude, it will become circular dependency and StackOverFlow error
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    /**
     * 1. This is the inverse relationship of Ingredient with Recipe so @ManyToOne
     * 2. We are not specifying 'cascade' property here, because we don't want Recipe to be deleted if Ingredient is deleted
     * 3. We are specifying @ManyToOne annotation here because we are implementing Bidirectional relation advised by JPA
      */
    @ManyToOne
    private Recipe recipe;

    /**
     * 1. One ingredient can have one type of unit measurement so @OneToOne
     * 2. cascade is not specified because Ingredient entity is not owner, it is just referring to units (cm,tbsp,etc) created
     * 3. We don't want to delete unitOfMeasure when we delete any ingredient, so no cascade
     * 4. fetch type is EAGER default for @OneToOne, but we specified it just to show how to set it
     */
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient() {
    }

    /**
     * code refactor to use a constructor without ingredient property, addIngredient() method will be used to maintain 2 way relationships
     */
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
        this.recipe = recipe;
    }

}
