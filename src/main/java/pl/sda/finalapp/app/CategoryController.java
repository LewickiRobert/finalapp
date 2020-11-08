package pl.sda.finalapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping ("/categories")
    public String categoriesPage (Model model){
        List<CategoryDTO> categories = categoryService.findCategories();
        model.addAttribute("categoriesData",categories);
        return "categoriesPage";
    }
}
