package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
