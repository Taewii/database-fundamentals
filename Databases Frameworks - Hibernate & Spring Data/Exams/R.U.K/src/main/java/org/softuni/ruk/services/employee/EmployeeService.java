package org.softuni.ruk.services.employee;

import org.softuni.ruk.models.dtos.EmployeeJsonImportDTO;
import org.softuni.ruk.models.entities.Employee;

public interface EmployeeService {

    String create(EmployeeJsonImportDTO employeeDto);

    Employee getEmployeeByNames(String firstName, String lastName);
}