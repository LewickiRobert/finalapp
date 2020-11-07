package pl.sda.finalapp.app;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOTest {
    @Test
    void shouldPopulateCategories(){
        CategoryDAO instance = CategoryDAO.getInstance();
        List<Category> categoryList = instance.getCategoryList();
        System.out.println();
    }

}