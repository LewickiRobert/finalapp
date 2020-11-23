package pl.sda.finalapp.app.categories.persistence;

import pl.sda.finalapp.app.categories.api.CategoryDTO;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;

public class CategoryFromFileDTO {
    private Integer id;
    private String categoryName;
    private Integer parrentId;
    private Integer depth;

    private static Integer idCounter = 0;

    public static CategoryFromFileDTO applyFromText(String t) {
        CategoryFromFileDTO categoryFromFileDTO = new CategoryFromFileDTO();
        categoryFromFileDTO.categoryName = t.trim();
        categoryFromFileDTO.id = ++idCounter;
        categoryFromFileDTO.depth = calculateDepth(t);
        return categoryFromFileDTO;
    }

    private static int calculateDepth(String t) {
        if(!t.startsWith(" ") && !t.startsWith("\t") ){
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
