package com.raman.recipe.repositories;

import com.raman.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    /**
     * 1. Spring 4 don't uses Optional, but Spring 5 encourages use of Optional
     * 2. Spring JPA will implement this method at runtime, and will allow us to get Category by description
     * 3. IntelliJ Ultimate shows you various options when you type 'findByDesc....' but community edition doesn't support that
     */
    Optional<Category> findByDescription(String description);
}
