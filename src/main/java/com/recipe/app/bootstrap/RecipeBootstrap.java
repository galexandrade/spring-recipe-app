package com.recipe.app.bootstrap;

import com.recipe.app.domain.*;
import com.recipe.app.repositories.CategoryRepository;
import com.recipe.app.repositories.RecipeRepository;
import com.recipe.app.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by alex.andrade on 05/02/2018.
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(this.getRecipes());
        log.debug("Recipes created");
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found!");
        }

        //get UOMs
        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found!");
        }

        //get UOMs
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found!");
        }

        //get UOMs
        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found!");
        }

        //get UOMs
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found!");
        }

        //get UOMs
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found!");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        //Get categories
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found!");
        }

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found!");
        }

        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("dsa dsa dsa sda dsa sda sa dsad sad sa sad sa sad sa dsa \n sda sad sa dsa \n sdas\n dsad sa dsa sa");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("alaslldasldlsal dsaldsladlsalda");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(".5"), tableSpoonUom));

        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.getCategories().add(americanCategory);

        recipes.add(guacRecipe);

        return recipes;
    }
}
