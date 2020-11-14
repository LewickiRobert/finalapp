package pl.sda.finalapp.app.categories.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories") //   /categories?searchText=ksiazka
    public String categoriesPage(@RequestParam(required = false) String searchText, Model model) {
        List<CategoryTreeDTO> categories = categoryService.findCategories(searchText);
        model.addAttribute("categoriesData", categories);
        model.addAttribute("searchText", searchText);
        return "categoriesPage";
    }

    @PostMapping("/categories")
    public String addCateory(@RequestParam String categoryName, @RequestParam Integer parentId) {
        categoryService.addCategory(categoryName, parentId);
        return "redirect:/categories?searchText="+categoryName;
    }
}
