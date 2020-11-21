package pl.sda.finalapp.app.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalapp.app.categories.api.CategoryDTO;
import pl.sda.finalapp.app.categories.domain.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDTO productDTO) {
        productRepository.save(Product.fromDTO(productDTO));
    }

    public List<ProductListDTO> allProducts() {
        return productRepository.findAll().stream()
                .map(p -> createProductListDTO(p))
                .collect(Collectors.toList());
    }

    private ProductListDTO createProductListDTO(Product p) {
        String catNameWIthId = categoryService.findCategoryNameById(p.getCategoryId())
                .map(cn -> p.getCategoryId() + " " + cn).orElse("Błąd! Kategoria produktyo ID= "
                        + p.getCategoryId() + "nie istnieje.");
        return p.toListDTO(catNameWIthId);
    }

    public ProductDTO findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(p -> p.toDTO())
                .orElseThrow(() -> new ProductNotFoundExeption(productId));

    }

    public void editProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new ProductNotFoundExeption(productDTO.getId()));
        product.apply(productDTO);
        productRepository.save(product);
    }

    public List<ProductListDTO> allProducts(String searchText,
                                        ProductType productType,
                                        Integer categoryId) {
        List<Product> products = null;
        if (searchText != null && !searchText.isBlank() && productType != null && categoryId != null) {
            products = productRepository.findProducts(searchText, productType, categoryId);
        }else {
            products = productRepository.findAll();
        }
        return products.stream()
                .map(p -> createProductListDTO(p))
                .collect(Collectors.toList());
    }
}
