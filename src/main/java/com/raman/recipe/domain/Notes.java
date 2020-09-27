package com.raman.recipe.domain;

import javax.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  1. We are not specifying cascade option, because Recipe is owner, cascade is used for CRUD operations, will see in future
     *  2. For e.g. if you delete Recipe, you want notes associated with it too, so that will be done if we specify cascade
     *  3. If we delete Notes object from DB, Recipe will still exist because Recipe is owner, not Notes
     */
    @OneToOne
    private Recipe recipe;

    /**
     * Lob specifies that it can be large data, so JPA will use CLOB as it is String
     */
    @Lob
    private String recipeNotes;

    public Notes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
