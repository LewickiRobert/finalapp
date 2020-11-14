package pl.sda.finalapp.app;

public class CategoryDTO {

    public static final String NO_PARENT_VALUE = "#";
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
            return NO_PARENT_VALUE;
        }
        return String.valueOf(parrentId);
    }

    public CategoryState getState() {
        if (state == null) {
            return new CategoryState(false, false);
        }
        return state;
    }

    public void setSelected(boolean selected) {
        if (state == null) {
            state = new CategoryState(selected, false);
        } else {
            state = new CategoryState(selected, state.isOpened());
        }
    }

    public void setOpened(boolean opened) {
        if (state == null) {
            state = new CategoryState(false, opened);
        } else {
            state = new CategoryState(state.isSelected(), opened);
        }
    }
}
