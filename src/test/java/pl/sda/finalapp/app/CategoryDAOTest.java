package pl.sda.finalapp.app;

import org.junit.jupiter.api.Test;
import pl.sda.finalapp.app.categories.persistence.Category;
import pl.sda.finalapp.app.categories.persistence.CategoryDAO;

import java.util.List;

class CategoryDAOTest {
    @Test
    void shouldPopulateCategories(){
        CategoryDAO instance = CategoryDAO.getInstance();
        List<Category> categoryList = instance.getCategoryList();
        System.out.println();
    }

}