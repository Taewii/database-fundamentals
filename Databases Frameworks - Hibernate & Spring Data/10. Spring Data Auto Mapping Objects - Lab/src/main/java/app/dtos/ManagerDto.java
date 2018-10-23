package app.dtos;

import java.util.Set;

public class ManagerDto {

    private String firstName;
    private String lastName;
    private Set<EmployeeDto> workers;

    public ManagerDto() {
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

    public Set<EmployeeDto> getWorkers() {
        return this.workers;
    }

    public void setWorkers(Set<EmployeeDto> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return String.format("%s %s | Employees: %d", this.getFirstName(), this.getLastName(), this.getWorkers().size());
    }
}
