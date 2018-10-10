import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class p01_remove_objects {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            List<Town> towns =
                    entityManager.createQuery("SELECT t FROM Town t WHERE LENGTH(t.name) > 5", Town.class)
                    .getResultList();

            for (Town town : towns) {
                entityManager.detach(town);
                town.setName(town.getName().toLowerCase());
                entityManager.merge(town);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
