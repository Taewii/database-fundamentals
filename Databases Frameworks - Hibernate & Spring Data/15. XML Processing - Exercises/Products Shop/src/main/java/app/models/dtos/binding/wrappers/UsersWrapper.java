package app.models.dtos.binding.wrappers;

import app.models.dtos.binding.UserDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersWrapper {

    @XmlElement(name = "user")
    private UserDto[] users;

    public UsersWrapper() {
    }

    public UserDto[] getUsers() {
        return this.users;
    }
}
