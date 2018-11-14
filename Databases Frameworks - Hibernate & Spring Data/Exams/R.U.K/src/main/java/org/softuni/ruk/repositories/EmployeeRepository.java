package org.softuni.ruk.repositories;

import org.softuni.ruk.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName);
}