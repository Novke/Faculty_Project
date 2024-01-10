package fon.mas.novica.Faculty.Project.repository;

import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.entity.Lecture;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    public List<Lecture> findAllByEngagement(Engagement engagement);
    public List<Lecture> findAllByEngagementSubjectAndEngagementYear(Subject subject, int year);
    public List<Lecture> findAllByEngagementMemberAndEngagementYear(Member member, int year);
}
