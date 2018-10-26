package gamestore.models.dtos.view;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameDetailViewDto {

    private String title;
    private BigDecimal price;
    private String description;
    private Date releaseDate;

    public GameDetailViewDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(getReleaseDate());

        sb.append("Title: ").append(this.getTitle()).append(System.lineSeparator());
        sb.append("Price: ").append(this.getPrice()).append(System.lineSeparator());
        sb.append("Description: ").append(this.getDescription()).append(System.lineSeparator());
        sb.append("Release date: ").append(date);

        return sb.toString();
    }
}
