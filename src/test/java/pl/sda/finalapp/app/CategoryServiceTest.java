package pl.sda.finalapp.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {


    @Test
    public void shouldMarkSelectedAndOpened() {
        String searchText = "lek";
        CategoryService categoryService = new CategoryService();

        List<CategoryDTO> categories = categoryService.findCategories(searchText);
        CategoryDTO category1 = getCategory(categories,1);
        CategoryDTO category4 = getCategory(categories,4);

        Assertions.assertEquals(true, category1.getState().isOpened());
        Assertions.assertEquals(true, category4.getState().isSelected());
        Assertions.assertEquals(false, category1.getState().isSelected());
    }

    private CategoryDTO getCategory(List<CategoryDTO> categories, Integer catId) {
        return categories.stream()
                .filter(c -> c.getId().equals(catId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleźion kategorii o ID = 1"));
    }

    ;

}