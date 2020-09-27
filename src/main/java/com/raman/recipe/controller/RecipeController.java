package com.raman.recipe.controller;

import com.raman.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public String getAllRecipes(Model model) {
        model.addAttribute("recipeList", recipeService.findAll());
        return "recipes/index";
    }
}
