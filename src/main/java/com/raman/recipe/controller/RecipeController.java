package com.raman.recipe.controller;

import com.raman.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public String getAllRecipes(Model model) {
        log.info("Fetching all recipes...");
        model.addAttribute("recipeList", recipeService.findAll());
        log.info("Returning all recipes...");
        return "recipes/index";
    }
}
