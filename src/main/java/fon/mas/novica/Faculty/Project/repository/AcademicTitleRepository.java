package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.AcademicTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTitleRepository extends JpaRepository<AcademicTitle, Long> {

    public AcademicTitle findByName(String name);

}
