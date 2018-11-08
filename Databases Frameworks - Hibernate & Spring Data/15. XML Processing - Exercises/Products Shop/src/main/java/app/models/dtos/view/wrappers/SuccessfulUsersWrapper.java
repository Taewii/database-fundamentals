package app.models.dtos.view.wrappers;

import app.models.dtos.view.users.SuccessfulUserDto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SuccessfulUsersWrapper {

    @XmlElement(name = "user")
    private List<SuccessfulUserDto> users;

    public SuccessfulUsersWrapper() {
    }

    public SuccessfulUsersWrapper(List<SuccessfulUserDto> users) {
        this.users = users;
    }
}
