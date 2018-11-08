package app.models.dtos.view.users;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersCountAndUsersDto implements Serializable {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "user")
    private Set<UserWithProductsSoldDto> users;

    public UsersCountAndUsersDto() {
        this.users = new HashSet<>();
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<UserWithProductsSoldDto> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserWithProductsSoldDto> users) {
        this.users = users;
        this.count = users.size();
    }
}
