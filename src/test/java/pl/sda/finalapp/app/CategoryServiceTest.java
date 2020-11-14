package pl.sda.finalapp.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.util.List;

class CategoryServiceTest {


    @Test
    public void shouldMarkSelectedAndOpened() {
        String searchText = "lek";
        CategoryService categoryService = new CategoryService();

        List<CategoryTreeDTO> categories = categoryService.findCategories(searchText);
        CategoryTreeDTO category1 = getCategory(categories,1);
        CategoryTreeDTO category4 = getCategory(categories,4);

        Assertions.assertEquals(true, category1.getState().isOpened());
        Assertions.assertEquals(true, category4.getState().isSelected());
        Assertions.assertEquals(false, category1.getState().isSelected());
    }

    private CategoryTreeDTO getCategory(List<CategoryTreeDTO> categories, Integer catId) {
        return categories.stream()
                .filter(c -> c.getId().equals(catId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleźion kategorii o ID = 1"));
    }

    ;

}