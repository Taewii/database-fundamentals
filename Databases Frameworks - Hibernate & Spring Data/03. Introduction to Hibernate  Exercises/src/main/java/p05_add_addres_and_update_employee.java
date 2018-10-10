import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class p05_add_addres_and_update_employee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String employeeName = reader.readLine();
        em.getTransaction().begin();

        try {
            Address address = new Address();
            address.setText("Vitoshka 15");
            em.persist(address);

            Employee employee = em.createQuery("SELECT e FROM Employee e WHERE e.lastName =:name", Employee.class)
                    .setParameter("name", employeeName)
                    .getSingleResult();

            employee.setAddress(address);

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
