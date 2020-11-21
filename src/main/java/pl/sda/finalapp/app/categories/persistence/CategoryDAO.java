package pl.sda.finalapp.app.categories.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.finalapp.app.categories.domain.Category;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryDAO {
    private static CategoryDAO INSTANCE;

    private List<CategoryFromFileDTO> categoryFromFileDTOList = populateCategories();

    private CategoryDAO() {
    }

    public List<CategoryFromFileDTO> getCategoryList() {
        return categoryFromFileDTOList;
    }

    public static CategoryDAO getInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryDAO();
                }
            }
        }
        return INSTANCE;
    }

    private List<CategoryFromFileDTO> populateCategories() {
        List<String> categoriesText;
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resourceURL = classLoader.getResource("categories.txt");
        try {
            categoriesText = Files.readAllLines(Paths.get(resourceURL.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            categoriesText = Collections.EMPTY_LIST;
        }
        Integer id = 1;
        List<CategoryFromFileDTO> categories = categoriesText.stream()
                .map(t -> CategoryFromFileDTO.applyFromText(t))
                .collect(Collectors.toList());

        Map<Integer, List<CategoryFromFileDTO>> categoriesMap = categories.stream()
                .collect(Collectors.groupingBy(e -> e.getDepth()));

//        for (Category lc : categories) {
//            if (categoriesMap.containsKey(lc.getDepth())) {
//                List<Category> innerList = categoriesMap.get(lc.getDepth());
//                innerList.add(lc);
//            } else {
//                List<Category> innerList = new ArrayList<>();
//                innerList.add(lc);
//                categoriesMap.put(lc.getDepth(), innerList);
//            }
//        }

        populateParentId(0, categoriesMap);

        return categories;
    }

    private void populateParentId(int depth, Map<Integer, List<CategoryFromFileDTO>> categoriesMap) {
        List<CategoryFromFileDTO> children = categoriesMap.get(depth);
        List<CategoryFromFileDTO> parents = categoriesMap.get(depth - 1);
        if (children == null) {
            return;
        }
        if (depth > 0) {
            for (CategoryFromFileDTO child : children) {
                chooseParent(parents, child);
            }
        }
        populateParentId(depth + 1, categoriesMap);
    }

    private void chooseParent(List<CategoryFromFileDTO> parents, CategoryFromFileDTO child) {
        Integer childId = child.getId();
        Integer parentId = parents.stream()
                .map(e -> e.getId())
                .filter(id -> id < childId)
                .sorted((a, b) -> b - a)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rodzica"));
        child.setParrentId(parentId);
    }


}


