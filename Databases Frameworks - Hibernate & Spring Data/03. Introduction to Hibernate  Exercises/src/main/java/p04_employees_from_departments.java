import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class p04_employees_from_departments {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees =
                em.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name = 'Research and Development'", Employee.class)
                .getResultList();

        employees.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getId))
                .forEach(e -> {
                    System.out.printf("%s %s from %s - $%.2f%n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getDepartment().getName(),
                            e.getSalary());
                });

        em.close();
        emf.close();
    }
}
