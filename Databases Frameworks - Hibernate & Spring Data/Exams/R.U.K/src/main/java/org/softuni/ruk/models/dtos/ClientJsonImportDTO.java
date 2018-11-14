package org.softuni.ruk.models.dtos;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClientJsonImportDTO implements Serializable {

    @NotNull
    @SerializedName("first_name")
    private String firstName;

    @NotNull
    @SerializedName("last_name")
    private String lastName;

    private Integer age;

    @SerializedName("appointed_employee")
    private String appointedEmployee;

    public ClientJsonImportDTO() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAppointedEmployee() {
        return this.appointedEmployee;
    }

    public void setAppointedEmployee(String appointedEmployee) {
        this.appointedEmployee = appointedEmployee;
    }
}
