package pl.sda.finalapp.app.categories.domain;

import pl.sda.finalapp.app.categories.api.CategoryDTO;
import pl.sda.finalapp.app.categories.api.CategoryTreeDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String categoryName;
    private Integer parentId;

    public Category(String categoryName) {

        this.categoryName = categoryName;
    }

    public Category() {
    }

    public Category(String categoryName, Integer parentId) {

        this.categoryName = categoryName;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getParrentId() {
        return parentId;
    }

    public Category applyParentId(Integer newParentId) {
        this.parentId = newParentId;
        return this;
    }

    public CategoryTreeDTO toTreeDTO() {
        return new CategoryTreeDTO(id, parentId, categoryName);
    }

    public CategoryDTO toDTO() {
        return new CategoryDTO(id, categoryName);
    }
}
