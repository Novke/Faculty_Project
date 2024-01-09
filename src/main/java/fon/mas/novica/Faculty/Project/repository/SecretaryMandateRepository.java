package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.SecretaryMandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretaryMandateRepository extends JpaRepository<SecretaryMandate, Long> {
    public List<SecretaryMandate> findByDepartmentOrderByStartDateDescIdDesc(Department department);
}
