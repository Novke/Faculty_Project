package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationTitleRepository extends JpaRepository<EducationTitle, Long> {
}
