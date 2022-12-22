package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe r=new Recipe();
        HashSet receipeData=new HashSet();
        receipeData.add(r);
        when(recipeRepository.findAll()).thenReturn(receipeData);
        Set<Recipe> recipes=recipeService.getRecipes();
        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(3)).findAll();
    }
}