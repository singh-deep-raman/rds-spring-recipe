package com.raman.recipe.domain;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
        this.recipe = recipe;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
