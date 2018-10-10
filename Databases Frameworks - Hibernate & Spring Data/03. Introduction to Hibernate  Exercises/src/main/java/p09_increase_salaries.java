import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.*;

public class p09_increase_salaries {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e " +
                    "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')" +
                    "ORDER BY e.id", Employee.class)
                    .getResultList();

            for (Employee employee : employees) {
                    BigDecimal salary = employee.getSalary();
                    BigDecimal afterRaise = salary.multiply(new BigDecimal(1.12));
                    employee.setSalary(afterRaise);

                System.out.printf("%s %s ($%.2f)%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary());
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
