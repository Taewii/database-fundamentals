package app;

import app.dtos.EmployeeDto;
import app.dtos.EmployeeWithManagerDto;
import app.dtos.ManagerDto;
import app.models.Address;
import app.models.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ConsoleRunner(final EmployeeRepository employeeRepository,
                         final AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
        ModelMapper modelMapper = new ModelMapper();

        //1. Simple Mapping
        Employee employee = this.employeeRepository.findById(1L).orElse(null);
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        System.out.println(employeeDto);

        //2. Advanced Mapping - the same as 1. cus the mapper is smart enough to map the employees to emloyeesDto
        ManagerDto managerDto = modelMapper.map(employee, ManagerDto.class);
        System.out.println(managerDto);
        managerDto.getWorkers().forEach(System.out::println);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");

        //3. Projection version 1
        modelMapper.addMappings(new PropertyMap<Employee, EmployeeWithManagerDto>() {
            @Override
            protected void configure() {
                map().setManagerLastName(source.getManager().getLastName());
            }
        });
        this.employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(date)
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeWithManagerDto.class))
                .forEach(System.out::println);

        //version 2
        TypeMap<Employee, EmployeeWithManagerDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeWithManagerDto.class);
        typeMap.addMappings(m -> m.map(src -> src.getManager().getLastName(), EmployeeWithManagerDto::setManagerLastName));
        this.employeeRepository
                .findAllByBirthdayBeforeOrderBySalaryDesc(date)
                .stream()
                .map(typeMap::map)
                .forEach(System.out::println);

    }

    private void seedDatabase() throws ParseException {
        Address address = new Address();
        address.setCity("Cherven traktor");
        address.setCountry("Binkos");

        Employee employee1 = new Employee();
        employee1.setFirstName("Speedyboi");
        employee1.setLastName("Gonzales");
        employee1.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1914-01-01"));
        employee1.setSalary(new BigDecimal(12413.213));
        employee1.setOnHoliday(true);
        employee1.setAddress(address);

        Employee employee2 = new Employee();
        employee2.setFirstName("Six");
        employee2.setLastName("Nine");
        employee2.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1969-06-09"));
        employee2.setSalary(new BigDecimal(696969.69));
        employee2.setOnHoliday(true);
        employee2.setAddress(address);

        Employee employee3 = new Employee();
        employee3.setFirstName("Kiro");
        employee3.setLastName("Skalichkata");
        employee3.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
        employee3.setSalary(new BigDecimal(3215.12));
        employee3.setOnHoliday(false);
        employee3.setAddress(address);

        Employee employee4 = new Employee();
        employee4.setFirstName("Mitio");
        employee4.setLastName("Pishtovcheto");
        employee4.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1985-01-01"));
        employee4.setSalary(new BigDecimal(2215.12));
        employee4.setOnHoliday(false);
        employee4.setAddress(address);

        employee4.getWorkers().add(employee1);
        employee4.getWorkers().add(employee2);
        employee4.getWorkers().add(employee3);
        employee1.setManager(employee4);
        employee2.setManager(employee4);
        employee3.setManager(employee4);

        this.employeeRepository.save(employee4);
    }
}
