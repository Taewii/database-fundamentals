package app.repositories;

import app.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByBirthdayBeforeOrderBySalaryDesc(Date date);
}
