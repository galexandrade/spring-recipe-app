package com.recipe.app.services;

import com.recipe.app.commands.IngredientCommand;

/**
 * Created by alex.andrade on 16/02/2018.
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long id);
}
