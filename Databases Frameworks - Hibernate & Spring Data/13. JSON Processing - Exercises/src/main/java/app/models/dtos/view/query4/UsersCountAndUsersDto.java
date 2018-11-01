package app.models.dtos.view.query4;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsersCountAndUsersDto implements Serializable {

    private int count;
    private Set<UserWithProductsSold> users;

    public UsersCountAndUsersDto() {
        this.users = new HashSet<>();
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<UserWithProductsSold> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserWithProductsSold> users) {
        this.users = users;
    }
}
