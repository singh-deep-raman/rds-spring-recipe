package com.raman.recipe.service.impl;

import com.raman.recipe.domain.Recipe;
import com.raman.recipe.repositories.RecipeRepository;
import com.raman.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * 1. Repository can return Optional or Iterator, but your service class must return useful Collection objects (Set, List etc)
     */
    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
