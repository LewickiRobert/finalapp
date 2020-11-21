package pl.sda.finalapp.app.categories.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.finalapp.app.categories.api.CategoryDTO;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;
import pl.sda.finalapp.app.categories.persistence.CategoryFromFileDTO;
import pl.sda.finalapp.app.categories.persistence.CategoryDAO;
import pl.sda.finalapp.app.categories.persistence.CategoryRepository;
import pl.sda.finalapp.app.products.ProductType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryTreeDTO> findCategories(String searchText) {
        List<CategoryTreeDTO> dtos = categoryRepository.findAll().stream()
                .map(c -> c.toTreeDTO())
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

    public void addCategory(String categoryName, Integer parentId) {
        categoryRepository.save(new Category(categoryName, parentId));
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(c -> c.toDTO())
                .collect(Collectors.toList());
    }

    public Optional<String> findCategoryNameById(Integer id) {
        return categoryRepository.findCategoryNameById(id);
    }

    private void openAllParents(CategoryTreeDTO child, List<CategoryTreeDTO> parents) {
        if (child.getParent().equals(CategoryTreeDTO.NO_PARENT_VALUE)) {
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

    @PostConstruct
    void initializeCatrgories() {
        if (categoryRepository.count() != 0) {
            return;
        }
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        Map<Integer, Integer> oldChildAndOldParentsIdsMap = new HashMap<>();
        Map<Integer, Integer> newIdToOldIdMap = new HashMap<>();
        List<CategoryFromFileDTO> categoryDTOList = categoryDAO.getCategoryList();
        saveCategoryAndPopulateMaps(oldChildAndOldParentsIdsMap, newIdToOldIdMap, categoryDTOList);
        populateParentsIds(oldChildAndOldParentsIdsMap, newIdToOldIdMap);

    }

    private void populateParentsIds(Map<Integer, Integer> oldChildAndOldParentsIdsMap, Map<Integer, Integer> newIdToOldIdMap) {
        for (Integer newId : newIdToOldIdMap.keySet()) {
            Category category = categoryRepository.findById(newId).orElseThrow(() -> new RuntimeException("błąd"));
            Integer oldId = newIdToOldIdMap.get(newId);
            Integer oldParentId = oldChildAndOldParentsIdsMap.get(oldId);
            Integer newParentId = newIdToOldIdMap.entrySet().stream()
                    .filter(e -> e.getValue().equals(oldParentId))
                    .map(e -> e.getKey())
                    .findFirst()
                    .orElse(null);
            if (newParentId == null) {
                continue;
            }
            category.applyParentId(newParentId);
            categoryRepository.save(category);
        }
    }

    private void saveCategoryAndPopulateMaps(Map<Integer, Integer> oldChildAndOldParentsIdsMap, Map<Integer, Integer> newIdToOldIdMap, List<CategoryFromFileDTO> categoryDTOList) {
        for (CategoryFromFileDTO dto : categoryDTOList) {
            oldChildAndOldParentsIdsMap.put(dto.getId(), dto.getParrentId());
            Category category = new Category(dto.getCategoryName());
            Category savedCategory = categoryRepository.save(category);
            newIdToOldIdMap.put(savedCategory.getId(), dto.getId());
        }
    }

    public void moveCategory(Integer newParentId, Integer movedId) {
        final Category category = categoryRepository.findById(movedId)
                .orElseThrow(() -> new RuntimeException("Kategoria o id:" + movedId + " nie została odnaleziona"))
                .applyParentId(newParentId);
        categoryRepository.save(category);
    }

}

