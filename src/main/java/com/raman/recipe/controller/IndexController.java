package com.raman.recipe.controller;

import com.raman.recipe.domain.Category;
import com.raman.recipe.domain.UnitOfMeasure;
import com.raman.recipe.repositories.CategoryRepository;
import com.raman.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    /** we are showing this for demo purpose, in general we don't inject repository in Controller, we have a service layer for that */
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String indexPage() {
        log.info("Fetching Index page...");
        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> pinch = unitOfMeasureRepository.findByDescription("Pinch");
        /** check on console */
        System.out.println("Category id: "+mexican.get().getId());
        System.out.println("UOM id: "+pinch.get().getId());
        log.info("Index page returning...");
        return "index";
    }
}
