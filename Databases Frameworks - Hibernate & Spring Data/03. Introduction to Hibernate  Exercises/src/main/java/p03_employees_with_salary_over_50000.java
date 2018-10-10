import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class p03_employees_with_salary_over_50000 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees =
                em.createQuery("SELECT e FROM Employee e WHERE e.salary > 50000", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        em.close();
        emf.close();
    }
}
