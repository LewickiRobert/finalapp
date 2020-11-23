package pl.sda.finalapp.app;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.finalapp.app.categories.domain.CategoryService;
import pl.sda.finalapp.app.products.Product;
import pl.sda.finalapp.app.products.ProductDTO;
import pl.sda.finalapp.app.products.ProductRepository;
import pl.sda.finalapp.app.products.ProductType;
import pl.sda.finalapp.app.users.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataSeed implements InitializingBean {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(Role.ADMIN));
            roleRepository.save(new Role(Role.USER));
        }

        addUsers();
        createProducts();
    }

    private void createProducts() {
        if (productRepository.count() != 0) {
            return;
        }
        List<Integer> categoryId = categoryService.findAll().stream().map(c -> c.getId()).collect(Collectors.toList());
        for (int i = 0; i < 33; i++) {
            final ProductType productType = ProductType.values()[i % 3];
            final ProductDTO productDTO = new ProductDTO("product" + i, "desc" + i, "picture" + i, BigDecimal.valueOf(i), productType, categoryId.get(i));
            productRepository.save(Product.fromDTO(productDTO));
        }
    }

    private void addUsers() {
        if (userRepository.count() != 0) {
            return;
        }
        final Role admin = roleRepository.findByRoleName(Role.ADMIN);
        final Role user = roleRepository.findByRoleName(Role.USER);
        User admin1 = new User("Admin",
                "Admin",
                "admin@admin.pl",
                passwordEncoder.encode("admin123"),
                "Lublin",
                "pl",
                "20-123",
                "street",
                "1-1-1",
                "1",
                "1",
                true);
        admin1.addRole(admin);
        userRepository.save(admin1);
        User user1 = new User("User",
                "User",
                "user@user.pl",
                passwordEncoder.encode("user123"),
                "Lublin",
                "pl",
                "20-123",
                "street",
                "1-1-1",
                "1",
                "1",
                true);
        user1.addRole(user);
        userRepository.save(user1);
    }
}
