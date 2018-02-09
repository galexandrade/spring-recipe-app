package com.recipe.app.controllers;

import com.recipe.app.domain.Recipe;
import com.recipe.app.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexCtrl).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndex() throws Exception {
        //given
        Set<Recipe> recipes = new HashSet<>();
        Recipe r1 = new Recipe();
        r1.setId(1L);
        Recipe r2 = new Recipe();
        r2.setId(2L);

        recipes.add(r1);
        recipes.add(r2);

        //when
        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class)
;
        //then
        assertEquals("index", indexCtrl.getIndex(model));
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();

        assertEquals(2, setInController.size());
    }

}