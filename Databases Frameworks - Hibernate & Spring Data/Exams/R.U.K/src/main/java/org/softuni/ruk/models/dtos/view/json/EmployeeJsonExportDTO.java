package org.softuni.ruk.models.dtos.view.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmployeeJsonExportDTO implements Serializable {

    @SerializedName("full_name")
    private String fullName;

    private BigDecimal salary;

    @SerializedName("started_on")
    private Date startedOn;

    private List<String> clients;

    public EmployeeJsonExportDTO() {
    }

    public EmployeeJsonExportDTO(String fullName, BigDecimal salary, Date startedOn, List<String> clients) {
        this.fullName = fullName;
        this.salary = salary;
        this.startedOn = startedOn;
        this.clients = clients;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public List<String> getClients() {
        return this.clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }
}
