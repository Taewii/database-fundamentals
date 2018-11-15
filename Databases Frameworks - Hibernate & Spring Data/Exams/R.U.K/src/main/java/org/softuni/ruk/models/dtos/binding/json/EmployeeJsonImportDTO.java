package org.softuni.ruk.models.dtos.binding.json;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeJsonImportDTO implements Serializable {

    @NotNull
    @SerializedName("full_name")
    private String fullName;

    private BigDecimal salary;

    @SerializedName("started_on")
    private Date startedOn;

    @NotNull
    @SerializedName("branch_name")
    private String branchName;

    public EmployeeJsonImportDTO() {
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

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
