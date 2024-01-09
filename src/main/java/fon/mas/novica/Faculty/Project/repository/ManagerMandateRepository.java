package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.ManagerMandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMandateRepository extends JpaRepository<ManagerMandate, Long> {
    public List<ManagerMandate> findByDepartmentOrderByStartDateDescIdDesc(Department department);
}
