package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
    category=new Category();
    }
    @Test
    public void getId() {
        Long l= 4L;
        category.setId(l);
        assertEquals((Long) 4L, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}