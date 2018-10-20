package app.repositories;

import app.models.labels.BasicLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<BasicLabel, Long> {
}
