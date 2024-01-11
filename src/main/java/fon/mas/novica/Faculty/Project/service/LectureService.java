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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SimpleDateFormat sdfDatum = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat sdfVreme = new SimpleDateFormat("HH-mm");


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

    public Lecture updateDateTitle(Lecture lecture, String datumString, String vremeString, String title) {
        if (title != null && !title.isBlank()) lecture.setTitle(title.replaceAll("_", " "));
        Date vreme = null, datum = null;

        try {
            vreme = sdfVreme.parse(vremeString);
        } catch (ParseException ignored){}

        try {
            datum = sdfDatum.parse(datumString);
        } catch (ParseException ignored) {}

        LocalDateTime oldDateTime = lecture.getDateTime();

        if (oldDateTime==null){
            if (datum == null) throw new IllegalArgumentException("Date must be provided when current lecture date is null");
            if (vreme == null){
                oldDateTime = LocalDateTime.of(
                        datum.getYear()+1900,
                        datum.getMonth()+1,
                        datum.getDate(),
                        0,
                        0);
            } else {
                oldDateTime = LocalDateTime.of(
                        datum.getYear() + 1900,
                        datum.getMonth() + 1,
                        datum.getDate(),
                        vreme.getHours(),
                        vreme.getMinutes());
            }

        } else {
            //oldDate!=null
            if (vreme != null){
                oldDateTime = LocalDateTime.of(
                        oldDateTime.getYear(),
                        oldDateTime.getMonth(),
                        oldDateTime.getDayOfMonth(),
                        vreme.getHours(),
                        vreme.getMinutes());
            }
            if (datum != null){
                oldDateTime = LocalDateTime.of(
                        datum.getYear()+1900,
                        datum.getMonth() + 1,
                        datum.getDate(),
                        oldDateTime.getHour(),
                        oldDateTime.getMinute()
                );
            }

        }

        lecture.setDateTime(oldDateTime);
        return lectureRepository.save(lecture);
    }
}
