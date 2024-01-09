package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.AcademicTitleHistory;
import fon.mas.novica.Faculty.Project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {

    public List<AcademicTitleHistory> findByMemberSortByStartDateDesc(Member member);
}
