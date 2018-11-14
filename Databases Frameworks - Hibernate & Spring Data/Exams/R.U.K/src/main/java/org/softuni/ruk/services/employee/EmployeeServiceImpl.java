package org.softuni.ruk.services.employee;

import org.softuni.ruk.Constants;
import org.softuni.ruk.models.dtos.EmployeeJsonImportDTO;
import org.softuni.ruk.models.entities.Branch;
import org.softuni.ruk.models.entities.Employee;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.EmployeeRepository;
import org.softuni.ruk.services.branch.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ValidationUtil validation;
    private final ModelParser modelParser;
    private final BranchService branchService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ValidationUtil validation,
                               ModelParser modelParser,
                               BranchService branchService) {
        this.employeeRepository = employeeRepository;
        this.validation = validation;
        this.modelParser = modelParser;
        this.branchService = branchService;
    }

    @Override
    public String create(EmployeeJsonImportDTO employeeDto) {
        if (!this.validation.isValid(employeeDto)) {
            return Constants.INVALID_DATA_ERROR;
        }

        String[] names = employeeDto.getFullName().split(" ");
        Branch branch = this.branchService.getBranchByName(employeeDto.getBranchName());

        if (branch == null || names.length != 2) {
            return Constants.INVALID_DATA_ERROR;
        }

        Employee employee = this.modelParser.convert(employeeDto, Employee.class);
        employee.setFirstName(names[0]);
        employee.setLastName(names[1]);
        employee.setBranch(branch);

        this.employeeRepository.saveAndFlush(employee);

        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Employee", employeeDto.getFullName());
    }

    @Override
    public Employee getEmployeeByNames(String firstName, String lastName) {
        return this.employeeRepository.getEmployeeByFirstNameAndLastName(firstName, lastName);
    }
}