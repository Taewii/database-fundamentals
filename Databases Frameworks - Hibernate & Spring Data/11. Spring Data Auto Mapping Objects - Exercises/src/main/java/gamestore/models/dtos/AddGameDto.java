package gamestore.models.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class AddGameDto {

    private String title;
    private BigDecimal price;
    private double size;
    private String youtubeTrailer;
    private String imageURL;
    private String description;
    private Date releaseDate;

    public AddGameDto(String title, BigDecimal price, double size,
                      String youtubeTrailer, String imageURL, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.youtubeTrailer = youtubeTrailer;
        this.imageURL = imageURL;
        this.description = description;
        this.releaseDate = releaseDate;
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

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getYoutubeTrailer() {
        return this.youtubeTrailer;
    }

    public void setYoutubeTrailer(String youtubeTrailer) {
        this.youtubeTrailer = youtubeTrailer;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
}
