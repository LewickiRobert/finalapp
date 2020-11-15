package pl.sda.finalapp.app.categories.persistence;

import pl.sda.finalapp.app.categories.api.CategoryDTO;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;

public class Category {
    private Integer id;
    private String categoryName;
    private Integer parrentId;
    private Integer depth;

    private static Integer idCounter = 0;

    public static Category applyFromText(String t) {
        Category category = new Category();
        category.categoryName = t.trim();
        category.id = ++idCounter;
        category.depth = calculateDepth(t);
        return category;
    }

    private static int calculateDepth(String t) {
        if(!t.startsWith(" ")){
            return 0;
        }
        return t.split("\\S+")[0].length();
    }

    public void setParrentId(Integer parrentId) {
        this.parrentId = parrentId;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getParrentId() {
        return parrentId;
    }

    public Integer getDepth() {
        return depth;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public CategoryTreeDTO toTreeDTO(){
        return new CategoryTreeDTO(id, parrentId, categoryName);
    }

    public CategoryDTO toDTO(){
        return new CategoryDTO(id, categoryName);
    }
}
