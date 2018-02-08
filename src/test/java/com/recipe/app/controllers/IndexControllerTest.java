package com.recipe.app.controllers;

import com.recipe.app.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by alex.andrade on 08/02/2018.
 */
public class IndexControllerTest {

    IndexController indexCtrl;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        indexCtrl = new IndexController(recipeService);
    }

    @Test
    public void getIndex() throws Exception {
        assertEquals("index", indexCtrl.getIndex(model));
        verify(recipeService, times(1)).getRecipes();
    }

}