package com.raman.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/** Lombok annotation @Data for getter, setter, toString, equals, hashcode, constructor */
@Data
@EqualsAndHashCode(exclude = "recipe") // if you don't specify exclude, it will become circular dependency and StackOverFlow error
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

}
