package pl.sda.finalapp.app.categories.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.util.List;

@Controller // klasyczny kontorler zwracajacy widok
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories") //   /categories?searchText=ksiazka
    public String categoriesPage(@RequestParam(required = false) String searchText, Model model) {
        List<CategoryTreeDTO> categories = categoryService.findCategories(searchText);
        List<CategoryDTO> parentsList = categoryService.findAll();
        model.addAttribute("categoriesData", categories);
        model.addAttribute("searchText", searchText);
        model.addAttribute("parentsList",parentsList);
        return "categoriesPage";
    }

    @PostMapping("/categories")
    public String addCateory(@RequestParam String categoryName, @RequestParam Integer parentId) {
        categoryService.addCategory(categoryName, parentId);
        return "redirect:/categories?searchText="+categoryName;
    }
    @PostMapping("/moveCat") // to jest ajax request
    @ResponseBody // to spowoduje zwrócenie Jsona
    public ResponseEntity<String> moveCategory(@RequestParam Integer newParentId, @RequestParam Integer movedId){
        categoryService.moveCategory(newParentId, movedId);
        return ResponseEntity.ok("udało się");
    }
}
