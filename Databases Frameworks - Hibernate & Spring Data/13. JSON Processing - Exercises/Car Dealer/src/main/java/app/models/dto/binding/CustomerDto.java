package app.models.dto.binding;

import java.io.Serializable;
import java.util.Date;

public class CustomerDto implements Serializable {

    private String name;
    private Date birthDate;
    private boolean isYoungDriver;

    public CustomerDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
