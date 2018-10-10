import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class p08_find_10_latest_projects {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        List<Project> projects =
                em.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        StringBuilder sb = new StringBuilder();

        projects.stream().sorted(Comparator.comparing(Project::getName)).forEach(project -> {
            sb.append("Project name: ").append(project.getName()).append(System.lineSeparator());
            sb.append("\tProject Description: ").append(project.getDescription()).append(System.lineSeparator());
            sb.append("\tProject Start Date: ").append(project.getStartDate()).append(System.lineSeparator());
            sb.append("\tProject End Date: ").append(project.getEndDate()).append(System.lineSeparator());
        });

        System.out.println(sb.toString().trim());

        em.close();
        emf.close();
    }
}
