package com.raman.recipe.service;

import com.raman.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();

}
