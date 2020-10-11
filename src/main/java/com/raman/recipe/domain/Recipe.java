package com.raman.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @Lob
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
    private Set<Ingredient> ingredients = new HashSet<>();

    /**
     * 1. @Enumerated annotation is used when you try to save some enum value in DB
     * 2. You can specify EnumType.STRING or EnumType.ORDINAL, default one is ORDINAL
     * 3. STRING will save string content of enum, while ORDINAL will save 0,1,2.. etc
     * 4. It is best you use STRING, because if you use ORDINAL and in future you change order or introduce new values in between
     *    your saved data will be inconsistent
     */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    /**
     * 1. Initially what we did, we specified @ManyToMany annotation in both Recipe and Categories POJOs
     * 2. Result of it was hibernate created 2 tables for mapping, one with recipe_categories name and other with category_recipes name but columns were same
     * 3. So we need to specify @JoinTable (you specify table name too) to tell hibernate that create join table for it, where we specify joinColumns and inverseJoinColumns
     * 4. The joinColumns attribute is responsible for the columns mapping of the owning side.
     * 5. The inverseJoinColumns attribute is responsible for the columns mapping of the inverse side.
     */
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        /**
         * This is a 2 way relationship, so chances are you might forget to add recipe in notes object, so it is wise to do it here
         */
        notes.setRecipe(this);
    }

    /**
     * Recipe-Ingredient  is a 2 way relationship, so chances are you might forget to add recipe in ingredient object,
     * so it is wise to do it here
     */
    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        ingredients.add(ingredient);
        return this;
    }
}
