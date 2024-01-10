package fon.mas.novica.Faculty.Project.service;

import fon.mas.novica.Faculty.Project.entity.Engagement;
import fon.mas.novica.Faculty.Project.entity.Lecture;
import fon.mas.novica.Faculty.Project.entity.Member;
import fon.mas.novica.Faculty.Project.entity.Subject;
import fon.mas.novica.Faculty.Project.repository.LectureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<Lecture> findAll(){
        return lectureRepository.findAll();
    }

    public Lecture findById(Long id) throws FileNotFoundException {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isPresent()){
            return optionalLecture.get();
        } else {
            throw new FileNotFoundException("Lecture with ID = " + id + " does not exist!");
        }
    }

    public Lecture create(Lecture lecture){
        return lectureRepository.save(lecture);
    }

    public Lecture edit(Lecture lecture) throws FileNotFoundException {
        findById(lecture.getId());

        return lectureRepository.save(lecture);
    }

    public void delete(Long lectureId) throws FileNotFoundException {
        Lecture lecture = findById(lectureId);

        lectureRepository.delete(lecture);
    }

    public List<Lecture> findAllInEngagement(Engagement engagement){
        return lectureRepository.findAllByEngagement(engagement);
    }

    public List<Lecture> findAllBySubjectInYear(Subject subject, int year){
        return lectureRepository.findAllByEngagementSubjectAndEngagementYear(subject, year);
    }
    public List<Lecture> findAllByMemberInYear(Member member, int year){
        return lectureRepository.findAllByEngagementMemberAndEngagementYear(member, year);
    }
}
