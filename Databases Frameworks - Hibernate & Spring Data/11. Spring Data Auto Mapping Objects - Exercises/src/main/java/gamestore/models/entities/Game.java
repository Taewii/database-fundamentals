package gamestore.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Z].{2,}", message = "Invalid title")
    private String title;

    @Size(min = 11, max = 11, message = "Invalid title")
    private String youtubeTrailer;

    @Pattern(regexp = "(https://|http://).*", message = "Invalid Image URL")
    private String imageURL;

    @Min(value = 0, message = "Size must be positive")
    @Digits(integer = 10, fraction = 1)
    private double size;

    @Min(value = 0, message = "Price must be positive")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Size(min = 20, message = "Size must be at least 20 symbols")
    private String description;

    private Date releaseDate;

    public Game() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
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
}
