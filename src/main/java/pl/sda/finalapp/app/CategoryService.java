package pl.sda.finalapp.app;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryDAO categoryDAO = CategoryDAO.getInstance();

    public List<CategoryDTO> findCategories() {
        return categoryDAO.getCategoryList().stream()
                .map(c -> c.toDTO())
                .collect(Collectors.toList());
    }
}

