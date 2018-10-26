package gamestore.models.dtos.view;

public class OwnedGameDto {

    private String title;

    public OwnedGameDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s%n", this.getTitle());
    }
}
