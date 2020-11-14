package pl.sda.finalapp.app;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryDAO categoryDAO = CategoryDAO.getInstance();

    public List<CategoryDTO> findCategories(String searchText) {
        List<CategoryDTO> dtos = categoryDAO.getCategoryList().stream()
                .map(c -> c.toDTO())
                .collect(Collectors.toList());
        if (searchText == null || searchText.isBlank()) {
            return dtos;
        }
        dtos.stream()
                .filter(c -> c.getText().trim().toLowerCase().contains(searchText.trim().toLowerCase()))
                .forEach(c -> {
                    c.setSelected(true);
                    openAllParents(c, dtos);
                });

        return dtos;
    }

    private void openAllParents(CategoryDTO child, List<CategoryDTO> parents) {
        if (child.getParent().equals(CategoryDTO.NO_PARENT_VALUE)) {
            return;
        }
        Integer parentId = Integer.valueOf(child.getParent());
        parents.stream()
                .filter(p -> p.getId().equals(parentId))
                .findFirst()
                .map(p -> {
                    p.setOpened(true);
                    openAllParents(p, parents);
                    return p;
                });
    }
}

