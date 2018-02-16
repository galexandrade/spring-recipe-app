package com.recipe.app.services;

import com.recipe.app.commands.RecipeCommand;
import com.recipe.app.domain.Recipe;

import java.util.Set;

/**
 * Created by alex.andrade on 06/02/2018.
 */
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
