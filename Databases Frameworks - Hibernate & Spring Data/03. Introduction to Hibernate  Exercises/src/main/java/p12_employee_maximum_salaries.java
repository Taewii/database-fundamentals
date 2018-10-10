import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class p12_employee_maximum_salaries {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees = em.createQuery("SELECT e FROM Employee e " +
                "WHERE e.salary NOT BETWEEN 30000 AND 70000" +
                "GROUP BY e.department.id ORDER BY e.salary DESC", Employee.class)
                .getResultList();

        employees.stream().sorted(Comparator.comparing(e -> e.getDepartment().getId()))
                .forEach(employee -> System.out.printf("%s - %.2f%n",
                        employee.getDepartment().getName(), employee.getSalary()));

        em.close();
        emf.close();
    }
}
