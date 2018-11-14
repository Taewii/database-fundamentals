package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.EmployeeJsonImportDTO;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

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
        try {
            EmployeeJsonImportDTO[] employees = this.jsonParser.read(EmployeeJsonImportDTO[].class, content);

            StringBuilder sb = new StringBuilder();
            for (EmployeeJsonImportDTO employee : employees) {
                sb.append(this.employeeService.create(employee)).append(System.lineSeparator());
            }

            return sb.toString().trim();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
