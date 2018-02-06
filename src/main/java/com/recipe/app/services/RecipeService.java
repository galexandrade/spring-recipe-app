package com.recipe.app.services;

import com.recipe.app.domain.Recipe;

import java.util.Set;

/**
 * Created by alex.andrade on 06/02/2018.
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
