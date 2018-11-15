package org.softuni.ruk.services.employee;

import org.softuni.ruk.models.dtos.binding.json.EmployeeJsonImportDTO;
import org.softuni.ruk.models.dtos.view.json.EmployeeJsonExportDTO;
import org.softuni.ruk.models.entities.Employee;

import java.util.List;

public interface EmployeeService {

    String create(EmployeeJsonImportDTO employeeDto);

    Employee getEmployeeByNames(String firstName, String lastName);

    List<EmployeeJsonExportDTO> getTopEmployees();
}