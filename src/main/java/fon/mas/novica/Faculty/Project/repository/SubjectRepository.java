package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
