package pl.sda.finalapp.app.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.math.BigDecimal;

@Controller
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public String products(Model model) {
        productService.allProducts();
        model.addAttribute("productTypesList", ProductType.values());
        model.addAttribute("categoriesList", categoryService.findAll());
        model.addAttribute("productsList", productService.allProducts());
        return "productsPage";

    }
    @PostMapping(value = "/products",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addProduct (@RequestParam String title,
                              @RequestParam String description,
                              @RequestParam String pictureUrl,
                              @RequestParam BigDecimal price,
                              @RequestParam ProductType productType,
                              @RequestParam Integer categoryId){
        productService.addProduct(new ProductDTO(title, description, pictureUrl, price, productType, categoryId));
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String editForm(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("productTypesList", ProductType.values());
        model.addAttribute("categoriesList", categoryService.findAll());
        return "productEditPage";
    }
    @PostMapping("/products/{id}")
    public String editProduct(@ModelAttribute ProductDTO product, @PathVariable Integer id, Model model){
        productService.editProduct(product);
        return "redirect:/products";

    }
}
