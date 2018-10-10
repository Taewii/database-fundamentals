import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.in;

public class p02_contains_employee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String[] names = reader.readLine().split(" ");

        List<Employee> employees =
                em.createQuery("SELECT e FROM Employee e WHERE e.firstName =:first " +
                        "AND e.lastName =:second", Employee.class)
                        .setParameter("first", names[0])
                        .setParameter("second", names[1])
                        .getResultList();

        System.out.println(employees.size() == 0 ? "No" : "Yes");

        em.close();
        emf.close();
    }
}
