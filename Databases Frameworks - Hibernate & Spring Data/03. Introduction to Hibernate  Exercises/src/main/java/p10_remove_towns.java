import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.in;

public class p10_remove_towns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String townName = reader.readLine();

        em.getTransaction().begin();
        try {
            Town town = em.createQuery("SELECT t FROM Town t WHERE t.name =:name", Town.class)
                    .setParameter("name", townName)
                    .getSingleResult();

            List<Address> addresses = em.createQuery("SELECT a FROM Address a WHERE a.town =:town", Address.class)
                    .setParameter("town", town)
                    .getResultList();

            System.out.printf(addresses.size() == 1
                    ? "%d address in %s deleted%n"
                    : "%d addresses in %s deleted%n",
                    addresses.size(), townName);

            for (Address address : addresses) {
                for (Employee employee : address.getEmployees()) {
                    employee.setAddress(null);
                }
                em.remove(address);
            }

            em.remove(town);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
