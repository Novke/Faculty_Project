package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureScheduleRepository extends JpaRepository<LectureSchedule, Long> {

    List<LectureSchedule> findAllBySubjectIdAndYear(Long subjectId, int year);

}
