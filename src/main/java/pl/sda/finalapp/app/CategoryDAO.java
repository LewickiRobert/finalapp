package pl.sda.finalapp.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryDAO {
    private static CategoryDAO INSTANCE;
    private List<Category> categoryList = populateCategories();

    private CategoryDAO() {
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

    private List<Category> populateCategories() {
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
        List<Category> categories = categoriesText.stream()
                .map(t -> Category.applyFromText(t))
                .collect(Collectors.toList());

        Map<Integer, List<Category>> categoriesMap = categories.stream()
                .collect(Collectors.groupingBy(e ->e.getDepth()));

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

        return null;
    }

}
