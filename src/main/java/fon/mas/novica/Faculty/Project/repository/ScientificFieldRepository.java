package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
}
