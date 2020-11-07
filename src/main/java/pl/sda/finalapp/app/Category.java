package pl.sda.finalapp.app;

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
        category.depth = t.split("\\S+")[0].length();
        return category;
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
}
