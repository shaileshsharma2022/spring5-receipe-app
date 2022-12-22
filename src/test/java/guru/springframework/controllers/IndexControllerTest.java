package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
           indexController=new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws  Exception{
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void getIndexPage() throws Exception{
        Set<Recipe> recipeSet=new HashSet<>();
        recipeSet.add(new Recipe());
        //recipeSet.add(new Recipe());
        Recipe r=new Recipe();
        r.setId(3L);
        recipeSet.add(r);

        ArgumentCaptor<Set<Recipe>> argumentCaptor =ArgumentCaptor.forClass(Set.class);

        when(recipeService.getRecipes()).thenReturn(recipeSet);

String viewName=indexController.getIndexPage(model);
assertEquals("index",viewName);
verify(recipeService,times(1)).getRecipes();
verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
Set<Recipe> setIntController=argumentCaptor.getValue();
assertEquals(2,setIntController.size());


    }
}