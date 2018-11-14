package org.softuni.ruk.services.client;

import org.softuni.ruk.Constants;
import org.softuni.ruk.models.dtos.ClientJsonImportDTO;
import org.softuni.ruk.models.entities.Client;
import org.softuni.ruk.models.entities.Employee;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.ClientRepository;
import org.softuni.ruk.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final EmployeeService employeeService;
    private final ModelParser modelParser;
    private final ValidationUtil validation;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             EmployeeService employeeService,
                             ModelParser modelParser,
                             ValidationUtil validation) {
        this.clientRepository = clientRepository;
        this.employeeService = employeeService;
        this.modelParser = modelParser;
        this.validation = validation;
    }

    @Override
    public String create(ClientJsonImportDTO clientDto) {
        if (!this.validation.isValid(clientDto)) {
            return Constants.INVALID_DATA_ERROR;
        }

        String[] employeeNames = clientDto.getAppointedEmployee().split(" ");
        Employee employee = this.employeeService.getEmployeeByNames(employeeNames[0], employeeNames[1]);

        if (employee == null) {
            return Constants.INVALID_DATA_ERROR;
        }

        Client client = this.modelParser.convert(clientDto, Client.class);
        client.setFullName(clientDto.getFirstName() + " " + clientDto.getLastName());
        this.clientRepository.saveAndFlush(client);

        employee.getClients().add(client);

        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Client", client.getFullName());
    }
}