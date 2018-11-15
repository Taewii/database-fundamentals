package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.binding.json.EmployeeJsonImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Parser jsonParser;

    @Autowired
    public EmployeeController(EmployeeService employeeService, Parser jsonParser) {
        this.employeeService = employeeService;
        this.jsonParser = jsonParser;
    }

    public String importEmployeesFromJson(String content) {
        EmployeeJsonImportDTO[] employees = this.jsonParser.read(EmployeeJsonImportDTO[].class, content);

        StringBuilder sb = new StringBuilder();
        for (EmployeeJsonImportDTO employee : employees) {
            sb.append(this.employeeService.create(employee)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportTopEmployees() {
        return this.jsonParser.write(this.employeeService.getTopEmployees());
    }
}
