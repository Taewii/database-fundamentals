package shampoo_company.core;

import shampoo_company.contracts.Shampoo;
import shampoo_company.entities.ingredients.basic.BasicIngredient;
import shampoo_company.entities.ingredients.basic.Mint;
import shampoo_company.entities.ingredients.basic.Nettle;
import shampoo_company.entities.ingredients.chemical.AmmoniumChloride;
import shampoo_company.entities.labels.BasicLabel;
import shampoo_company.entities.shampoos.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            BasicIngredient am = new AmmoniumChloride();
            BasicIngredient mint = new Mint();
            BasicIngredient nettle = new Nettle();

            BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Contains mint and nettle");
            Shampoo shampoo = new FreshNuke(label);

            shampoo.getIngredients().add(mint);
            shampoo.getIngredients().add(nettle);
            shampoo.getIngredients().add(am);
            em.persist(shampoo);

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
