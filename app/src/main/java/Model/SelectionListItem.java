package Model;

public class SelectionListItem {

    private String name;
    private String imageSelection;

    public SelectionListItem() {
    }

    public SelectionListItem(String name, String imageSelection) {
        this.name = name;
        this.imageSelection = imageSelection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSelection() {
        return imageSelection;
    }

    public void setImageSelection(String imageSelection) {
        this.imageSelection = imageSelection;
    }
}
