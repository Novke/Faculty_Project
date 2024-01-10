package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Department;
import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, Long> {

    public List<Engagement> findAllByMemberAndYear(Member member, int year);
    public List<Engagement> findAllBySubjectAndYear(Subject subject, int year);
    public List<Engagement> findAllBySubjectAndMemberAndYear(Subject subject, Member member, int year);
    public List<Engagement> findAllBySubjectDepartmentAndYear(Department department, int year);
}
