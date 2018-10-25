package gamestore.models.entities;

import gamestore.models.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    @Pattern(regexp = "^.*@.*\\..*$", message = "Incorrect email.")
    private String email;

    @Size(min = 5, message = "Password too short")
    @Pattern(regexp = ".*[A-Z].*[0-1]+.*", message = "Incorrect username/password.")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_games",
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> games;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_shopping_cart_games",
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> shoppingCart;

    public User() {
        this.games = new HashSet<>();
        this.shoppingCart = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Game> getGames() {
        return this.games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public Set<Game> getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(Set<Game> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
