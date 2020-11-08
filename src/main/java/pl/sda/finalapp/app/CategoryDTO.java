package pl.sda.finalapp.app;

public class CategoryDTO {

    private Integer id;
    private String categoryName;
    private Integer parrentId;

    private CategoryState state;

    public CategoryDTO(Integer id, Integer parrentId, String categoryName) {
        this.id = id;
        this.parrentId = parrentId;
        this.categoryName = categoryName;
    }

    public String getText() {
        return categoryName;
    }

    public Integer getId() {
        return id;
    }

    public String getParent() {
        if (parrentId == null) {
            return "#";
        }
        return String.valueOf(parrentId);
    }

    public CategoryState getState() {
        if(state == null){
            return new CategoryState(false,false);
        }
        return state;
    }
}
